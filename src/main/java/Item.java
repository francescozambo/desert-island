package src.main.java;
import java.io.Serializable;

public class Item  implements Serializable{
	private String idItem;
	private int weight;
	private boolean pick;     //indica se l'oggetto può essere raccolto o no
	
	
	public Item(String x, int y, boolean p){
		idItem = x;
		weight = y;	
		pick = p;
	}
	Item(String x){
		idItem = x;
		weight = 100;	
		pick = false;
	}
	
	int getWeight() {
		return weight;
	}
	String getidItem(){
		return idItem;
	}
	boolean isPickable() {
		return pick;
	}
	public void useItem(Player player) {
		System.out.println("You can't use this object");
	}
}



