package main;
import java.io.Serializable;

public class Character implements Serializable{
	private String idCharacter;
	private int maxHealth;
	private int health;
	protected int damage; 
	boolean isAlive;
	
	Character(String id, int mH,int d){		//costruttore
		idCharacter = id;
		maxHealth = mH;
		health = maxHealth;
		damage = d;
		isAlive=true;
		
	}
	public boolean isAlive(){
		return isAlive;
	}
	public void giveHealth(int x) {
		health = health+x;
		if(health>=maxHealth){
			health=maxHealth;
		}
	}
	public void removeHealth(int x) {
		health = health-x;
		if(health<=0){
			health=0;
			isAlive=false;
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
