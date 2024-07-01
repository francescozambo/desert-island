package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Player;
import main.Room;
import main.Inventory;
import main.Item;


public class PlayerTest {

	 	private Player player;
	    private Room initialRoom;
	    private Room newRoom;
	    private String mapString="Test";
	    private int mapPosition=1;
	    private Item item;
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
	    
	    @BeforeEach
	    public void setUp1() {
	        player = new Player("Player test",100,10);
	        item = new Item("Stone",1,true);
	    }
	    
	    @Test
	    public void testThrowStone(){
	    	
	       assertFalse(player.getInventory().findItem(item)); //caso 0 pietre nell' inventario
		   player.throwStone();
		   assertFalse(player.getInventory().findItem(item));
	        
	       player.getInventory().addItem(item, 1);   //caso 1 pietra nell'inventario
	       player.throwStone();
	       assertFalse(player.getInventory().findItem(item));  
	       
	       player.getInventory().addItem(item, 4); //caso pietre nell'inventario >1
	       player.throwStone();
	       assertEquals(3, player.getInventory().getQuantity(item));
	       
	      
	    }
	    

}
