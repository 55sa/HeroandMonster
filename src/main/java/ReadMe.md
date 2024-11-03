# CS611-Assignment 4
## Hero&Monster
---------------------------------------------------------------------------
- Name: Jiahao He
- Email: jiahhe@bu.edu


## Files
---------------------------------------------------------------------------

### 0. `Controller.BattleEvent`

Manages the battle logic between hero and monster teams. Key methods include:
- **startBattle**: Handles the main battle loop, where each hero and monster takes turns attacking.
- **heroTurn**: Allows a hero to choose actions like attacking, casting spells, using potions, and equipping items.
- **rewardHeroes**: Rewards surviving heroes with experience points and gold if they win the battle.
- **useSpell, usePotion, useWeapon, useArmor**: Methods for hero actions, including using spells, potions, weapons, and armor.

---

### 1. `Controller.MarketEvent`

Manages the market mechanics, allowing heroes to buy and sell items. Key methods include:
- **buy**: Handles item purchases by heroes, ensuring they meet level and gold requirements and removing purchased items from the market.
- **sell**: Allows heroes to sell items from their inventory back to the market, where they can be repurchased by any hero.
- **action**: Initiates market interactions, providing options to buy, sell, or exit the market.

---

### 2. `Controller.MainEvent`

The main game controller that handles hero creation, game instructions, and movement across the board. Key methods include:
- **displayInstructions**: Displays game instructions for movement and actions.
- **createHeroes**: Allows players to create a team of heroes by selecting names and classes.
- **moveAndCheckForEncounter**: Manages hero movements and encounters with monsters or market tiles.
- **updateHeroPosition**: Updates and displays the hero’s position on the board.

---

### 3. `DataBase.Gamedatabase`

Stores the items available for purchase in the game market. The `Gamedatabase` initializes a list of items, including weapons, armor, potions, and spells. Key methods include:
- **initializeArmors, initializeWeapons, initializePotions, initializeSpells**: Populate the game database with a variety of items using randomized attributes to diversify the marketplace.

---

### 4. `Entity.Board.Board`

The base board class, defining a grid for game navigation. Key methods include:
- **getCell, setCell**: Retrieve or set specific cells on the board.
- **isWithinBounds**: Ensures actions stay within the board’s boundaries.
- **initializeBoard, print**: Abstract methods implemented in subclasses to set up and display the board layout.

---

### 5. `Entity.Board.HeroBoard`

This class represents the game board and manages board initialization, tile shuffling, and hero positioning. Key methods include:
- **initializeBoard**: Sets up different tile types on the board, such as common, inaccessible, and market tiles.
- **populateMarketsWithUniqueItems**: Adds unique items to each market tile for heroes to buy.
- **print**: Displays the current layout of the board with tiles and hero positions.

---

### 6. `Entity.Board.State`

Defines the possible states of cells on the board:
- **INACCESSIBLE**: Cells heroes cannot enter.
- **MARKET**: Cells where heroes can buy or sell items.
- **COMMON**: Cells where battles may occur.
- **HERO**: Indicates the hero’s current position.
- **MONSTER**: Indicates a monster’s position on the board.

---

### 7. `Entity.Human.Equipment`

Manages the hero's equipped items. Key methods include:
- **getLeftHand, setLeftHand, getRightHand, setRightHand**: Manage equipped weapons.
- **getArmor, setArmor**: Manage equipped armor.

---

### 8. `Entity.Human.Paladin, Sorcerer, Warrior`

These classes define hero types with unique attribute modifications:
- **Paladin**: Doubles strength and dexterity.
- **Sorcerer**: Doubles agility and dexterity.
- **Warrior**: Doubles agility and strength.

---

### 9. `Entity.Market.Market`

The market where heroes can purchase or sell items. Key methods include:
- **getProducts, setProducts**: Retrieve or set items in the market.
- **addItem, deleteItem**: Manage inventory, adding and removing items as they are bought or sold.

---

### 10. `Entity.Monster.Dragon, Exoskeleton, Spirit`

