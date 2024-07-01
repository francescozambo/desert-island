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
				+ "You don't know how you ended up here, but you feel that you have to leave as soon as possible.\nExplore and interact to try to figure out how to escape\n");
	}
	
	public void firtsInteractionBeach(DesertIsland ds,Player player, NPC npc) {
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
						System.out.println(npc.getIdCharacter().toUpperCase()+": We found the compass...thanks for your help, i'll give you my knife");
						Item i = new Weapon("Knife",4,true,20);
						player.getLocation().addItem(i,1);
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
	public void firstInteractionForest(DesertIsland ds,Player player, NPC npc){
		System.out.println(npc.getIdCharacter().toUpperCase()+": Who it is... Who are you? Be careful in this forest! A mysterous creature is assaulting our villages\nWe call it the Feralis..."
				+ "It's hearing is excellent, \nI suggest you picking up stones so you can throw them and distract it if you unfortunately come across it\nBe careful");
		npc.setHide();
		
	}
	public void secondInteractionForest(DesertIsland ds,Player player, NPC npc) {
		int event = generateRandomNumber(3);
		if(event == 3) {
			Mob mob = new Mob("Feralis",200,200);
			System.out.println("You encountered Feralis");
			Combact combact = new Combact(mob, player);
			combact.start();
			if(combact.getWin()==true) {
				npc.setInteract();
			}
		}
		else {
			System.out.println(npc.getIdCharacter().toUpperCase()+": No feralis in sight");
			
		}
	}
	public void thirdInteractionForest(DesertIsland ds,Player player, NPC npc){
		System.out.println(npc.getIdCharacter().toUpperCase()+": Thank you so much for killing the Feralis!");
		
	}
	public void firstInteractionCave(DesertIsland ds,Player player, NPC npc){
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
