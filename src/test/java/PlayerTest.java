package src.test.java;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import src.main.java.Player;
import src.main.java.Room;

public class PlayerTest {

	 	private Player player;
	    private Room initialRoom;
	    private Room newRoom;
	    private String mapString="Test";
	    private int mapPosition=1;

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
	    public void getMapString() {		
			player.getMapString(mapString, mapPosition);
			assertEquals(player.getMap(mapPosition-1),mapString+" ");
		}
	    

}