Defines monster types with unique characteristics:
- **Dragon**: Deals double damage.
- **Exoskeleton**: Doubles defense.
- **Spirit**: Doubles dodge chance.

---

### 11. `Entity.Team`

Creates and manages a team of heroes or monsters. Key methods include:
- **addMember**: Adds a new member to the team.
- **getTeams**: Retrieves the list of team members.

---

### 12. `Util.Utils`

Utility class for general program functions, such as input validation. Key methods include:
- **getIntInRange**: Validates integer input within a specified range.
- **getStringFromOptions**: Validates string input against allowed options.
- **getYesNo**: Gets and validates a yes/no input.

---

### 13. `Piece`

Represents an entity on the board with an event, such as a market or battle encounter.

### 13. Repository.CharacterFactory

An interface defining the factory pattern for creating characters in the game. It includes methods for creating heroes and monsters, encapsulating the instantiation process for different character types.

- **Methods**:
    - `createHero`: Creates a hero based on type and name.
    - `createMonster`: Creates a monster based on type, name, and level.

### 14. Repository.CharacterFactoryImp

An implementation of `CharacterFactory` that instantiates specific heroes (Warrior, Sorcerer, Paladin) and monsters (Dragon, Exoskeleton, Spirit) based on the provided type. This class simplifies character creation and ensures consistency.

- **Methods**:
    - `createHero`: Returns a hero of the specified class if it exists.
    - `createMonster`: Returns a monster of the specified type if it exists.

### 15. Repository.Event

An interface for handling actions and interactions between characters, such as attacking, dodging, casting spells, and using items. The interface is generic to allow event handling for any type of character.

- **Methods**:
    - `win`: Determines if a team has won by checking if all opposing team members are defeated.
    - `attack`: Initiates an attack action.
    - `dodge`: Checks if the target successfully dodges an attack.
    - `avalibleUnit`: Returns available units from a team.
    - `castSpell`: Allows a character to cast a spell on an opponent.
    - `usePotion`: Allows a character to use a potion on themselves or another character.

### 16. Repository.HeroEventImp

Implements the `Event` interface for heroes, managing all hero-specific events like attacking, casting spells, and using potions. It includes logic for calculating damage, applying item effects, and checking victory conditions for hero teams.

- **Methods**:
    - `win`: Checks if the hero team has defeated all monsters.
    - `attack`: Calculates and executes an attack on a monster, considering hero stats and equipment.
    - `dodge`: Determines if the target monster dodges the attack.
    - `avalibleUnit`: Returns alive heroes from the team.
    - `castSpell`: Handles casting a spell on a monster, including effects like reducing defense or attack stats.
    - `usePotion`: Applies a potion's effects on the hero's stats (HP, MP, strength, dexterity, or agility).

### 17. Repository.ItemFactory

An interface defining methods to create items, allowing the system to dynamically instantiate different item types (weapons, armor, potions, spells) with attributes based on parameters provided.

- **Methods**:
    - `createItem`: Creates an item based on its name, price, level, type, and additional attributes.

### 18. Repository.ItemFactoryImp

An implementation of `ItemFactory` that handles the creation of different item types. This class enables flexible instantiation of items with specific attributes and characteristics.

- **Methods**:
    - `createItem`: Instantiates items such as `Weapon`, `Armor`, `Potion`, or `Spell`, assigning attributes like damage, hands required, or mana cost based on item type.

### 19. Repository.MonsterEventImp

Implements the `Event` interface for monsters, managing monster-specific events in the game, like attacking heroes and checking if the monster team has won. This class handles the monster's attack and dodge mechanics.

- **Methods**:
    - `win`: Checks if all heroes are defeated, indicating a monster team victory.
    - `attack`: Executes an attack on a hero, factoring in hero armor and monster damage.
    - `dodge`: Determines if the target hero dodges the attack.
    - `avalibleUnit`: Returns alive monsters from the team.
    - `castSpell` and `usePotion`: Placeholder methods for monsters, as they typically don't use these items in the current design.

