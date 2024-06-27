import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Story {
	Story(){
		
	}
	public void beginnin(String x) {
		System.out.println("Hello "+x+"! \nYou are an adventurer who has woken up on the beach of an island that seems abandoned...\n"
				+ "You don't know how you ended up here, but you feel that you have to leave as soon as possible.\nExplore and interact to try to figure out how to escape\n");
	}
	public void seaCreatureDialogue() {
		System.out.println("Be careful, be careful...this beach is not safe. A sea creature just eat my boat and i think it killed the fisherman\nIt hides in the deep sea, if you help me killing him I'll give you a reward");
	}
	
	public void firtsInteractionBeach(DesertIsland ds,Player player, NPC npc) {
		if(player.getInventory().findItem(player.getInventory().getItemById("Showel"))==true) {
			System.out.println("OLD SAILOR: Hello stranger! I think i lost my compass...I must had left it somewhere here and was covered by the sand\n"
					+ "Can you help me find it? We just need to dig up these dunes...Type dig to use the showel");
			Scanner in = new Scanner(System.in);
			int y=player.getLocation().getItemQuantity(player.getLocation().getItemById("Dune"));
			int t=0;
			while(player.getLocation().findItem(player.getLocation().getItemById("Dune"))==true){
				String x = in.nextLine();
				if(x.equalsIgnoreCase("dig")) {
					t=t+1;
					player.getLocation().removeItem(player.getLocation().getItemById("Dune"), 1);
					if(t==y/2) {
						System.out.println("OLD SAILOR: We found the compass...thanks for your help, i'll give you my knife");
						Item i = new Item("Knife",4,true);
						player.getLocation().addItem(i,1);
					}
					else if(t==(y/2+2)) {
						player.getMapString(npc.interact(),4);
						System.out.println("YOU FOUND A MAP PIECE!...It says: "+npc.interact()+"\nYou have now "
								+player.returnNMapPieces()+"/"+player.returnNTMapPieces()+"Map pieces\n");
						
					}
					else {
						System.out.println("OLD SAILOR: Nothing...keep up");
					}
				}
				else {
					System.out.println("OLD SAILOR: Help me, Lazybones!");
				}
			}
			System.out.println("OLD SAILOR: Thank you for helping me");
			npc.setInteract();
		}
		else {
			System.out.println("OLD SAILOR: Maybe you should come back when you have a showel");
		}
		
	}
}
