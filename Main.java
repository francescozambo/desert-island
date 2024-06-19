import java.util.Scanner;

public class Main {

	public static void main(String[] args) {			
		Inventory playerInventory = new Inventory();		//ESEMPI solo per provare I metodi di Inventory
		Item rock = new Item("Rock",10,true);
		Item stick = new Item("Stick", 2, true);
		Item ship = new Item("Ship wreck",10000,false);
		playerInventory.addItem(stick, 5);
		playerInventory.removeItem(stick, 3);
		playerInventory.addItem(rock, 5);
		playerInventory.removeItem(rock, 5);
		playerInventory.addItem(ship, 1);
		playerInventory.showInventory();
	}

}
