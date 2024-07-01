package main;
import java.io.*;
import java.util.Scanner;

public class Game {
	DesertIsland island=null; 
	Player player=null; 
	Story story = new Story();
	Game(){																//Costruttore nuova partita;
		island = new DesertIsland();
		System.out.println("What's your name?");
		Scanner scanner = new Scanner (System.in);
		String x = scanner.nextLine();
		player = new Player(x, 100, 10);
		story.beginnin(player.getIdCharacter());
		player.movePlayer(island.getStartRoom());
		
	}
	Game(int x) throws IOException, ClassNotFoundException{				//Costruttore gioco "caricato"
			FileInputStream file = new FileInputStream("file.txt");
			ObjectInputStream input = new ObjectInputStream(file);
			island = (DesertIsland)input.readObject();
			player = (Player) input.readObject();
	}
	Game(int x, int y){												//Costruttore gioco caso eliminazione file di salvataggio
		deleteFile("file.txt");
	}
	
	public void play() throws IOException{									//esegue le azioni associate ai comandi
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
            playing =process1WordCommand(currentRoom, words);
            }
            else if(words.length==2) {
		 
            playing=process2WordCommand(currentRoom, words);	
            
            }
            else {
            playing=process3WordCommand(currentRoom, words);
            }

        if(player.allMap()==true) {
        	player.showMap();
        	System.out.print("Congratulation, you won!\n");
        	playing=false;
        }
        if(player.isAlive()==false) {
        	System.out.print("You died\n");
        	playing=false;
        }
    }
        scanner.close();
	}
	
	private void printStatusGame() {																		//Stampa le informazioni del giocatore
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
	
	public static void deleteFile(String filePath) {												//elimina i file del gioco
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
        System.out.println("player --> View player status");
        System.out.println("inventory --> Show your inventory");
        System.out.println("map --> show map");
        System.out.println("move + direction (north/south/west/east) --> Move to another room in the chosen direction");
        System.out.println("back --> Go back to the last room");
        System.out.println("pickup + item name + quantity --> Pick up an item");
        System.out.println("drop + item name + quantity --> Drop an item");
        System.out.println("use + item name (food or weapon) --> use the weapon or eat 1 food");
        System.out.println("interact --> Interact with an NPC");
        System.out.println("exit --> Quit the game");
        System.out.println("save --> save the game");
        System.out.println("help --> show commands");
        
    }
	
	private boolean process1WordCommand(Room currentRoom,String words[]) throws IOException {
		switch (words[0]){
        case "look":
            currentRoom.showItems();
            System.out.println("\nNPC: "+currentRoom.getNPC().getIdCharacter());
            break;
        case "player":
        	player.printStatusPlayer();
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
                		story.firtsInteractionBeach(player, npc);
                		break;
                	case "Ocean":
                		break;
                	case "Forest":
                		if(npc.getHide()==true)
                			story.secondInteractionForest(player, npc);
                		else
                			story.firstInteractionForest(player, npc);
                		break;
                	case "Cave":
                		story.firstInteractionCave(player, npc);
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
            		if(room.getIdRoom().equalsIgnoreCase("Forest")) {
            			story.thirdInteractionForest(player, npc);
            		}
            		else if(room.getIdRoom().equalsIgnoreCase("Ocean")) {
            			//Interazione Oceano
            		}
            		else {
            		 System.out.println(npc.getIdCharacter().toUpperCase()+": I have nothing to say");
            		}
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
            System.out.println("Thank you for playing!");
            return false;
        case "save":
        	saveGame();
        	System.out.println("GAME SAVED");
        	printStatusGame();
        	break;
        default:
            System.out.println("Invalid command, try again.");
            displayCommands();
    }
		return true;
	}

	private boolean process2WordCommand(Room currentRoom,String words[]) {
		switch(words[0]) {
        case "move":
        	if(words[1].equalsIgnoreCase("north")||words[1].equalsIgnoreCase("south")||words[1].equalsIgnoreCase("west")||words[1].equalsIgnoreCase("east")) {
            String direction = words[1];
            Room newRoom = currentRoom.returnRoom(direction);
           
            if (newRoom != null) {
            	 NPC npc = newRoom.getNPC();
            	story.randomEvent(player, newRoom);
                player.movePlayer(newRoom);
                // System.out.println("You moved to " + newRoom.getIdRoom());
                if(newRoom.getIdRoom().equalsIgnoreCase("Forest")&&newRoom.getNPC().getHide()==true) {
                	
                	
                	story.secondInteractionForest( player, npc);
                	
                }
            } 
            else {
                System.out.println("You can't go that way.");
            }
        	}	
        break;
        case "use":
        	Item x = player.getInventory().getItemById(words[1]);
        	if(player.getInventory().findItem(x)==true){
        		x.useItem(player);
        	}
		else {
			System.out.println("You don't have that item");
		}
		break;
        default:
            System.out.println("Invalid command, try again.");
            displayCommands();
    	}
		return true;
	}
	
	private boolean process3WordCommand(Room currentRoom,String words[]) {
    	try {
            switch(words[0]) {
            case "pickup":
                String itemNameToPick = words[1];
                Item itemFound= player.getLocation().getItemById(itemNameToPick);
                	if(itemFound!=null) {
                        int quantityToPick = Integer.parseInt(words[2]);
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
                String itemNameToDrop = words[1];
                Item itemDropped = player.getInventory().getItemById(itemNameToDrop);
                    if (itemDropped!=null) {
                        int quantityToDrop = Integer.parseInt(words[2]);
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
        	catch(ArrayIndexOutOfBoundsException e){
        		System.out.println("Invalid command");
                displayCommands();
        	}
        	catch(NumberFormatException e) {
        		System.out.println("Invalid number format. Please enter a valid number.");
                displayCommands();
        	}
		return true;
	}
	private static String[] splitCommand(String s) {				//metodo che data una stringa la divide in parole
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



