import java.util.HashMap;
import java.util.Iterator;

public class Room {
	private String idRoom;
	HashMap<Item,Integer> roomItem; 
	HashMap<String, Room>connectedRoom;
	Room(String name){
		idRoom = name;
		roomItem = new HashMap<Item,Integer>();
		connectedRoom = new HashMap<String, Room>();
		System.out.println("Fine costruttore");
	}
	public boolean findItem(Item x) {
		return roomItem.containsKey(x);
	}
	public void addItem(Item x, int quantity) {
		if(findItem(x)==false)
			roomItem.put(x,quantity);
		else
			roomItem.put(x,roomItem.get(x)+quantity);
		
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
		Iterator<Item> it = roomItem.keySet().iterator();
		while(it.hasNext()) {
			Item key = it.next();
			int value = roomItem.get(key);
			System.out.println("OGGETTI NELLA STANZA:");
			System.out.println(key.getidItem()+": "+value);
		}
		/*
		System.out.println("OGGETTI NELLA STANZA:");
		for (Item key : roomItem.keySet()) {
			int value = roomItem.get(key);
			System.out.println(key.getidItem() + ": " + value);
		}

		*/
		
	}
	public void connectRoom(Room n, Room s, Room e, Room w){
		System.out.println("Inizio");
		if (n != null) connectedRoom.put("north", n);
		if (s != null) connectedRoom.put("south", s);
		if (e != null) connectedRoom.put("east", e);
		if (w != null) connectedRoom.put("west", w);
	}
	public Room returnRoom(String s) {
		return connectedRoom.get(s);
	}
}