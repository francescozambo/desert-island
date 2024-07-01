package main;
public class Food extends Item {
	private int healthPoints;
	Food(String x, int y, boolean p, int hp) {
		super(x, y, p);
		healthPoints=hp;
	}
	public int getHp() {
		return healthPoints;
	}
	public void useItem(Player player) {
		player.giveHealth(healthPoints);
		player.getInventory().removeItem(this, 1);
		System.out.println("You eat 1 " + this.getidItem() + " and got " + this.getHp() + " HP.");
		System.out.println("Your health is now: "+player.getHealth()+"/"+player.getMaxHealth());
	}

}
