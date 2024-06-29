import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	 private Player player;
	    private Room initialRoom;
	    private Room newRoom;

	    @Before
	    public void setUp() {
	        player = new Player("Player test",100,10);
	        initialRoom = new Room("Initial Room");
	        newRoom = new Room("New Room");
	    }

	    @Test
	    public void testMovePlayer() {
	        player.movePlayer(initialRoom);
	        assertEquals(initialRoom, player.getLocation());
	        player.movePlayer(newRoom);
	        assertEquals(newRoom, player.getLocation());
	        assertEquals(initialRoom,player.getLastLocation());
	        assertTrue(initialRoom.getVisited()); 
	    }
	    

}
