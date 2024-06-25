import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
    		Game game;
    		String x;
    		System.out.println("What would you wanto to do?");
    		Scanner scanner =new Scanner(System.in);
    		x=scanner.next();
    		if(x.equalsIgnoreCase("play")) {
    		game=new Game();				//NUOVO GIOCO
    		game.play();
    		game.printStatusGame();
    		}
    		else if(x.equalsIgnoreCase("continue")){
    			game=new Game(1);					//CONTINUAZIONE GIOCO PRECEDENTE
    			System.out.println("WELCOME BACK");
        		game.play();
        		game.printStatusGame();
    		}
    		else {
    			System.out.println("Invalid data");
    		}
    }
    	 
}