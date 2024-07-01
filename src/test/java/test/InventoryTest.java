package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Inventory;
import main.Item;

class InventoryTest {
	private Item item1;
	private Item item2;
	private Inventory inventory;
	@BeforeEach
	void setUp(){
		item1 = new Item("Test Item",3,true);
		item2 = new Item("test Item 2",4,true);
		inventory = new Inventory();
	}

	@Test
	void testFindItemExist() {
		inventory.addItem(item1, 2);
		assertTrue(inventory.findItem(item1));
		
	}
	@Test
	void testFindItemNotExist() {
		assertFalse(inventory.findItem(item1));
	}
	@Test
	 public void testRemoveExistingItem() {
		inventory.getBackpack().put(item1, 2);
		inventory.getBackpack().put(item2, 3);
       Item itemToRemove = item1;
       int initialSize = inventory.getBackpack().size();
       inventory.removeItem(itemToRemove, 1); 
       assertEquals(1, (int)inventory.getBackpack().get(itemToRemove));
       itemToRemove = item2;
       inventory.removeItem(itemToRemove, 3); 
       assertEquals(initialSize - 1, inventory.getBackpack().size()); 
    }
	@Test
	public void testRemoveNotExixtingItem() {
		 inventory.getBackpack().put(item1, 2);
		 inventory.getBackpack().put(item2, 3);
    	int initialSize = inventory.getBackpack().size();
        Item nonExistingItem = new Item("NonExistingItem", 1, true); 
        initialSize = inventory.getBackpack().size();
        inventory.removeItem(nonExistingItem, 1);
        assertEquals(initialSize, inventory.getBackpack().size());
	}
	@Test
	public void testAddExistingItem() {
		inventory.addItem(item1, 2);
		assertTrue(inventory.findItem(item1));
		int initialItemQuantity = inventory.getQuantity(item1);
		inventory.addItem(item1, 1);
    	assertEquals(inventory.getQuantity(item1),initialItemQuantity+1);
	}
	@Test
	public void testAddNotExistingItem(){
		inventory=new Inventory();
    	assertFalse(inventory.findItem(item1));
    	inventory.addItem(item1, 1);
    	assertTrue(inventory.findItem(item1));
    	assertEquals(inventory.getQuantity(item1),1);
	}

}
