
public class Character {
	private String idCharacter;
	private int maxHealth;
	private int health;
	private int damage; 
	
	Character(String id, int mH,int d){		//costruttore
		idCharacter = id;
		maxHealth = mH;
		health = maxHealth;
		damage = d;
		
	}
	public boolean isAlive(){
		if (health==0)
			return false;
		return true;
	}
	public void setHealth(int x) {
		health=x;
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
