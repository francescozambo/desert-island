package main;

public class Mob extends Character{
	int actioncure=5;
	final int fleeMalus =3;
	final int smallAttack =6;
	public Mob(String id, int mH, int d) {
		super(id, mH, d);
	}
	public void flee(Player player) {
		player.giveHealth(fleeMalus);
	}
	public void attack(Player player) {
		player.removeHealth(damage);
	}
	public void smallAttack(Player player) {
		player.removeHealth(smallAttack);
	}
	public void cure() {
		this.giveHealth(actioncure);
	}
	public int getfleeMalus() {
		return fleeMalus;
	}
	public int getActioncure() {
		return actioncure;
	}
	public int getSmallAttack() {
		return smallAttack;
	}

}