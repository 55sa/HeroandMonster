# CS611-Assignment 5
## Legends of Valor
---------------------------------------------------------------------------
- Name: Jiahao He, Bhavya Surana
- Email: jiahhe@bu.edu, surana@bu.edu


## UML Diagram
---------------------------------------------------------------------------


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

### 3. `Controller.LegendsOfValor`

Manages the main gameplay logic for the “Legends of Valor” game, including the board setup, hero and monster interactions, and game flow. Key methods include:
- **Start**: Initializes the game, sets up the hero and monster teams, and manages the main game loop, where heroes and monsters take turns.
- **initializeHeroPositions / initializeMonsterPositions**: Sets up initial positions of heroes and monsters on the board.
- **moveAndCheckForEncounter**: Validates a hero’s move, updates their position, and handles board state effects (e.g., entering special zones like BUSH, CAVE, or KOULOU).
- **isValidMove**: Ensures a hero’s move follows the game’s rules (e.g., avoiding inaccessible cells, not moving behind monsters, etc.).
- **heroTurn**: Manages actions for a hero’s turn, including attacking, using items, teleporting, and recalling to the Nexus.
- **monsterBehavior**: Defines how monsters take actions during their turn, including attacking heroes or moving forward.
- **respawnDeadHeroes**: Respawns heroes who died in their Nexus at the start of a new round.
- **spawnNewMonsters**: Spawns new monsters periodically based on the highest hero level and available space in lanes.
- **teleport**: Allows a hero to teleport to an adjacent space near another hero in a different lane, adhering to teleportation restrictions.
- **distributeRewards**: Grants gold and experience points to surviving heroes after defeating a monster.
- **endRound**: Handles end-of-round events, including respawning heroes and spawning new monsters.
- **displayInstructions**: Prints the list of actions available to the player during the game.

---

### 4. `Controller.MusicPlayer`

Handles background music playback for the game, enhancing the player’s experience with continuous audio. Key methods include:
- **playMusic**: Loads and plays the specified audio file in a continuous loop.
- **stopMusic**: Stops and closes the currently playing audio clip.

---

### 5. `DataBase.Gamedatabase`

Stores the items available for purchase in the game market. The `Gamedatabase` initializes a list of items, including weapons, armor, potions, and spells. Key methods include:
- **initializeArmors, initializeWeapons, initializePotions, initializeSpells**: Populate the game database with a variety of items using randomized attributes to diversify the marketplace.

---

### 6. `Entity.Board.Board`

The base board class, defining a grid for game navigation. Key methods include:
- **getCell, setCell**: Retrieve or set specific cells on the board.
- **isWithinBounds**: Ensures actions stay within the board’s boundaries.
- **initializeBoard, print**: Abstract methods implemented in subclasses to set up and display the board layout.

---

### 7. `Entity.Board.HeroBoard`

This class represents the game board and manages board initialization, tile shuffling, and hero positioning. Key methods include:
- **initializeBoard**: Sets up different tile types on the board, such as common, inaccessible, and market tiles.
- **populateMarketsWithUniqueItems**: Adds unique items to each market tile for heroes to buy.
- **print**: Displays the current layout of the board with tiles and hero positions.

---

### 8. `Entity.Board.LegendBoard`

Defines the specific 8x8 game board used in “Legends of Valor,” featuring various cell states and mechanics. Key methods include:
- **LegendBoard**: Constructor that initializes the 8x8 grid and sets up the board layout with Nexus, lanes, and special states.
- **initializeBoard**: Populates the board with states like Nexus, walls, and special zones (e.g., Bush, Cave, Koulou), and assigns markets to Nexus cells.
- **getRandomState**: Randomly assigns a state (Bush, Cave, Koulou, or Common) to applicable board cells.
- **print**: Displays the board in a visually formatted way, showing the state, hero, and monster information for each cell.
- **getStateSymbol**: Maps board cell states to their respective symbols for display purposes (e.g., “N” for Nexus, “B” for Bush).

