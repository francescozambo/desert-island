package main;
import java.util.Random;

import java.util.Scanner;
public class Combact {
	private Mob mob;
	private Player player;
	private boolean win=false;
	Scanner in = new Scanner (System.in);
	Combact(Mob mob, Player player){
		this.mob=mob;
		this.player=player;
	}
	public void start(){
		while(checkPlayer()==true && checkMob() ==true) {
			System.out.println("What do you want to do? (attack/distract/cure)");
			String x = in.nextLine();
			switch(x) {
				
			case ("attack"):
				mob.removeHealth(player.getDamage());
			System.out.println("You attacked "+mob.getIdCharacter()+"\nIt has now: "+mob.getHealth()+" lifepoints");
				break;
			case ("cure"):
				player.cure();
				System.out.println("You cure yourself");
				break;
			case ("distract"):
			mob.removeHealth(player.throwStone());
			System.out.println("You attacked "+mob.getIdCharacter()+"\nIt has now: "+mob.getHealth()+" lifepoints");
				break;
			default:
				System.out.println("Invalid command");
			break;
			}
			int c = generateRandomNumber(4);
			switch(c) {
			case (1):
				mob.flee(player);
				System.out.println(mob.getIdCharacter()+" moved back...you can take a breath\nNow you have: "+player.getHealth()+" lifepoints");
			break;
			case (2):
				mob.attack(player);
				System.out.println(mob.getIdCharacter()+" bit you you...you are hurting\nNow you have: "+player.getHealth()+" lifepoints");
				
			break;
			case(3):
				mob.smallAttack(player);
				System.out.println(mob.getIdCharacter()+" scratched you...You are loosing blood\nNow you have: "+player.getHealth()+" lifepoints");
			break;
			case(4):
				mob.cure();
				System.out.println(mob.getIdCharacter()+" healed...\nOra ha: "+mob.getHealth()+" lifepoints");
			break;
			default:
				System.out.println("The mob did nothing");
			}
			
		}
		if(checkPlayer()==true) {
			win =true;
			System.out.println("You won");
		}
		
	}
	public boolean checkMob(){
		return mob.isAlive();
		
	}
	public boolean checkPlayer(){
		return player.isAlive();
	}
	public boolean getWin() {
		return win;
	}
	private int generateRandomNumber(int x) {					
		int max=x;
		Random random = new Random();
		int number=random .nextInt((max - 1) + 1) +1;
		return number;
	}
}
