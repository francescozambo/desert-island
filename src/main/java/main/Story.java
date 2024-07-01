package main;
import java.util.Random;

import java.util.Scanner;

public class Story {
	Random random;
	Scanner in = new Scanner(System.in);
	Story(){
		random = new Random();
		
	}
	public void beginnin(String x) {
		System.out.println("Hello "+x+"! \n\nYou are an adventurer who has woken up on the beach of an island that seems abandoned...\n"
				+ "You don't know how you ended up here, but you feel that you have to leave as soon as possible.\nExplore and interact to try to figure out how to escape\n"
				+ "\t   |\n" + //
										"         \\ _ /\r\n" + //
										"       -= (_) =-\r\n" + //
										"         /   \\         _\\/_\r\n" + //
										"           |           //o\\  _\\/_\r\n" + //
										"    _____ _ __ __ ____ _ | __/o\\\\ _\r\n" + //
										"  =-=-_-__=_-= _=_=-=_,-'|\"'\"\"-|-,_\r\n" + //
										"   =- _=-=- -_=-=_,-\"          |\r\n" + //
										"     =- =- -=.--\""
										+ "\n"
				);

	}
	
	public void firtsInteractionBeach(Player player, NPC npc) {
		if(player.getInventory().findItem(player.getInventory().getItemById("Showel"))==true) {
			System.out.println(npc.getIdCharacter().toUpperCase()+": Hello stranger! I think i lost my compass...I must had left it somewhere here and was covered by the sand\n"
					+ "Can you help me find it? We just need to dig up these dunes...Type dig to use the showel");
			
			int t=0;
			int map=0;
			int compass=0;
			do {																//rende la "ricerca" randomizzata in modo che a ogni giocata sia diversa;
				map=generateRandomNumber(player.getLocation().getItemQuantity(player.getLocation().getItemById("Dune")));
				compass=generateRandomNumber(player.getLocation().getItemQuantity(player.getLocation().getItemById("Dune")));
			}
			while((compass==map));
			while(player.getLocation().findItem(player.getLocation().getItemById("Dune"))==true){
				String x = in.nextLine();
				if(x.equalsIgnoreCase("dig")) {
					t=t+1;
					player.getLocation().removeItem(player.getLocation().getItemById("Dune"), 1);
					if(t==compass) {
						System.out.println(npc.getIdCharacter().toUpperCase()+": We found the compass...thanks for your help, i'll give you my knife if you have space in your backpack");
						Item i = new Weapon("Knife",4,true,20);

						if (player.getInventory().isFull(i.getWeight())) {
                            player.getInventory().addItem(i,1);
                        } else {
                            player.getLocation().addItem(i, 1);
                        }
						//player.getLocation().addItem(i,1);
					}
					else if(t==map) {
						player.getMapString(npc.interact(),4);
						System.out.println("YOU FOUND A MAP PIECE!...It says: "+npc.interact()+"\nYou have now "
								+player.returnNMapPieces()+"/"+player.returnNTMapPieces()+" Map pieces\n");
						
					}
					else {
						System.out.println(npc.getIdCharacter().toUpperCase()+": Nothing...keep up");
					}
				}
				else if(x.equalsIgnoreCase("save")){
					System.out.println("You can not save now...help the old sailor first");
				}
				else {
					System.out.println(npc.getIdCharacter().toUpperCase()+": Help me, Lazybones!");
				}
			}
			System.out.println(npc.getIdCharacter().toUpperCase()+": We dug up all the sand, thank you for helping me!");
			npc.setInteract();
		}
		else {
			System.out.println(npc.getIdCharacter().toUpperCase()+": Maybe you should come back when you have a showel!");
		}
		
	}
	public void firstInteractionForest(Player player, NPC npc){
		System.out.println(npc.getIdCharacter().toUpperCase()+": Who it is... Who are you? Be careful in this forest! A mysterous creature is assaulting our villages\nWe call it the Feralis..."
				+ "It's hearing is excellent, \nI suggest you picking up stones so you can throw them and distract it if you unfortunately come across it\nBe careful");
		npc.setHide();
		
	}
	public void secondInteractionForest(Player player, NPC npc) {
		int event = generateRandomNumber(3);
		if(event == 3) {
			Mob mob = new Mob("Feralis",50,20);
			System.out.println("You encountered Feralis");
			Combact combact = new Combact(mob, player);
			combact.start();
			if(combact.getWin()==true) {
				System.out.println(npc.getIdCharacter() +": The villages are safe..here, take this map piece as a thanl you gift\nIt says: "+npc.interact()+"\nYou have now "
						+player.returnNMapPieces()+"/"+player.returnNTMapPieces()+" Map pieces\n");
				npc.setInteract();
			}
		}
		else {
			System.out.println(npc.getIdCharacter().toUpperCase()+": No feralis in sight");
			
		}
	}
	public void thirdInteractionForest(Player player, NPC npc){
		System.out.println(npc.getIdCharacter().toUpperCase()+": Thank you so much for killing the Feralis!");
		
	}
	public void firstInteractionCave(Player player, NPC npc){
		String answer="";
		int riddleGuessed=0;
		System.out.println(npc.getIdCharacter().toUpperCase()+": Hello "+player.getIdCharacter()+"!\nYeah i know your name, even though I'm down here all day, I know"
				+ " everything about this island\nI've heard you are looking for a map piece...I have one, i can give it to you if you play riddles with me\nLet's start or quit (typer start or quit)");
		while(!answer.equalsIgnoreCase("quit")&&npc.getInteract()==false) {
		answer=in.nextLine();
		switch(answer) {
		case "quit":
			System.out.println(npc.getIdCharacter().toUpperCase()+": As you want, but I think we will see each other soon");
			break;
		case "start": 
			System.out.println(npc.getIdCharacter().toUpperCase()+": Let's start!");
			for(int i=1;i<=3;i++) {
				int tries=0;
				System.out.println(npc.getIdCharacter().toUpperCase()+": Here is the "+i+"° riddle: "+getRiddle(i-1));
				String guess="";
				do {
					guess=in.nextLine();
					if(guess.equalsIgnoreCase(getRiddleAnswer(i-1))) {
						riddleGuessed=riddleGuessed+1;
						System.out.println(npc.getIdCharacter().toUpperCase()+": That's correct!");
						break;
					}
					else {
						tries=tries+1;
						System.out.println(npc.getIdCharacter().toUpperCase()+": Wrong answer");
						if(tries>=3&&i!=3) {
							System.out.println(npc.getIdCharacter().toUpperCase()+": Ok, maybe this riddle is too difficult, let's do another one");
							break;
						}
					}
				}
				while(tries<3);
			}
			if(riddleGuessed<=2) {
				System.out.println(npc.getIdCharacter().toUpperCase()+": Ok, riddles are not your forte\nI'm still going to give you the map piece, I don't need it"
						+ " i know this island by heart\n");
				npc.setInteract();
				player.getMapString(npc.interact(),2);
				System.out.println("YOU WERE GIVEN A MAP PIECE!...It says: "+npc.interact()+"\nYou have now "
						+player.returnNMapPieces()+"/"+player.returnNTMapPieces()+" Map pieces\n");
				break;
			}
			else {
				System.out.println(npc.getIdCharacter().toUpperCase()+": You are good at riddles! I'm impressed You deserved the map piece!");
				npc.setInteract();
				player.getMapString(npc.interact(),2);
				System.out.println("YOU WERE GIVEN A MAP PIECE!...It says: "+npc.interact()+"\nYou have now "
						+player.returnNMapPieces()+"/"+player.returnNTMapPieces()+" Map pieces\n");
				break;
			}
			
		default:
			System.out.println(npc.getIdCharacter().toUpperCase()+": Speak clear!");
			break;
		}
		}
	}
	public void firstInteractionOcean(DesertIsland ds,Player player, NPC npc){

		System.out.println("\nAs you explore the beach, you notice a strange glimmer in the water. As you get closer, a beautiful siren appears.");
        System.out.println(npc.getIdCharacter().toUpperCase() + ": Greetings, brave adventurer. I am the guardian of these waters. I have seen your struggles and I offer my aid.\n"
                + "I need your help to recover three magical pearls hidden in the depths of the ocean. In return, I will give you a piece of the map that leads to a great treasure.\n"
                + "Are you ready to accept this quest? (type yes or no)");
        
        String response = in.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("\n"+npc.getIdCharacter().toUpperCase() + ": Excellent! The first pearl is hidden in a sunken shipwreck. Be careful, as the waters are treacherous.");
            // Implementazione della missione per trovare la prima perla
			findFirstPearl(ds, player, npc);

        } else {
            System.out.println(npc.getIdCharacter().toUpperCase() + ": I understand. Return to me when you are ready to embark on this quest.");
        }
		
	}

	public void findFirstPearl(DesertIsland ds, Player player, NPC npc) {
        System.out.println("You dive into the ocean, swimming towards the shipwreck. As you explore the wreck, you find a chest locked with a riddle.\n");
        System.out.println("Riddle: I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?");
        
        String guess = "";
        int tries = 0;
        while (!guess.equalsIgnoreCase("Echo") && tries < 3) {
            guess = in.nextLine();
            if (guess.equalsIgnoreCase("Echo")) {
                System.out.println("The chest opens, revealing the first magical pearl!");
                player.getInventory().addItem(new Item("Pearl1", 1, true),1);
                findSecondPearl(ds, player, npc);
                return;
            } else {
                tries++;
                System.out.println("Incorrect. Try again.");
            }
        }
        System.out.println("You couldn't solve the riddle and the chest remains locked. Return to the surface and speak with the siren for guidance.");
    }
    
    public void findSecondPearl(DesertIsland ds, Player player, NPC npc) {
        System.out.println("\n"+npc.getIdCharacter().toUpperCase() + ": You have found the first pearl! The second pearl is guarded by a giant octopus in an underwater cave. Good luck!");
        System.out.println("You swim into the cave, and the giant octopus attacks. Defend yourself! (type attack)");
        
        String action = "";
        while (!action.equalsIgnoreCase("attack")) {
            action = in.nextLine();
            if (action.equalsIgnoreCase("attack")) {
                System.out.println("You bravely scare the octopus and it retreats. You find the second pearl hidden in the cave.");
                player.getInventory().addItem(new Item("Pearl2", 1, true),1);
                findThirdPearl(ds, player, npc);
                return;
            } else {
                System.out.println("You hesitate, the octopus is getting closer!");
            }
        }
    }
    
    public void findThirdPearl(DesertIsland ds, Player player, NPC npc) {
        System.out.println("\n"+npc.getIdCharacter().toUpperCase() + ": You have found the second pearl! The final pearl is in the depths of a coral reef. Beware of the poisonous sea urchins.");
        System.out.println("You carefully navigate the reef, avoiding the sea urchins. After a long search, you spot the final pearl glowing among the corals. Grab it!");
        
        String action = "";
        while (!action.equalsIgnoreCase("grab")) {
            action = in.nextLine();
            if (action.equalsIgnoreCase("grab")) {
                System.out.println("You successfully grab the final pearl!");
                player.getInventory().addItem(new Item("Pearl3", 1, true),1);
                completePearlQuest(ds, player, npc);
                return;
            } else {
				Mob mob = new Mob("Sea urchins",0,5);
				mob.attack(player);
				System.out.println("Be careful, the sea urchins are dangerous!");
            }
        }
    }
    
    public void completePearlQuest(DesertIsland ds, Player player, NPC npc) {
        System.out.println(npc.getIdCharacter().toUpperCase() + ": You have found all three pearls! As promised, here is a piece of the map.");
        npc.setInteract();
        player.getMapString(npc.interact(), 3);
        System.out.println("YOU WERE GIVEN A MAP PIECE!...It says: " + npc.interact() + "\nYou have now "
                + player.returnNMapPieces() + "/" + player.returnNTMapPieces() + " Map pieces\n");
    }

	public void randomEvent(Player player, Room room) {
		String idRoom = room.getIdRoom();
		int number=generateRandomNumber(5);
		int hp=generateRandomNumber(15)+6;
		if(number==3||number==2) {		
		switch(idRoom) {
		case "Ocean":
			System.out.println("\nYou were attacked by a shark and lost a finger\nYou lost "+hp+" lifepoints\n");
			player.removeHealth(hp);
			break;
		case "Forest":
			System.out.println("\nAn unknown type of spider bite you and gave you an infection\nYou lost "+hp+" lifepoints\n");
			player.removeHealth(hp);
			break;
		case "Cave":
			System.out.println("\nYou fell in a rift and almost broke your neck\nYou lost "+hp+" lifepoints\n");
			player.removeHealth(hp);
			break;
		case "Beach":
			System.out.println("\nYou fell aspleep in the sun and got a sunstroke\nYou lost "+hp+" lifepoints\n");
			player.removeHealth(hp);
			break;
		default:
		throw new IllegalArgumentException(); 
		
		}
		}
	}
	private int generateRandomNumber(int x) {					//genera numeri random da 1 a x
		int max=x;
		int number=random.nextInt((max - 1) + 1) +1;
		return number;
	}
	private String getRiddle(int i) {
		String[] riddles=new String[3];
		riddles[0]="It cannot be seen, cannot be felt,\n"
				+ "Cannot be heard, cannot be smelt.\n"
				+ "It lies behind stars and under hills,\n"
				+ "And empty holes it fills.\n"
				+ "It comes out first and follows after,\n"
				+ "Ends life, kills laughter.";
		riddles[1]="Voiceless it cries,\n"
				+ "Wingless flutters,\n"
				+ "Toothless bites,\n"
				+ "Mouthless mutters. \n";
		riddles[2]="This thing all things devours;\n"
				+ "Birds, beasts, trees, flowers;\n"
				+ "Gnaws iron, bites steel;\n"
				+ "Grinds hard stones to meal;\n"
				+ "Slays king, ruins town,\n"
				+ "And beats mountain down. \n"
				+ "";
		return riddles[i];
	}
	private String getRiddleAnswer(int i){
		String[] riddles=new String[3];
		riddles[0]="Dark";
		riddles[1]="Wind";
		riddles[2]="Time";
		return riddles[i];
	}
}