---

### 9. `Entity.Board.State`

Defines the possible states of cells on the board:
- **INACCESSIBLE**: Cells heroes cannot enter.
- **MARKET**: Cells where heroes can buy or sell items.
- **COMMON**: Cells where battles may occur.
- **HERO**: Indicates the hero’s current position.
- **MONSTER**: Indicates a monster’s position on the board.

---

### 10. `Entity.Human.Equipment`

Manages the hero's equipped items. Key methods include:
- **getLeftHand, setLeftHand, getRightHand, setRightHand**: Manage equipped weapons.
- **getArmor, setArmor**: Manage equipped armor.

---

### 11. `Entity.Human.Paladin, Sorcerer, Warrior`

These classes define hero types with unique attribute modifications:
- **Paladin**: Doubles strength and dexterity.
- **Sorcerer**: Doubles agility and dexterity.
- **Warrior**: Doubles agility and strength.

---

### 12. `Entity.Market.Market`

The market where heroes can purchase or sell items. Key methods include:
- **getProducts, setProducts**: Retrieve or set items in the market.
- **addItem, deleteItem**: Manage inventory, adding and removing items as they are bought or sold.

---

### 13. `Entity.Monster.Dragon, Exoskeleton, Spirit`

Defines monster types with unique characteristics:
- **Dragon**: Deals double damage.
- **Exoskeleton**: Doubles defense.
- **Spirit**: Doubles dodge chance.

---

### 14. `Entity.Team`

Creates and manages a team of heroes or monsters. Key methods include:
- **addMember**: Adds a new member to the team.
- **getTeams**: Retrieves the list of team members.

---

### 15. `Util.Utils`

Utility class for general program functions, such as input validation. Key methods include:
- **getIntInRange**: Validates integer input within a specified range.
- **getStringFromOptions**: Validates string input against allowed options.
- **getYesNo**: Gets and validates a yes/no input.

---

### 16. `Piece`

Represents an entity on the board with an event, such as a market or battle encounter.

### 17. Repository.CharacterFactory

An interface defining the factory pattern for creating characters in the game. It includes methods for creating heroes and monsters, encapsulating the instantiation process for different character types.

- **Methods**:
    - `createHero`: Creates a hero based on type and name.
    - `createMonster`: Creates a monster based on type, name, and level.

### 18. Repository.CharacterFactoryImp

An implementation of `CharacterFactory` that instantiates specific heroes (Warrior, Sorcerer, Paladin) and monsters (Dragon, Exoskeleton, Spirit) based on the provided type. This class simplifies character creation and ensures consistency.

- **Methods**:
    - `createHero`: Returns a hero of the specified class if it exists.
    - `createMonster`: Returns a monster of the specified type if it exists.

### 19. Repository.Event

An interface for handling actions and interactions between characters, such as attacking, dodging, casting spells, and using items. The interface is generic to allow event handling for any type of character.

- **Methods**:
    - `win`: Determines if a team has won by checking if all opposing team members are defeated.
    - `attack`: Initiates an attack action.
    - `dodge`: Checks if the target successfully dodges an attack.
    - `avalibleUnit`: Returns available units from a team.
    - `castSpell`: Allows a character to cast a spell on an opponent.
    - `usePotion`: Allows a character to use a potion on themselves or another character.

### 20. Repository.HeroEventImp

Implements the `Event` interface for heroes, managing all hero-specific events like attacking, casting spells, and using potions. It includes logic for calculating damage, applying item effects, and checking victory conditions for hero teams.

- **Methods**:
    - `win`: Checks if the hero team has defeated all monsters.
    - `attack`: Calculates and executes an attack on a monster, considering hero stats and equipment.
    - `dodge`: Determines if the target monster dodges the attack.
    - `avalibleUnit`: Returns alive heroes from the team.
    - `castSpell`: Handles casting a spell on a monster, including effects like reducing defense or attack stats.
    - `usePotion`: Applies a potion's effects on the hero's stats (HP, MP, strength, dexterity, or agility).

