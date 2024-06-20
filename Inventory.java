import java.util.HashMap;
import java.util.Iterator;
public class Inventory {
	HashMap<Item,Integer> backpack;
	int weight;
	final static int MAX_WEIGHT=10;		//capienza massima inventario (per ora settata a 10);
	
	public Inventory() {			//costruttore
		weight = 0;
		backpack = new HashMap<Item,Integer>();
	}
	
	public boolean findItem(Item i) {	//ritorna true se un Item i è contenuto nell'inventario
		if(backpack.containsKey(i)){
			return true;
		}
		System.out.println("Oggetto non trovato")       //print solo PER CONTROLLO;
		return false;
	}
	
	public void removeItem(Item i, int q) {
		if(findItem(i)==false) {
		System.out.println("Nothing to remove")		//print solo PER CONTROLLO;
		}
		else {
			if(backpack.get(i)-q<0) {
				System.out.println("Not enough item in the inventory")		//print solo PER CONTROLLO;
			}
			else if(backpack.get(i)-q==0) {
				backpack.remove(i);
				weight = weight-(q*i.getWeight());
				System.out.println("Object removed, you don't have any other object of that type")  //print solo PER CONTROLLO;
			}
			else {
				backpack.put(i,backpack.get(i)-q);
				weight = weight-(q*i.getWeight());
				System.out.println("Object removed")         //print solo PER CONTROLLO;
			}
		}
			
		
	}
	public void addItem(Item i,int q) {
		if(i.isPickable()==false || (weight+i.getWeight()*q)>MAX_WEIGHT) {
			System.out.println("The object is too heavy"); 
		}
		else if(findItem(i)==false) {
			backpack.put(i,q);
			weight = weight+i.getWeight()*q;			
			System.out.println("Object added in your inventory")	
		}
		else {
			backpack.put(i,backpack.get(i)+q);
			weight = weight+i.getWeight()*q;
			System.out.println("Object added")            
		}
		
	}
	public void showInventory() {						//visualizza tutto l'inventario	(item con quantità)										
		Iterator<Item> it = backpack.keySet().iterator();
		while(it.hasNext()) {
			Item key = it.next();
			int value = backpack.get(key);
			System.out.println("INVENTORY:");
			System.out.println(key.getidItem()+": "+value);
			System.out.println("PESO INVENTARIO: "+weight);
		}
	}
	public boolean isFull(){
		if(weight==MAX_WEIGHT)
			return true;
		return false;
		//
	}
	public int getWeight(){
		return weight;
	}

}
