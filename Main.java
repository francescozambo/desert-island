import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create items
        Item food = new Item("Food", 1, true);
        Item wood = new Item("Wood", 3, true);
        Item stone = new Item("Stone", 5, true);

        // Create NPCs
        NPC beachNPC = new NPC("Old Sailor", 50, 5, "Beach Map Piece");
        NPC forestNPC = new NPC("Hunter", 70, 7, "Forest Map Piece");
        NPC caveNPC = new NPC("Miner", 60, 6, "Cave Map Piece");
        NPC oceanNPC = new NPC("Fisherman", 80, 8, "Ocean Map Piece");

        // Create rooms
        Room ocean = new Room("Ocean");
        Room beach = new Room("Beach");
        Room forest = new Room("Forest");
        Room cave = new Room("Cave");
        

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
        player.movePlayer(beach);

        // Create inventory for player
        Inventory inventory = new Inventory();

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        // Display commands
        displayCommands();

        while (playing) {
            Room currentRoom = player.getLocation();
            System.out.println("\nYou are in " + currentRoom.getIdRoom());
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
            	case "back":
            		player.goback();
                case "look":
                    currentRoom.showItems();
                    break;
                case "move":
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
                case "inventory":
                    inventory.showInventory();
                    break;
                case "pickup":
                    System.out.println("Enter the item name you want to pick up:");
                    String itemNameToPick = scanner.nextLine();
                    boolean itemPicked = false;
                    for (Item item : currentRoom.getRoomItems().keySet()) {
                        if (item.getidItem().equalsIgnoreCase(itemNameToPick)) {
                            System.out.println("Enter quantity:");
                            int quantityToPick = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (currentRoom.getRoomItems().get(item) >= quantityToPick) {
                                currentRoom.removeItem(item, quantityToPick);
                                inventory.addItem(item, quantityToPick);
                                itemPicked = true;
                            } else {
                                System.out.println("Not enough items in the room.");
                            }
                            break;
                        }
                    }
                    if (!itemPicked) {
                        System.out.println("Item not found in the room.");
                    }
                    break;
                case "drop":
                    System.out.println("Enter the item name you want to drop:");
                    String itemNameToDrop = scanner.nextLine();
                    boolean itemDropped = false;
                    for (Item item : inventory.getBackpack().keySet()) {
                        if (item.getidItem().equalsIgnoreCase(itemNameToDrop)) {
                            System.out.println("Enter quantity:");
                            int quantityToDrop = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            inventory.removeItem(item, quantityToDrop);
                            currentRoom.addItem(item, quantityToDrop);
                            itemDropped = true;
                            break;
                        }
                    }
                    if (!itemDropped) {
                        System.out.println("Item not found in inventory.");
                    }
                    break;
                case "interact":
                    NPC npc = null;
                    switch (currentRoom.getIdRoom()) {
                        case "Beach":
                            npc = beachNPC;
                            break;
                        case "Forest":
                            npc = forestNPC;
                            break;
                        case "Cave":
                            npc = caveNPC;
                            break;
                        case "Ocean":
                            npc = oceanNPC;
                            break;
                    }
                    if (npc != null) {
                        String mapPiece = npc.interact();
                        System.out.println("You interacted with " + npc.getIdCharacter() + " and received: " + mapPiece);
                    } else {
                        System.out.println("No NPC here.");
                    }
                    break;
                case "quit":
                    playing = false;
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid command, try again.");
                    displayCommands();
            }
        }

        scanner.close();
    }

    private static void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("look - Look around the room");
        System.out.println("move - Move to another room");
        System.out.println("inventory - Show your inventory");
        System.out.println("pickup - Pick up an item");
        System.out.println("drop - Drop an item");
        System.out.println("interact - Interact with an NPC");
        System.out.println("back -Go back to the previous room");
        System.out.println("quit - Quit the game");
    }
}
