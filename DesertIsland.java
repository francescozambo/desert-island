
public class DesertIsland {
	
	// Create rooms
    Room beach;
    Room forest;
    Room cave;
    Room ocean;
	
  
    Item food;
    Item wood;
    Item stone;
    
    
    
    DesertIsland(){
    	beach = new Room("Beach");
    	forest = new Room("Forest");
    	cave = new Room("Cave");
    	ocean = new Room("Ocean");
    	
    	food = new Item("Food", 1, true);
    	wood = new Item("Wood", 3, true);
    	stone = new Item("Stone", 5, true);
    	
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
    beach.addItem(food,3);
    forest.addItem(wood, 5);
    cave.addItem(stone, 2);
   }
   private void setRoomNPC() {
	   beach.setNPC("Old Sailor", 50, 5, "Beach Map Piece");
	   forest.setNPC("Hunter", 70, 7, "Forest Map Piece");
	   cave.setNPC("Miner", 60, 6, "Cave Map Piece");
	   ocean.setNPC("Fisherman", 80, 8, "Ocean Map Piece");
   }
   public Room getStartRoom() {
	   return beach;
   }
   public NPC getRoomNPC(Room x){
	   return x.getNPC();
	   
   }

}
