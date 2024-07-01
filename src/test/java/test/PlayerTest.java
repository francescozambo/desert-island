package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Player;
import main.Room;
import main.Weapon;
import main.Food;
import main.Item;


public class PlayerTest {

	 	private Player player;
	    private Room initialRoom;
	    private Room newRoom;
	    private String mapString="Test";
	    private int mapPosition=1;
	    private Item item;
	    private Food food;
	    private Weapon weapon;
	    @BeforeEach
	    public void setUp() {
	        player = new Player("Player test",100,10);
	        initialRoom = new Room("Initial Room");
	        newRoom = new Room("New Room");
	        weapon = new Weapon("Knife",10,true,20);
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
	    public void testThrowStoneWith0Stone(){
	    	
	       assertFalse(player.getInventory().findItem(item)); //caso 0 pietre nell' inventario
		   player.throwStone();
		   assertFalse(player.getInventory().findItem(item));
	      
	       
	      
	    }
	    public void testThrowStoneWithOneStone(){
	    	
		        
		       player.getInventory().addItem(item, 1);   //caso 1 pietra nell'inventario
		       player.throwStone();
		       assertFalse(player.getInventory().findItem(item));  
		       
		      
		       
		      
		    }
	    public void testThrowStoneWithMultipleStone(){
	    
		       
		       player.getInventory().addItem(item, 4); //caso pietre nell'inventario >1
		       player.throwStone();
		       assertEquals(3, player.getInventory().getQuantity(item));
		       
		      
		    }
	    
	    @BeforeEach
	    public void setUp2() {
	        player = new Player("testCharacter", 100, 10);
	        food = new Food("steak", 1, true, 6);
	    }

	    @Test
	    public void testCureWithFood() {							//caso si abbia cibo nell'inventario;
	        player.getInventory().addItem(food, 1);
	        int initialHealth = player.getHealth();
	        player.cure();
	        if(initialHealth + food.getHp()>=100) {
	        assertEquals(player.getHealth(), player.getMaxHealth());
	        assertFalse(player.getInventory().findItem(food));
	        }
	        else {
	        	 assertEquals(initialHealth + food.getHp(), player.getHealth());
	 	        assertFalse(player.getInventory().findItem(food));
	        }
	    }

	    @Test
	    public void testCureWithoutFood() {							//caso non si abbia cibo nell'inventario;
	        int initialHealth = player.getHealth();
	        player.cure();
	        assertEquals(initialHealth, player.getHealth());
	       
	    }
	    @Test
	    public void testSetWeapon() {
	    	player.setWeapon(weapon);
	    	assertEquals(player.getDamage(),weapon.getDamage());
	    }
	    
	}


