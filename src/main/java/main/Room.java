package main;
import java.io.Serializable;
import java.util.HashMap;
//import java.util.Iterator;

public class Room implements Serializable{
	private String idRoom;
	private HashMap<Item,Integer> roomItem; 
	private HashMap<String, Room> connectedRoom;
	private NPC npc;
	private boolean visited;
	public Room(String name){
		visited=false;
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
	public int getItemQuantity(Item x) {
		return roomItem.get(x);
	}
	public Item getItemById(String id) {				//restituisce l'oggetto a partire dal suo id, null altrimenti
        for (Item item : roomItem.keySet()) {
            if (item.getidItem().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null; 
    }
	
	
	/*/public boolean findItemById(String id) {
		 for (Item item : roomItem.keySet()) {
	            if (item.getidItem().equalsIgnoreCase(id))
	                return true;
		 }
		return false;
	}
/*/
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

	public void showItems() {										//stampa gli oggetti nella stanza
		/*Iterator<Item> it = roomItem.keySet().iterator();
		while(it.hasNext()) {
			Item key = it.next();
			int value = roomItem.get(key);
			System.out.println("OGGETTI NELLA STANZA:");
			System.out.println(key.getidItem()+": "+value);
		}
		*/
		if(roomItem.isEmpty()) {
			System.out.println("There is nothing in this room");
		}
		else {
		System.out.println("OGGETTI NELLA STANZA:");
		for (Item key : roomItem.keySet()) {
			int value = roomItem.get(key);
			System.out.println(key.getidItem() + ": " + value);
		}
		}
	}
	public void connectRoom(Room n, Room s, Room e, Room w){		//metodo per connettere le varie stanze
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
	public boolean getVisited() {
		return visited;
	}
	public void setVisisted() {
		visited=true;
	}
}