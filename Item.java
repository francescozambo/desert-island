
public class Item {
	private String idItem;
	private int weight;
	private boolean pick;     //indica se l'oggetto può essere raccolto o no
	
	
	Item(String x, int y, boolean p){
		idItem = x;
		weight = y;	
		pick = p;
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
}



