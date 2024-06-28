
public class Player extends Character {
	
	private Room actualLocation;
	private Room previousLocation; 
	private Inventory inventory;
	private int mapPieces=0;
	final private  int nMap =4;
	private Item weapon = null;
	private String[] map = new String[nMap];

	Player(String id, int mH,int d) {
		super(id, mH,d);
		previousLocation = null;
		inventory=new Inventory();
	}
	
	public void movePlayer(Room r) {			//sposta il giocatore in un'altra stanza
	if (actualLocation != null) {
		previousLocation = actualLocation;
		previousLocation.setVisisted();
	}
	actualLocation = r;
		//System.out.println("You've arrived in the: "+actualLocation.getIdRoom());
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
	public void getMapString(String x,int s) {
		map[s-1]=x+" ";
		mapPieces=mapPieces+1;
	}
	public boolean allMap(){
		return mapPieces>=nMap;
	}
	public int returnNMapPieces() {
		return mapPieces;
	}
	public int returnNTMapPieces() {
		return nMap;
	}
	public void showMap() {
		if(mapPieces>0) {
		for(int i=0; i<nMap; i++) {
			if(map[i]!=null)
				System.out.print(map[i]+" ");
			else
				System.out.print("------");
		 }
		}
		else {
			System.out.println("You don't have any map piece");
		}
	}
	public void setWeapon(Item x) {
		
	}
	public Item getWeapon() {
		return weapon;
	}
	public void printStatusPlayer() {
		System.out.println("MAP PIECE FOUND: "+mapPieces+"/"+nMap);
		System.out.println("HEALTH: "+getHealth()+"/"+getMaxHealth());
		System.out.println("DAMAGE: "+getDamage());
		if(weapon!=null) {
			System.out.println("WEAPON: "+getWeapon().getidItem());
		}
		else {
			System.out.println("WEAPON: Bare hands");
		}
	}

}
