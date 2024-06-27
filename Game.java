import java.io.*;
import java.util.Scanner;

public class Game {
	DesertIsland island=null; 
	Player player=null; 
	
	Game(){																//Caso nuova partita;
		island = new DesertIsland();
		player = new Player("Castaway", 100, 10);
		player.movePlayer(island.getStartRoom());
		
	}
	Game(int x) throws IOException, ClassNotFoundException{				//Caso gioco "caricato";
			FileInputStream file = new FileInputStream("file.txt");
			ObjectInputStream input = new ObjectInputStream(file);
			island = (DesertIsland)input.readObject();
			player = (Player) input.readObject();
	}
	Game(int x, int y){
		deleteFile("file.txt");
	}
	
	public void play() throws IOException{
		Scanner scanner = new Scanner(System.in);
        boolean playing = true;
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
                    Item itemFound= player.getLocation().getItemById(itemNameToPick);
                    	if(itemFound!=null) {
                            System.out.println("Enter quantity:");
                            int quantityToPick = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (currentRoom.getRoomItems().get(itemFound) >= quantityToPick) {
                                player.getInventory().addItem(itemFound, quantityToPick);
                                if(!player.getInventory().isNotFull(quantityToPick*itemFound.getWeight())) {
                                currentRoom.removeItem(itemFound, quantityToPick);
                                }
                            } else {
                                System.out.println("Not enough items in the room.");
                            }
                            break;
                        
                    }
                    else {
                        System.out.println("Item not found in the room.");
                    }
                    break;
                case "drop":
                    System.out.println("Enter the item name you want to drop:");
                    String itemNameToDrop = scanner.nextLine();
                    Item itemDropped = player.getInventory().getItemById(itemNameToDrop);
                        if (itemDropped!=null) {
                            System.out.println("Enter quantity:");
                            int quantityToDrop = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if(player.getInventory().getQuantity(itemDropped)>=quantityToDrop) {
                            player.getInventory().removeItem(itemDropped, quantityToDrop);
                            currentRoom.addItem(itemDropped, quantityToDrop);
                            }
                            else {
                            	System.out.println("you don't have enough item in your inventory:");
                            }
                            break;
                    }
                    else{
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
                case "exit":
                    playing = false;
                    System.out.println("Thank you for playing!");
                    break;
                case "save":
                	saveGame();
                	System.out.println("Partita salvata");
                	break;
                	
                default:
                    System.out.println("Invalid command, try again.");
                    displayCommands();
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
	private void saveGame() throws IOException {
		try {
			FileOutputStream file = new FileOutputStream("file.txt");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(island);
			output.writeObject(player);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File " +filePath+ " successfully deleted");
            } else {
                System.out.println("An error occured ");
            }
        } else {
            System.out.println(filePath+" doesn't exist");
        }
    }
	
	private static void displayCommands() {							//Stampa i comandi del gioco 
        System.out.println("Available commands:");
        System.out.println("look - Look around the room");
        System.out.println("move - Move to another room");
        System.out.println("back - Go back to the last room");
        System.out.println("inventory - Show your inventory");
        System.out.println("pickup - Pick up an item");
        System.out.println("drop - Drop an item");
        System.out.println("interact - Interact with an NPC");
        System.out.println("back -Go back to the previous room");
        System.out.println("exit - Quit the game");
        System.out.println("save - save the game");
    }
	}

