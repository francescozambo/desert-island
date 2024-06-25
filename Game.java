import java.io.*;
import java.util.Scanner;

public class Game {
	DesertIsland island=null; 
	Player player=null; 
	
	Game(){
		island = new DesertIsland();
		player = new Player("Castaway", 100, 10);
		player.movePlayer(island.getStartRoom());
		
	}
	Game(int x) throws IOException, ClassNotFoundException{
		
		try {
			FileInputStream file = new FileInputStream("file.txt");
			ObjectInputStream input = new ObjectInputStream(file);
			island = (DesertIsland)input.readObject();
			player = (Player) input.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() throws IOException{
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
                case "back":
                    Room lastRoom = player.getLastLocation();
                    if (lastRoom != null) {
                        player.movePlayer(lastRoom);
                        System.out.println("You moved back to " + lastRoom.getIdRoom());
                    } else {
                        System.out.println("You have no previous room to go back to.");
                    }
                    break;
                case "inventory":
                   player.getInventory().showInventory();
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
                                player.getInventory().addItem(item, quantityToPick);
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
                    for (Item item : player.getInventory().getBackpack().keySet()) {
                        if (item.getidItem().equalsIgnoreCase(itemNameToDrop)) {
                            System.out.println("Enter quantity:");
                            int quantityToDrop = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            player.getInventory().removeItem(item, quantityToDrop);
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
                    npc=island.getRoomNPC(player.getLocation());
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
             try {
			FileOutputStream file = new FileOutputStream("file.txt");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(island);
			output.writeObject(player);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        }

        scanner.close();
    }
	public void printStatusGame() {
		System.out.println("Player Room: "+player.getLocation().getIdRoom());
		System.out.println("Player Helath: "+player.getHealth()+"/"+player.getMaxHealth());
		System.out.print("Player Inventory: ");
		player.getInventory().showInventory();
	}

    //Commands
    
    private static void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("look - Look around the room");
        System.out.println("move - Move to another room");
        System.out.println("back - Go back to the last room");
        System.out.println("inventory - Show your inventory");
        System.out.println("pickup - Pick up an item");
        System.out.println("drop - Drop an item");
        System.out.println("interact - Interact with an NPC");
        System.out.println("back -Go back to the previous room");
        System.out.println("quit - Quit the game");
    }
	}

