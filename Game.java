import java.io.*;
import java.util.Scanner;

public class Game {
	DesertIsland island=null; 
	Player player=null; 
	Story story = new Story();
	Game(){																//Caso nuova partita;
		island = new DesertIsland();
		System.out.println("What's your name?");
		Scanner scanner = new Scanner (System.in);
		String x = scanner.nextLine();
		player = new Player(x, 100, 10);
		story.beginnin(player.getIdCharacter());
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
            String words[] = splitCommand(command);
            if(words.length==1) {
            switch (words[0]) {
                case "look":
                    currentRoom.showItems();
                    break;
                case "map":
                	player.showMap();
                	break;
                case "help":
                	displayCommands();
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
                case "interact":
                    NPC npc = null;
                    npc=island.getRoomNPC(player.getLocation());
                    Room room = player.getLocation();
                    if (npc != null) {
                    	if(npc.getInteract()==false) {	//quando non si ha mai interagito prima con un npc
                    		try{
                        	switch(room.getIdRoom()) {
                        	case "Beach": 
                        		story.firtsInteractionBeach(island, player, npc);
                        		break;
                        	case "Ocean":
                        		break;
                        	case "Forest":
                        		break;
                        	case "Cave":
                        		break;
                        	default: 
                        		throw new IllegalArgumentException("Unknown room");
                        	}
                        	}
                    		catch(IllegalArgumentException e) {
                    			System.err.println("ERROR");
                    		}
                        }
                    	else {
                    		 System.out.println(npc.getIdCharacter().toUpperCase()+": I have nothing to say");
                    	}
                    } 
                    else {
                        System.out.println("No NPC here.");
                    }
                    break;
                case "inventory":
                    player.getInventory().showInventory();
                     break;
                case "exit":
                    playing = false;
                    System.out.println("Thank you for playing!");
                    break;
                case "save":
                	saveGame();
                	System.out.println("Partita salvata");
                	printStatusGame();
                	break;
                default:
                    System.out.println("Invalid command, try again.");
                    displayCommands();
            }
            }
            else if(words.length==2) {
		 
            	switch(words[0]) {
                case "move":
		 if(words[1].equalsIgnoreCase("north")||words[1].equalsIgnoreCase("south")||words[1].equalsIgnoreCase("west")||words[1].equalsIgnoreCase("east")) {
                    String direction = words[1];
                    Room newRoom = currentRoom.returnRoom(direction);
                    if (newRoom != null) {
                        player.movePlayer(newRoom);
                       // System.out.println("You moved to " + newRoom.getIdRoom());
                    } else {
                        System.out.println("You can't go that way.");
                    }
		}
            	else {
            		System.out.println("Invalid command, try again.");
                    displayCommands();
            	}
                    break;
			case "use": 
				
                default:
                    System.out.println("Invalid command, try again.");
                    displayCommands();
            	}
            
            }
            else {
                switch(words[0]) {
                case "pickup":
                   // System.out.println("Enter the item name you want to pick up:");
                   // String itemNameToPick = scanner.nextLine();
                    String itemNameToPick = words[1];
                    Item itemFound= player.getLocation().getItemById(itemNameToPick);
                    	if(itemFound!=null) {
                            //System.out.println("Enter quantity:");
                           // int quantityToPick = scanner.nextInt();
                            int quantityToPick = Integer.parseInt(words[2]);
                           // scanner.nextLine(); // Consume newline
                            if (currentRoom.getRoomItems().get(itemFound) >= quantityToPick) {
                            	
                                if(player.getInventory().isFull(itemFound.getWeight()*quantityToPick)==false) {
                               
                                currentRoom.removeItem(itemFound, quantityToPick);
                                }
                                player.getInventory().addItem(itemFound, quantityToPick);
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
                    //System.out.println("Enter the item name you want to drop:");
                    //String itemNameToDrop = scanner.nextLine();
                    String itemNameToDrop = words[1];
                    Item itemDropped = player.getInventory().getItemById(itemNameToDrop);
                        if (itemDropped!=null) {
                           // System.out.println("Enter quantity:");
                           // int quantityToDrop = scanner.nextInt();
                            int quantityToDrop = Integer.parseInt(words[2]);
                          //  scanner.nextLine(); // Consume newline
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
                default:
                    System.out.println("Invalid command, try again.");
                    displayCommands();
            }
        }

        if(player.allMap()==true) {
        	playing=false;
        }
    }
        scanner.close();
	}
	
	private void printStatusGame() {
		System.out.println("Player Room: "+player.getLocation().getIdRoom());
		System.out.println("Player Health: "+player.getHealth()+"/"+player.getMaxHealth());
		System.out.println("Player Inventory: ");
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
        System.out.println("look --> Look around the room");
        System.out.println("move + direction (north/south/west/east) --> Move to another room in the chosen direction");
        System.out.println("back --> Go back to the last room");
        System.out.println("inventory --> Show your inventory");
        System.out.println("pickup + item name + quantity --> Pick up an item");
        System.out.println("drop --> Drop an item");
        System.out.println("interact --> Interact with an NPC");
        System.out.println("back --> Go back to the previous room");
        System.out.println("exit --> Quit the game");
        System.out.println("save --> save the game");
        System.out.println("help --> show commands");
        System.out.println("map --> show map");
    }
	private static String[] splitCommand(String s) {
		final int maxWord =3;
		String[] x = s.split(" ");
		if(x.length<=maxWord) {
		return x;
		}
		else {
			x= new String[maxWord];
			for (int i = 0; i < x.length; i++) {
	            x[i] = " ";
	        }
			return x;
		}
	}
	}