### 20. Main

Start the Program.


## Notes
---------------------------------------------------------------------------
## Project Structure & Extensibility

This program is designed with a modular structure, prioritizing extensibility and scalability through thoughtful class organization and design patterns.

### Structure & Extensibility
1. **Modular Class Design**  
   Core elements like characters, items, and events are separated into specific packages (`Entity`, `Repository`, `Controller`, etc.), allowing easy maintenance and updates.

2. **Interfaces**  
   Interfaces like `Event`, `CharacterFactory`, and `ItemFactory` standardize actions across different implementations (e.g., `HeroEventImp` and `MonsterEventImp`), allowing new features without modifying existing code.

3. **Factory Patterns**  
   `CharacterFactory` and `ItemFactory` use the factory pattern to handle object creation. This approach supports adding new characters or items by extending the factory rather than rewriting code.

### Scalability
1. **Inheritance for Character Specialization**  
   Characters (e.g., `Warrior`, `Paladin`) inherit from base classes, allowing unique traits and easy integration of new types.

2. **Enum Categorization**  
   Enums like `Spell.SpellType` and `Board.State` define fixed categories, making the code more robust and extendable (e.g., adding new spells or board states).

3. **Team and Event Management**  
   `Team` and `Event` classes allow handling of multiple teams and interactions, supporting scalable battle mechanics.

### Additional Design Choices
- **Centralized Item Database**  
  `Gamedatabase` simplifies item management, making it easy to expand inventory without changing core logic.

- **Utility Class**  
  `Utils` provides reusable functions for input validation, reducing redundancy and ensuring consistent handling across the program.

### Some notification

I just set the defult strength for each hero to very huge, which is good for testing.
Some rule for this game maybe can be more specific, I just hardcode each initialtion.



## How to compile and run
---------------------------------------------------------------------------
Navigate to the home directory  after unzipping the files
Run the following instructions:
1. javac *.java
2. java Main

