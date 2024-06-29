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

}
