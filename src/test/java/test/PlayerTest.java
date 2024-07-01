package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Player;
import main.Room;


public class PlayerTest {

	 	private Player player;
	    private Room initialRoom;
	    private Room newRoom;
	    private String mapString="Test";
	    private int mapPosition=1;

	    @BeforeEach
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
	    @Test
	    public void getMapString() {		
			player.getMapString(mapString, mapPosition);
			assertEquals(player.getMap(mapPosition),mapString+" ");
		}
	    

}
