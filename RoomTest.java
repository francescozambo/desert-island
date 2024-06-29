import static org.junit.Assert.assertEquals;

import org.junit.*;

public class RoomTest {
	Room testRoom; 
	Room north;
	Room south;
	Room west;
	Room east;
	@Before
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

    @Before
    public void setUp1() {
        room = new Room("Test Room"); 
        Item item1 = new Item("Item1", 1, true);
        Item item2 = new Item("Item2", 4, true);
        room.getRoomItems().put(item1, 2);
        room.getRoomItems().put(item2, 3);
    }

    @Test
    public void testRemoveItem() {
        // Verifica la rimozione di un elemento esistente
        Item itemToRemove = new Item("Item1", 1, true);
        int initialSize = room.getRoomItems().size();
        room.removeItem(itemToRemove, 1); 
       //assertEquals(initialSize - 1, room.getRoomItems().size()); //HashMap deve essere diminuita di 1 come dimensione
        assertEquals(1, (int)room.getRoomItems().get(itemToRemove)); // Verifica che siano rimasti 1 elemento di item1 nella stanza

        // Verifica la rimozione di un elemento non esistente
        Item nonExistingItem = new Item("NonExistingItem", 1, true); 
        initialSize = room.getRoomItems().size();
        room.removeItem(nonExistingItem, 1); // Prova a rimuovere un elemento non presente
        assertEquals(initialSize, room.getRoomItems().size()); // Verifica che la dimensione della HashMap non è cambiata
    }

}
