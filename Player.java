
public class Player extends Character {
	
	private Room actualLocation;
	private Room previousLocation; 
	private Inventory inventory;
	private int mapPieces=0;
	final private  int nMap =4;							//costante per memorizzare i pezzi totali della mappa
	private Item weapon = null;
	private String[] map = new String[nMap];			//vettore per contenere i "pezzi" di mappa

	Player(String id, int mH,int d) {
		super(id, mH,d);
		previousLocation = null;
		actualLocation=null;
		inventory=new Inventory();
	}
	
	public void movePlayer(Room r) {			//sposta il giocatore in un'altra stanza
		if (actualLocation != null) { 
		previousLocation = actualLocation;
		previousLocation.setVisisted();
		}
		actualLocation = r;
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
	public void getMapString(String x,int s) {			//aggiunge un "pezzo" di mappa e aumenta di 1 il numero di pezzi di mappa del player
		map[s-1]=x+" ";
		mapPieces=mapPieces+1;
	}
	public boolean allMap(){						//ritorna TRUE se la mappa è completa
		return mapPieces>=nMap;
	}
	public int returnNMapPieces() {					//ritorna il numero di pezzi di mappa che ha il player
		return mapPieces;
	}
	public int returnNTMapPieces() {				//ritorna il numero totale dei pezzi di mappa
		return nMap;
	}
	public void showMap() {							//mostra la mappa
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
	public void setWeapon(Weapon x) {
		weapon =x;
		damage=x.getDamage();
	}
	public Item getWeapon() {
		return weapon;
	}
	public void printStatusPlayer() {									//Stampa le informazioni relative al player
		System.out.println("MAP PIECE FOUND: "+mapPieces+"/"+nMap);
		System.out.println("BIOME: "+actualLocation.getIdRoom());
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
