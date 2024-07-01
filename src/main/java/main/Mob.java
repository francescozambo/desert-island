package main;

public class Mob extends Character{
	int action=0;
	final int fleeMalus =3;
	final int smallAttack =6;
	Mob(String id, int mH, int d) {
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
		this.giveHealth(action);
	}

}
