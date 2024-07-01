package main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesertIsland implements Serializable {
	
	// Create rooms
	private Room beach;
    private Room forest;
    private Room cave;
    private Room ocean;
    private Room transporter;  // New transporter room

	private Item crab;
    private Item steak;
    private Item stone;
    private Item dune;
    private Item showel;

    private List<Room> allRooms;  // List to store all rooms
    
    
    DesertIsland(){
    	beach = new Room("Beach");
    	forest = new Room("Forest");
    	cave = new Room("Cave");
    	ocean = new Room("Ocean");
        transporter = new Room("Transporter");  // Initialize transporter room
        
        crab = new Food ("Crab", 1, true,5);
        steak = new Food("Steak", 3, true,10);
    	
        dune = new Item("Dune");
    	stone = new Item("Stone", 5, true);
    	showel = new Item("Showel",8,true);

        allRooms = new ArrayList<>();
        allRooms.add(beach);
        allRooms.add(forest);
        allRooms.add(cave);
        allRooms.add(ocean);
        allRooms.add(transporter);  // Add transporter to the list
    	
        connectRoom();
    	addRoomItem();
    	setRoomNPC();
    }
    
    private void connectRoom() {
    beach.connectRoom(ocean, forest, null, transporter);
    forest.connectRoom(beach, cave, null, null);
    cave.connectRoom(forest, null, transporter, null);
    ocean.connectRoom(null, beach, null, null);
    transporter.connectRoom(null, null, beach, cave);  
    }

   private void addRoomItem(){
    beach.addItem(crab,3);
    beach.addItem(dune, 4);
    forest.addItem(steak, 5);
    cave.addItem(stone, 6);
    beach.addItem(showel, 1);
   }
   private void setRoomNPC() {
	   beach.setNPC("Old Sailor", 50, 5, "dream");
	   forest.setNPC("Hunter", 70, 7, "wake");
	   cave.setNPC("Miner", 60, 6, "up it");
	   ocean.setNPC("Siren", 80, 8, "is a");
   }
   public Room getStartRoom() {
	   return beach;
   }
   public NPC getRoomNPC(Room x){
	   return x.getNPC();
	   
   }

   public Room getTransporterRoom() {
    return transporter;
}

    public Room getRandomRoom() {
        Random rand = new Random();
        return allRooms.get(rand.nextInt(allRooms.size()));
    }

}
