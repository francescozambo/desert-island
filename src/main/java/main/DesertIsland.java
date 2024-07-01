package main;
import java.io.Serializable;

public class DesertIsland implements Serializable {
	
	// Create rooms
	private Room beach;
    private Room forest;
    private Room cave;
    private Room ocean;
	private Item crab;
    private Item steak;
    private Item stone;
    private Item dune;
    private Item showel;
    
    
    DesertIsland(){
    	beach = new Room("Beach");
    	forest = new Room("Forest");
    	cave = new Room("Cave");
    	ocean = new Room("Ocean");
    	dune = new Item("Dune");
    	crab = new Food ("Crab", 1, true,5);
    	steak = new Food("Steak", 5, true,10);
    	stone = new Item("Stone", 10, true);
    	showel = new Item("Showel",8,true);
    	connectRoom();
    	addRoomItem();
    	setRoomNPC();
    }
    
    private void connectRoom() {
    beach.connectRoom(ocean, forest, null, null);
    forest.connectRoom(beach, cave, null, null);
    cave.connectRoom(forest, null, null, null);
    ocean.connectRoom(null, beach, null, null);
    }

   private void addRoomItem(){
    beach.addItem(crab,4);
    beach.addItem(dune, 4);
    forest.addItem(steak, 5);
    cave.addItem(stone, 6);
    beach.addItem(showel, 1);
   }
   private void setRoomNPC() {
	   beach.setNPC("Old Sailor", 50, 5, "dream");
	   forest.setNPC("Hunter", 70, 7, "wake");
	   cave.setNPC("Miner", 60, 6, "up it");
	   ocean.setNPC("Fisherman", 80, 8, "is a");
   }
   public Room getStartRoom() {
	   return beach;
   }
   public NPC getRoomNPC(Room x){
	   return x.getNPC();
	   
   }

}
