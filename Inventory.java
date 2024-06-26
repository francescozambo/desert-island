import java.io.Serializable;
import java.util.HashMap;
//import java.util.Iterator;
public class Inventory implements Serializable {
	private HashMap<Item,Integer> backpack;
	private int weight;
	private final static int MAX_WEIGHT=10;		//capienza massima inventario (per ora settata a 10);
	
	public Inventory() {			//costruttore
		weight = 0;
		backpack = new HashMap<Item,Integer>();
	}

	public HashMap<Item, Integer> getBackpack() {
        return backpack;
    }
	
	public boolean findItem(Item i) {	//ritorna true se un Item i è contenuto nell'inventario
		if(backpack.containsKey(i)){
			return true;
		}
		System.out.println("Oggetto non trovato");       //print solo PER CONTROLLO;
		return false;
		//return backpack.containsKey(i);
	}
	
	public Item getItemById(String id) {
        for (Item item : backpack.keySet()) {
            if (item.getidItem().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null; 
    }
	
	public void removeItem(Item i, int q) {
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
	public void addItem(Item i,int q) {
		if (!i.isPickable() || (weight + i.getWeight() * q) > MAX_WEIGHT) {
            System.out.println("The object is too heavy");
        } else {
            backpack.put(i, backpack.getOrDefault(i, 0) + q);
            weight += i.getWeight() * q;
            System.out.println("Object added in your inventory");
        }	
	}
	public void showInventory() {		//visualizza tutto l'inventario	(item con quantità)										
			for (Item key : backpack.keySet()) {
				int value = backpack.get(key);
				System.out.println(key.getidItem() + ": " + value);
			}
		System.out.println("PESO INVENTARIO: " + weight);
	}
	public boolean isNotFull(int x){			//controlla se con un peso x aggiunto, l'inventario è pieno o no
		return MAX_WEIGHT>weight+x;
	}
	public int getWeight(){
		return weight;
	}
	public int getQuantity(Item x){				//restituisce la quantità di un dato item
		if(findItem(x)) {
		int w=backpack.get(x);
		return w;
		}
		else {
			System.out.println("Object is not in the backpack");
			return -1;
		}
	}

}
