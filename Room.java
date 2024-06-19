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
		if(roomItem.containsKey(x))
			return true;
		else 
			return false;
	}
	public void addItem(Item x, int quantity) {
		if(findItem(x)==false)
			roomItem.put(x,quantity);
		else
			roomItem.put(x,roomItem.get(x)+quantity);
		
	}
	public void removeItem(Item x, int quantity) {
		roomItem.put(x,roomItem.get(x)-quantity);
		
	}
	public void showItems() {
		Iterator<Item> it = roomItem.keySet().iterator();
		while(it.hasNext()) {
			Item key = it.next();
			int value = roomItem.get(key);
			System.out.println("OGGETTI NELLA STANZA:");
			System.out.println(key.getidItem()+": "+value);
		}
		
	}
	public void connectRoom(Room n, Room s, Room e, Room w){
		System.out.println("Inizio");
		connectedRoom.put("north", n);
		connectedRoom.put("south", s);
		connectedRoom.put("east", e);
		connectedRoom.put("west", w);
	}
	public Room returnRoom(String s) {
		Room x = connectedRoom.get(s);
		return x;
	}
}