### 21. Repository.ItemFactory

An interface defining methods to create items, allowing the system to dynamically instantiate different item types (weapons, armor, potions, spells) with attributes based on parameters provided.

- **Methods**:
    - `createItem`: Creates an item based on its name, price, level, type, and additional attributes.

### 22. Repository.ItemFactoryImp

An implementation of `ItemFactory` that handles the creation of different item types. This class enables flexible instantiation of items with specific attributes and characteristics.

- **Methods**:
    - `createItem`: Instantiates items such as `Weapon`, `Armor`, `Potion`, or `Spell`, assigning attributes like damage, hands required, or mana cost based on item type.

### 23. Repository.MonsterEventImp

Implements the `Event` interface for monsters, managing monster-specific events in the game, like attacking heroes and checking if the monster team has won. This class handles the monster's attack and dodge mechanics.

- **Methods**:
    - `win`: Checks if all heroes are defeated, indicating a monster team victory.
    - `attack`: Executes an attack on a hero, factoring in hero armor and monster damage.
    - `dodge`: Determines if the target hero dodges the attack.
    - `avalibleUnit`: Returns alive monsters from the team.
    - `castSpell` and `usePotion`: Placeholder methods for monsters, as they typically don't use these items in the current design.

### 24. Main

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

4. **Stratergy Pattern**
    We are achieving the `Strategy Pattern` in our code by encapsulating different algorithms or behaviors in separate classes and making them interchangeable at runtime. One of the implementations is `Event interface` and its concrete implementations (`HeroEventImp`, `MonsterEventImp`)

5. **State Pattern**
    One example of `State Pattern` implemented in the Game is used in the `LegendBoard` class through the State enum, which determines the behavior of each cell on the board. For example, in `moveAndCheckForEncounter`, the hero’s attributes are dynamically modified based on the state of the cell they enter (BUSH, CAVE, Koulou). Additionally, the INACCESSIBLE state restricts movement by preventing access to specific cells.

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

I just set the default strength for each hero to very huge, which is good for testing.
Some rule for this game maybe can be more specific, I just hardcode each initialtion.



## How to compile and run
---------------------------------------------------------------------------
Navigate to the home directory after unzipping the files
Run the following instructions:
1. javac *.java
2. java Main

