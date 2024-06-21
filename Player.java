
public class Player extends Character {
	
	private Room actualLocation;  
	private Inventory inventory;
	

	Player(String id, int mH,int d) {
		super(id, mH,d);
	}
	
	public void movePlayer(Room r) {//sposta il giocatore in un'altra room
		actualLocation = r;
		System.out.println("You've arrived in the: "+actualLocation.getIdRoom());
	}
	
	public Room getLocation() {
		return actualLocation;
	}
	public void attack(NPC n) {
		
	}

}
