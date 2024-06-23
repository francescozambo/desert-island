import java.util.HashMap;
//import java.util.Iterator;

public class Room {
	private String idRoom;
	private HashMap<Item,Integer> roomItem; 
	private HashMap<String, Room> connectedRoom;
	private NPC npc;

	Room(String name){
		idRoom = name;
		roomItem = new HashMap<Item,Integer>();
		connectedRoom = new HashMap<String, Room>();
	}

	public String getIdRoom() {
        return idRoom;
    }

	public HashMap<Item, Integer> getRoomItems() {
        return roomItem;
    }

	public boolean findItem(Item x) {
		return roomItem.containsKey(x);
	}

	public void addItem(Item x, int quantity) {
		
		roomItem.put(x, roomItem.getOrDefault(x, 0) + quantity);
		//getOrDefault metodo che viene utilizzato per ottenere il 
		//valore associato a una chiave specificata, oppure un valore di default se la chiave non è presente nella mappa
		/*
		if(findItem(x)==false)
			roomItem.put(x,quantity);
		else
			roomItem.put(x,roomItem.get(x)+quantity);
		*/
	}
	public void removeItem(Item x, int quantity) {
		if (findItem(x)) {
			int currentQuantity = roomItem.get(x);
			if (currentQuantity <= quantity) {
				roomItem.remove(x);
			} else {
				roomItem.put(x, currentQuantity - quantity);
			}
		}																	
	}

	public void showItems() {
		/*Iterator<Item> it = roomItem.keySet().iterator();
		while(it.hasNext()) {
			Item key = it.next();
			int value = roomItem.get(key);
			System.out.println("OGGETTI NELLA STANZA:");
			System.out.println(key.getidItem()+": "+value);
		}
		*/
		System.out.println("OGGETTI NELLA STANZA:");
		for (Item key : roomItem.keySet()) {
			int value = roomItem.get(key);
			System.out.println(key.getidItem() + ": " + value);
		}

		
		
	}
	public void connectRoom(Room n, Room s, Room e, Room w){
		if (n != null) connectedRoom.put("north", n);
		if (s != null) connectedRoom.put("south", s);
		if (e != null) connectedRoom.put("east", e);
		if (w != null) connectedRoom.put("west", w);
	}
	public Room returnRoom(String s) {
		return connectedRoom.get(s);
	}
	public void setNPC(String id, int mH, int d, String mapPiece) {
		npc = new NPC(id,mH,d,mapPiece);
	}
	public NPC getNPC() {
		return npc;
	}
}