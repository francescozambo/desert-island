import java.util.Scanner;

public class Main {

	public static void main(String[] args) {			
		// Create items
        Item food = new Item("Food", 1, true);
        Item wood = new Item("Wood", 3, true);
        Item stone = new Item("Stone", 5, true);

        // Create rooms
        Room beach = new Room("Beach");
        Room forest = new Room("Forest");
        Room cave = new Room("Cave");
        Room ocean = new Room("Ocean");

        // Connect rooms
        beach.connectRoom(ocean, forest, null, null);
        forest.connectRoom(beach, cave, null, null);
        cave.connectRoom(forest, null, null, null);
        ocean.connectRoom(null, beach, null, null);

        // Add items to rooms
        beach.addItem(food, 3);
        forest.addItem(wood, 5);
        cave.addItem(stone, 2);

        // Create player
        Player player = new Player("Castaway", 100, 10);
        player.movePlayer(ocean);

        // Create inventory for player
        Inventory inventory = new Inventory();

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            Room currentRoom = player.getLocation();
            System.out.println("\nYou are in " + currentRoom.getIdRoom());
            System.out.println("What would you like to do?");
            System.out.println("1. Look around");
            System.out.println("2. Move");
            System.out.println("3. Show Inventory");
            System.out.println("4. Pick up an item");
            System.out.println("5. Drop an item");
            System.out.println("6. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    currentRoom.showItems();
                    break;
                case 2:
                    System.out.println("Where would you like to go? (north/south/east/west)");
                    String direction = scanner.nextLine();
                    Room newRoom = currentRoom.returnRoom(direction);
                    if (newRoom != null) {
                        player.movePlayer(newRoom);
                        System.out.println("You moved to " + newRoom.getIdRoom());
                    } else {
                        System.out.println("You can't go that way.");
                    }
                    break;
                case 3:
                    inventory.showInventory();
                    break;
                case 4:
                    System.out.println("Enter the item name you want to pick up:");
                    String itemNameToPick = scanner.nextLine();
                    for (Item item : currentRoom.getRoomItems().keySet()) {
                        if (item.getidItem().equalsIgnoreCase(itemNameToPick)) {
                            System.out.println("Enter quantity:");
                            int quantityToPick = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (currentRoom.getRoomItems().get(item) >= quantityToPick) {
                                currentRoom.removeItem(item, quantityToPick);
                                inventory.addItem(item, quantityToPick);
                            } else {
                                System.out.println("Not enough items in the room.");
                            }
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter the item name you want to drop:");
                    String itemNameToDrop = scanner.nextLine();
                    for (Item item : inventory.getBackpack().keySet()) {
                        if (item.getidItem().equalsIgnoreCase(itemNameToDrop)) {
                            System.out.println("Enter quantity:");
                            int quantityToDrop = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            inventory.removeItem(item, quantityToDrop);
                            currentRoom.addItem(item, quantityToDrop);
                            break;
                        }
                    }
                    break;
                case 6:
                    playing = false;
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
	}

}
