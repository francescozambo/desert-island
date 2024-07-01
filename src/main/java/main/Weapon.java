package main;
public class Weapon extends Item {
	private int damage;
	public Weapon(String x, int y, boolean p, int d) {
		super(x, y, p);
		damage =d;
	}
	public int getDamage() {
		return damage;
	}
	public void useItem(Player player) {
		player.setWeapon(this);
		System.out.println(this.getidItem()+" equipped");
	}
	
	}


