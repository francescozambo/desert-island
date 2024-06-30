package main;
import java.io.Serializable;
import java.util.HashMap;
//import java.util.Iterator;
public class Inventory implements Serializable {
	private HashMap<Item,Integer> backpack;
	private int weight;
	private final static int MAX_WEIGHT=10;							//costante per memorizzare la capienza massima dell'inventario
	
	public Inventory() {											//costruttore
		weight = 0;
		backpack = new HashMap<Item,Integer>();
	}

	public HashMap<Item, Integer> getBackpack() {
        return backpack;
    }
	
	public boolean findItem(Item i) {		//ritorna true se un Item i è contenuto nell'inventario
		if(backpack.containsKey(i)){
			return true;
		}
		return false;
	}
	
	public Item getItemById(String id) {					//ritorna un Item dato il suo id (Stringa) come parametro
        for (Item item : backpack.keySet()) {
            if (item.getidItem().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null; 
    }
	
	public void removeItem(Item i, int q) {						//rimuove q Item i dall'inventario
		if (!findItem(i)) {
            System.out.println("Nothing to remove");
        } else {
            int currentQuantity = backpack.get(i);
            if (currentQuantity < q) {
                System.out.println("Not enough item in the inventory");
            } else if (currentQuantity == q) {
                backpack.remove(i);
                weight -= q * i.getWeight();
                System.out.println("Object removed, you don't have any other object of that type");
            } else {
                backpack.put(i, currentQuantity - q);
                weight -= q * i.getWeight();
                System.out.println("Object removed");
            }
        }	
	}
	public void addItem(Item i,int q) {								//rimuove q Item i dall'inventario							
		if (!i.isPickable() || (weight + i.getWeight() * q) > MAX_WEIGHT) {
            System.out.println("The object is too heavy");
        } else {
            backpack.put(i, backpack.getOrDefault(i, 0) + q);
            weight += i.getWeight() * q;
            System.out.println("Object added in your inventory");
        }	
	}
	public void showInventory() {								//visualizza tutto l'inventario	(item con relativa quantità)	
		if(backpack.isEmpty()) {
			System.out.println("The inventory is empty");
		}
		else {
			for (Item key : backpack.keySet()) {
				int value = backpack.get(key);
				System.out.println(key.getidItem() + ": " + value);
			}
		}
		System.out.println("PESO INVENTARIO: " + weight + "/"+MAX_WEIGHT);
	}
	public boolean isFull(int x){			//controlla se con un peso x aggiunto, l'inventario è pieno o no
		return weight+x>MAX_WEIGHT;

	}
	public int getWeight(){
		return weight;
	}
	public int getQuantity(Item x){				//restituisce la quantità presente nell'inventario di un dato item
		if(findItem(x)) {
		int w=backpack.get(x);
		return w;
		}
		else {
			System.out.println(x.getidItem()+" is not in your backpack");
			return -1;
		}
	}

}