## Input/Output Example
---------------------------------------------------------------------------
```

jiahaohe@JiaHaodeMacBook-Air java % javac *.java 
jiahaohe@JiaHaodeMacBook-Air java % java Main
Welcome to Hero and Monster!
Enter the number of heroes you want in your team (1 to 3): 2
Creating Hero 1
Enter hero name: h1
Choose hero class (Warrior, Sorcerer, Paladin): warrior
Hero h1 of class WARRIOR created successfully!

Creating Hero 2
Enter hero name: h2
Choose hero class (Warrior, Sorcerer, Paladin): paladin
Hero h2 of class PALADIN created successfully!

    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | * | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | C | * | M | M | C | C | C | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | C | * | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | * | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | * | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: i
Heroes:
Name: h1, Level: 5, HP: 500, MP: 250
Name: h2, Level: 5, HP: 500, MP: 250
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | * | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: m
Welcome to the Market!
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
1
Available products for purchase:
Select a hero to make a purchase:
1. h1 (Gold: 10000)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
1
1. Armor_9 - Price: 866, Level: 10
2. Breastplate - Price: 350, Level: 3
3. Armor_1 - Price: 312, Level: 3
Enter the number of the item to buy or 0 to cancel:
1
Cannot purchase. Either not enough gold or hero level is too low.
Select a hero to make a purchase:
1. h1 (Gold: 10000)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
1
1. Armor_9 - Price: 866, Level: 10
2. Breastplate - Price: 350, Level: 3
3. Armor_1 - Price: 312, Level: 3
Enter the number of the item to buy or 0 to cancel:
3
h1 has purchased Armor_1.
Select a hero to make a purchase:
1. h1 (Gold: 9688)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
2
1. Armor_9 - Price: 866, Level: 10
2. Breastplate - Price: 350, Level: 3
Enter the number of the item to buy or 0 to cancel:
2
h2 has purchased Breastplate.
Select a hero to make a purchase:
1. h1 (Gold: 9688)
2. h2 (Gold: 9650)
Enter the number of the hero or 0 to cancel:
1
1. Armor_9 - Price: 866, Level: 10
Enter the number of the item to buy or 0 to cancel:
0
Purchase canceled.
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
3
Exiting the market. Thank you!
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | * | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | M | * | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | C | C | C | M | M |
   --------------------------------
 6 | X | M | * | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | * | C | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | * | C | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | X | C | M | C | C | C | C | M |
   --------------------------------
 1 | X | X | C | M | C | C | X | X |
   --------------------------------
 2 | X | M | C | C | X | M | C | C |
   --------------------------------
 3 | C | X | M | X | M | M | C | C |
   --------------------------------
 4 | X | C | M | M | C | C | M | M |
   --------------------------------
 5 | X | C | C | * | C | C | M | M |
   --------------------------------
 6 | X | M | C | M | C | C | C | C |
   --------------------------------
 7 | C | C | M | M | C | C | C | M |
   --------------------------------
A wild monster appears!
The battle begins!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 2
No spells available.
Spell not used.
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 3
No potions available.
Potion not used.
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 5
Select an armor to equip (0 to exit):
0. Exit
1. Armor_1
Enter a number: 1
h1 has equipped Armor_1.
h1 has equipped Armor_1.
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
dragon1 takes 945 damage, HP now at 0.
h1 attacked dragon1 for 1000 damage!
dragon1 is defeated!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
h1 dodged the attack!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
exoskeleton2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): ^C%                                                       jiahaohe@JiaHaodeMacBook-Air java % javac *.java
jiahaohe@JiaHaodeMacBook-Air java % java Main   
Welcome to Hero and Monster!
Enter the number of heroes you want in your team (1 to 3): 2
Creating Hero 1
Enter hero name: ^C%                                                            jiahaohe@JiaHaodeMacBook-Air java % clear

jiahaohe@JiaHaodeMacBook-Air java % java Main
Welcome to Hero and Monster!
Enter the number of heroes you want in your team (1 to 3): 2
Creating Hero 1
Enter hero name: h1
Choose hero class (Warrior, Sorcerer, Paladin): warrior
Hero h1 of class WARRIOR created successfully!

Creating Hero 2
Enter hero name: h2
Choose hero class (Warrior, Sorcerer, Paladin): palading
Invalid input. Please enter one of the following options: Warrior, Sorcerer, Paladin
Choose hero class (Warrior, Sorcerer, Paladin): paladin
Hero h2 of class PALADIN created successfully!

    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | * | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | * | M | C | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | * | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: i
Heroes:
Name: h1, Level: 5, HP: 500, MP: 250
Name: h2, Level: 5, HP: 500, MP: 250
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | * | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: m
Welcome to the Market!
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
1
Available products for purchase:
Select a hero to make a purchase:
1. h1 (Gold: 10000)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
1
1. Armor_10 - Price: 295, Level: 1
2. FireSpell_8 - Price: 774, Level: 8
3. FireSpell_5 - Price: 467, Level: 7
Enter the number of the item to buy or 0 to cancel:
1
h1 has purchased Armor_10.
Select a hero to make a purchase:
1. h1 (Gold: 9705)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
0
Returning to main market menu.
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
2
Select a hero to sell an item:
1. h1 (Gold: 9705)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
1
Select an item to sell:
1. Armor_10 - Sell Price: 147
Enter the number of the item to sell or 0 to cancel:
1
h1 has sold Armor_10 for 147 gold.
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
1
Available products for purchase:
Select a hero to make a purchase:
1. h1 (Gold: 9852)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
1
1. FireSpell_8 - Price: 774, Level: 8
2. FireSpell_5 - Price: 467, Level: 7
3. Armor_10 - Price: 295, Level: 1
Enter the number of the item to buy or 0 to cancel:
3
h1 has purchased Armor_10.
Select a hero to make a purchase:
1. h1 (Gold: 9557)
2. h2 (Gold: 10000)
Enter the number of the hero or 0 to cancel:
0
Returning to main market menu.
Choose an action:
1. Buy Item
2. Sell Item
3. Exit Market
3
Exiting the market. Thank you!
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | * | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | * | C | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | * | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
You can not cross the wall!
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | * | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | * | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | * | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | * | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | * | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | * | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | * | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | * | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | * | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | * | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | * | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | * | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
A wild monster appears!
The battle begins!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 5
Select an armor to equip (0 to exit):
0. Exit
1. Armor_10
Enter a number: 1
h1 has equipped Armor_10.
h1 has equipped Armor_10.
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
exoskeleton1 takes 890 damage, HP now at 0.
h1 attacked exoskeleton1 for 1000 damage!
exoskeleton1 is defeated!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 0
Ending turn...
spirit2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
spirit2 dodged the attack!
spirit2 attacked h1 for 0 damage!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
spirit2 takes 945 damage, HP now at 0.
h1 attacked spirit2 for 1000 damage!
spirit2 is defeated!
Heroes win the battle!
Distributing rewards to surviving heroes...
h1 leveled up to 6! New stats:
Level: 6
HP: 600
MP: 302
Strength: 22050
Dexterity: 26
Agility: 52
h1 receives 100 EXP and 50 gold.
h2 leveled up to 6! New stats:
Level: 6
HP: 600
MP: 302
Strength: 22050
Dexterity: 52
Agility: 26
h2 receives 100 EXP and 50 gold.
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | * | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: i
Heroes:
Name: h1, Level: 6, HP: 600, MP: 302
Name: h2, Level: 6, HP: 600, MP: 302
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | * | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: d
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | * |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | * |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: w
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | * |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | * |
   --------------------------------
 5 | C | C | M | M | C | C | C | C |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: s
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | * |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
A wild monster appears!
The battle begins!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
spirit1 takes 1029 damage, HP now at 0.
h1 attacked spirit1 for 1102 damage!
spirit1 is defeated!
h1's turn. Choose an action:
0. End Turn
1. Attack
2. Cast Spell
3. Use Potion
4. Equip Weapon
5. Equip Armor
6. Check Attributes
Enter a number (0-6): 1
exoskeleton2 takes 957 damage, HP now at 0.
h1 attacked exoskeleton2 for 1102 damage!
exoskeleton2 is defeated!
Heroes win the battle!
Distributing rewards to surviving heroes...
h1 leveled up to 7! New stats:
Level: 7
HP: 700
MP: 365
Strength: 24309
Dexterity: 27
Agility: 54
h1 leveled up to 8! New stats:
Level: 8
HP: 800
MP: 401
Strength: 26800
Dexterity: 28
Agility: 56
h1 receives 100 EXP and 50 gold.
h2 leveled up to 7! New stats:
Level: 7
HP: 700
MP: 365
Strength: 24309
Dexterity: 54
Agility: 27
h2 leveled up to 8! New stats:
Level: 8
HP: 800
MP: 401
Strength: 26800
Dexterity: 56
Agility: 28
h2 receives 100 EXP and 50 gold.
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | * |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: m
No market at this location.
    0   1   2   3   4   5   6   7  
   --------------------------------
 0 | M | X | C | C | C | M | X | M |
   --------------------------------
 1 | X | C | M | C | M | C | M | C |
   --------------------------------
 2 | C | M | C | M | C | X | C | X |
   --------------------------------
 3 | X | C | C | C | M | C | C | X |
   --------------------------------
 4 | C | C | X | C | C | C | X | M |
   --------------------------------
 5 | C | C | M | M | C | C | C | * |
   --------------------------------
 6 | M | C | X | X | C | X | M | C |
   --------------------------------
 7 | M | M | M | C | C | C | M | M |
   --------------------------------
Game Instructions:
W/w: Move up
A/a: Move left
S/s: Move down
D/d: Move right
Q/q: Quit game
I/i: Show hero and monster info
M/m: Enter market (when on a market tile)
Create heroes and explore the world. Enjoy the game!
Enter a command: q
Thank you for playing! Exiting game.
jiahaohe@JiaHaodeMacBook-Air java % 


```