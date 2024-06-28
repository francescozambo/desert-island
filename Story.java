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
						Item i = new Item("Knife",4,true);
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
		
	}
	public void firstInteractionCave(DesertIsland ds,Player player, NPC npc){
		int riddleGuessed=0;
		int tries=0;
		System.out.println(npc.getIdCharacter().toUpperCase()+": Hello "+player.getIdCharacter()+"!\nYeah i know your name, even though I'm down here all day, I know"
				+ "everything about this island\nI've heard you are looking for a map piece...I have one, i can give it to you if you play riddles with me\nLet's start or quit (typer start or quit)");
		String answer=in.nextLine();
		switch(answer) {
		case "quit":
			System.out.println(npc.getIdCharacter().toUpperCase()+": As you want, but I think we will see each other soon");
			break;
		case "start":
			break;
		default:
			System.out.println(npc.getIdCharacter().toUpperCase()+": Speak clear!");
			break;
		}
	}
	public void firstInteractionOcean(DesertIsland ds,Player player, NPC npc){
		
	}
	private int  generateRandomNumber(int x) {					//genera numeri random da 1 a x
		int max=x;
		int number=random.nextInt((max - 1) + 1) +1;
		return number;
	}
	
}
