package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Item;
import main.Room;
public class RoomTest {
	private Room testRoom; 
	private Room north;
	private Room south;
	private Room west;
	private Room east;
	@BeforeEach
	public void setUp() {
		 testRoom = new Room("test room");
		 north = new Room("north");
		 south = new Room("south room");
		 west = new Room("east room");
		 east = new Room("west room");
		 
	}
	
	@Test
	public void testConnectRoom() {
		testRoom.connectRoom(north, south, east, west);
		assertEquals(testRoom.returnRoom("north"),north);
		assertEquals(testRoom.returnRoom("south"),south);
		assertEquals(testRoom.returnRoom("east"),east);
		assertEquals(testRoom.returnRoom("west"),west);
	}
	
	private Room room;
	private Item item1;
	private Item item2;

    @BeforeEach
    public void setUp1() {
        room = new Room("Test Room"); 
        item1 = new Item("Item1", 1, true);
        item2 = new Item("Item2", 4, true);
        
    }

    @Test
    public void testRemoveExistingItem() {
    	 room.getRoomItems().put(item1, 2);
         room.getRoomItems().put(item2, 3);
        Item itemToRemove = item1;
        int initialSize = room.getRoomItems().size();
        room.removeItem(itemToRemove, 1); 
        assertEquals(1, (int)room.getRoomItems().get(itemToRemove));
        itemToRemove = item2;
        room.removeItem(itemToRemove, 3); 
        assertEquals(initialSize - 1, room.getRoomItems().size()); 
    }
    @Test
    public void testRemoveNotExistingItem() {
    	 room.getRoomItems().put(item1, 2);
         room.getRoomItems().put(item2, 3);
    	int initialSize = room.getRoomItems().size();
        Item nonExistingItem = new Item("NonExistingItem", 1, true); 
        initialSize = room.getRoomItems().size();
        room.removeItem(nonExistingItem, 1);
        assertEquals(initialSize, room.getRoomItems().size());
    }
    @Test
    public void testAddExistingItem() {
    	int initialItemQuantity = room.getItemQuantity(item1);
    	room.addItem(item1, 1);
    	assertEquals(room.getItemQuantity(item1),initialItemQuantity+1);
    	
    }
    @Test
    public void testAddNotExistingItem() {
    	room=new Room("Test");
    	assertFalse(room.findItem(item1));
    	room.addItem(item1, 1);
    	assertTrue(room.findItem(item1));
    	assertEquals(room.getItemQuantity(item1),1);
    }
    @BeforeEach
    	public void setup2() {
    	room = new Room("TestRoom");
    	item1 = new Item("Item1", 1, true);
        item2 = new Item("Item2", 4, true);
    	room.addItem(item1, 1);
    }
    	
    
    @Test
    public void testFindItemPresent(){
    	
    	assertTrue(room.findItem(item1));
     }
    public void testFindItem(){
    	;
    	assertFalse(room.findItem(item2));
     }
}
