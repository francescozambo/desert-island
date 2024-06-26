
public class Player extends Character {
	
	private Room actualLocation;
	private Room previousLocation; 
	private Inventory inventory;
	

	Player(String id, int mH,int d) {
		super(id, mH,d);
		previousLocation = null;
		inventory=new Inventory();
	}
	
	public void movePlayer(Room r) {			//sposta il giocatore in un'altra stanza
	if (actualLocation != null) {
		previousLocation = actualLocation;
	}
	actualLocation = r;
		System.out.println("You've arrived in the: "+actualLocation.getIdRoom());
	}
	
	public Room getLocation() {
		return actualLocation;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public Room getLastLocation() {
		return previousLocation;
	}

}
