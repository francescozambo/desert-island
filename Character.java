import java.io.Serializable;

public class Character implements Serializable{
	private String idCharacter;
	private int maxHealth;
	private int health;
	protected int damage; 
	
	Character(String id, int mH,int d){		//costruttore
		idCharacter = id;
		maxHealth = mH;
		health = maxHealth;
		damage = d;
		
	}
	public boolean isAlive(){
		return health > 0;
	}
	public void setHealth(int x) {
		health = x;
		if(health>=maxHealth){
			health=maxHealth;
		}
	}
	public String getIdCharacter() {
		return idCharacter;
		
	}
	public int getMaxHealth() {
		return maxHealth;
		
	}
	public int getHealth(){
		return health;
		
	}
	public int getDamage(){
		return damage;
		
	}
}
