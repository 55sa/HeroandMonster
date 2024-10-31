package Controller;

import Entity.Items.Armor;
import Entity.Items.Item;
import Entity.Items.Type;
import Entity.Items.Weapon;
import Entity.Market.Market;
import Entity.Human.Hero;
import Entity.Team;

import java.util.List;
import java.util.Scanner;

public class MarketEvent {
    private final Scanner scanner = new Scanner(System.in);

    // Method for buying an item for a specific hero in the team
    private void buy(Market market, Team<Hero> heroTeam) {
        System.out.println("Available products for purchase:");
        List<Item> products = market.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Item item = products.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - Price: " + item.getPrice() + ", Level: " + item.getLevel());
        }
        int choice = -1;

        while (choice != 0) {
            // Display heroes in the team
            System.out.println("Select a hero to make a purchase:");
            for (int i = 0; i < heroTeam.getTeams().size(); i++) {
                Hero hero = heroTeam.getTeams().get(i);
                System.out.println((i + 1) + ". " + hero.getName() + " (Gold: " + hero.getGold() + ")");
            }
            System.out.println("Enter the number of the hero or 0 to cancel:");
            int heroChoice = scanner.nextInt();

            if (heroChoice > 0 && heroChoice <= heroTeam.getTeams().size()) {
                Hero selectedHero = heroTeam.getTeams().get(heroChoice - 1);

                // Display available products
                for (int i = 0; i < products.size(); i++) {
                    Item item = products.get(i);
                    System.out.println((i + 1) + ". " + item.getName() + " - Price: " + item.getPrice() + ", Level: " + item.getLevel());
                }

                System.out.println("Enter the number of the item to buy or 0 to cancel:");
                choice = scanner.nextInt();

                if (choice > 0 && choice <= products.size()) {
                    Item selectedItem = products.get(choice - 1);

                    // Check if hero has enough gold and meets level requirement
                    if (selectedHero.getLevel() >= selectedItem.getLevel() && selectedHero.getGold() >= selectedItem.getPrice()) {
                        selectedHero.addItem(selectedItem); // Add item to hero's inventory
                        selectedHero.setGold(selectedHero.getGold() - selectedItem.getPrice()); // Deduct gold
                        System.out.println(selectedHero.getName() + " has purchased " + selectedItem.getName() + ".");
                    } else {
                        System.out.println("Cannot purchase. Either not enough gold or hero level is too low.");
                    }
                } else if (choice == 0) {
                    System.out.println("Purchase canceled.");
                } else {
                    System.out.println("Invalid choice. Please select a valid item number or 0 to cancel.");
                }
            } else if (heroChoice == 0) {
                System.out.println("Returning to main market menu.");
                return;
            } else {
                System.out.println("Invalid hero selection. Please try again.");
            }
        }
    }

    // Method for selling an item from a specific hero in the team
    private void sell(Team<Hero> heroTeam) {
        System.out.println("Select a hero to sell an item:");
        for (int i = 0; i < heroTeam.getTeams().size(); i++) {
            Hero hero = heroTeam.getTeams().get(i);
            System.out.println((i + 1) + ". " + hero.getName() + " (Gold: " + hero.getGold() + ")");
        }
        System.out.println("Enter the number of the hero or 0 to cancel:");
        int heroChoice = scanner.nextInt();

        if (heroChoice > 0 && heroChoice <= heroTeam.getTeams().size()) {
            Hero selectedHero = heroTeam.getTeams().get(heroChoice - 1);
            List<Item> inventory = selectedHero.getItems();

            if (inventory.isEmpty()) {
                System.out.println("No items available for sale.");
                return;
            }

            System.out.println("Select an item to sell:");
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " - Sell Price: " + (item.getPrice() / 2));
            }

            System.out.println("Enter the number of the item to sell or 0 to cancel:");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= inventory.size()) {
                Item selectedItem = inventory.get(choice - 1);

                // Check if the item is equipped and remove it from equipment if necessary
                if (selectedItem.getType() == Type.WEAPON) {
                    Weapon weapon = (Weapon) selectedItem;
                    if (selectedHero.getEquipment().getRightHand() == weapon) {
                        selectedHero.getEquipment().setRightHand(null);
                        System.out.println("Unequipped " + weapon.getName() + " from right hand.");
                    } else if (selectedHero.getEquipment().getLeftHand() == weapon) {
                        selectedHero.getEquipment().setLeftHand(null);
                        System.out.println("Unequipped " + weapon.getName() + " from left hand.");
                    }
                } else if (selectedItem.getType() == Type.ARMOR) {
                    Armor armor = (Armor) selectedItem;
                    if (selectedHero.getEquipment().getArmor() == armor) {
                        selectedHero.getEquipment().setArmor(null);
                        System.out.println("Unequipped " + armor.getName() + " from armor slot.");
                    }
                }

                // Proceed with the sale
                selectedHero.removeItem(selectedItem); // Remove item from inventory
                selectedHero.setGold(selectedHero.getGold() + (selectedItem.getPrice() / 2)); // Add half of item price to hero's gold
                System.out.println(selectedHero.getName() + " has sold " + selectedItem.getName() + " for " + (selectedItem.getPrice() / 2) + " gold.");
            } else {
                System.out.println("Sale canceled.");
            }
        } else if (heroChoice == 0) {
            System.out.println("Returning to main market menu.");
        } else {
            System.out.println("Invalid hero selection. Please try again.");
        }
    }

    // Method for performing market actions with a team
    public void action(Market market, Team<Hero> heroTeam) {
        System.out.println("Welcome to the Market!");
        boolean shopping = true;

        while (shopping) {
            System.out.println("Choose an action:");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("3. Exit Market");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    buy(market, heroTeam);
                    break;
                case 2:
                    sell(heroTeam);
                    break;
                case 3:
                    shopping = false;
                    System.out.println("Exiting the market. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