## Input/Output Example
---------------------------------------------------------------------------
```

Welcome to Legends Of Valor!
Enter the number of heroes you want in your team (1 to 3): 2
Creating Hero 1
Enter hero name: h1
Choose hero class (Warrior, Sorcerer, Paladin): warrior
Hero h1 of class WARRIOR created successfully!

Creating Hero 2
Enter hero name: h2
Choose hero class (Warrior, Sorcerer, Paladin): paladin
Hero h2 of class PALADIN created successfully!

      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |     M1  |         |X  X  X|     M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |  h1     |         |X  X  X|  h2     |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
It's h1's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |     M1  |         |X  X  X|     M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |  h1     |         |X  X  X|  h2     |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h1: from (7,0) to (6,0)
Cell (7,0) has monster: false
Cell (7,-1) is out of bounds.
Cell (7,1) has monster: false
h1 has entered a KOULOU! Strength increased to 22000

It's h2's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |     M1  |         |X  X  X|     M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |  h1     |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|  h2     |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h2: from (7,3) to (6,3)
Cell (7,3) has monster: false
Cell (7,2) is empty.
Cell (7,4) has monster: false
h2 has entered a CAVE! Agility increased to 44

It's M1's turn.
M1 moves forward.

It's M2's turn.
M2 moves forward.
   ################################ CONTINUING THE GAME ################################
It's h1's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |     M1  |         |X  X  X|     M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |  h1     |         |X  X  X|  h2     |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h1: from (4,0) to (3,0)
Cell (4,0) has monster: false
Cell (4,-1) is out of bounds.
Cell (4,1) has monster: false
h1 has entered a KOULOU! Strength increased to 29282

It's h2's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1 M1  |         |X  X  X|     M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|  h2     |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h2: from (4,3) to (3,3)
Cell (4,3) has monster: false
Cell (4,2) is empty.
Cell (4,4) has monster: false
h2 has entered a KOULOU! Strength increased to 24200

It's M1's turn.
Detected hero h1 at (3,0).
M1 attacks h1!
M1 attacked h1 for 320 damage!

It's M2's turn.
Detected hero h2 at (3,3).
M2 attacks h2!
M2 attacked h2 for 320 damage!
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1 M1  |         |X  X  X|  h2 M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
It's h1's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1 M1  |         |X  X  X|  h2 M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h1: from (3,0) to (2,0)
Cell (3,0) has monster: true
Cannot move behind a monster without killing it.
Invalid move. Please try again.
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1 M1  |         |X  X  X|  h2 M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h1: from (3,0) to (2,0)
Cell (3,0) has monster: true
Cannot move behind a monster without killing it.
Invalid move. Please try again.
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1 M1  |         |X  X  X|  h2 M2  |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 5
Returning to the previous menu.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 3
Choose a monster to attack:
1. M1
Enter monster number: 1
M1 takes 1338 damage, HP now at 0.
h1 attacked M1 for 1464 damage!
M1 is defeated!
h1 gains 4000 gold and 16 experience.
h2 gains 4000 gold and 16 experience.

It's h2's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 3
Choose a monster to attack:
1. M2
Enter monster number: 1
M2 takes 1105 damage, HP now at 0.
h2 attacked M2 for 1210 damage!
M2 is defeated!
h1 gains 4000 gold and 16 experience.
h2 gains 4000 gold and 16 experience.
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |  h1     |         |X  X  X|  h2     |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
It's h1's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 6
Choose a hero to teleport to:
1. h2 at (3,3)
Choose a hero (1-1): 1
Available positions:
1. (4,3)
2. (3,4)
Choose a position (1-2): 2
h1 teleported to position (3,4).

It's h2's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|  h2     |  h1     |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h2: from (3,3) to (2,3)
Cell (3,3) has monster: false
Cell (3,2) is empty.
Cell (3,4) has monster: false
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|  h2     |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|         |  h1     |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
   ################################ CONTINUING THE GAME ################################
   It's h2's turn.
Hero Actions in Legends of Valor:
1. Change Weapon
2. Use a Potion
3. Attack
4. Cast a Spell
5. Move
6. Teleport
7. Recall
8. Change Armor
9. Enter Market
10. Display Attribute
0. Skip round
Choose an action (0-10): 5
      0         1         2         3         4         5         6         7
   -----------------------------------------------------------------------------
 0 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 1 |    P    |    B    |   I   |    P    |    C    |   I   |    C    |    B    |
   |         |         |X  X  X|  h2     |  h1     |X  X  X|         |         |
   -----------------------------------------------------------------------------
 2 |    C    |    B    |   I   |    P    |    C    |   I   |    P    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 3 |    K    |    P    |   I   |    K    |    K    |   I   |    P    |    P    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 4 |    K    |    B    |   I   |    K    |    K    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 5 |    K    |    K    |   I   |    B    |    B    |   I   |    B    |    B    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 6 |    K    |    C    |   I   |    C    |    B    |   I   |    K    |    C    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   -----------------------------------------------------------------------------
 7 |    N    |    N    |   I   |    N    |    N    |   I   |    N    |    N    |
   |         |         |X  X  X|         |         |X  X  X|         |         |
   --------------------------------------------------------------------------------
Choose a direction to move:
1. North
2. West
3. South
4. East
5. Return to the previous menu
Enter direction (1-5): 1
Checking move for Hero h2: from (1,3) to (0,3)
Cell (1,3) has monster: false
Cell (1,2) is empty.
Cell (1,4) has monster: false
Heroes have reached the monsters' Nexus! Heroes win!

```