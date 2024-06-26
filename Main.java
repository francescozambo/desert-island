import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
    		Game game = null;
    		String x;
    		System.out.println("What would you wanto to do? (new/continue/reset)");
    		Scanner scanner =new Scanner(System.in);
    		x=scanner.next();
    		if(x.equalsIgnoreCase("new")) {
    		game=new Game();	//NUOVO GIOCO
    		game.play();
    		game.printStatusGame();
    		}
    		else if(x.equalsIgnoreCase("continue")){		//CONTINUAZIONE GIOCO PRECEDENTE
    			try {
    			game=new Game(1);
    			System.out.println("WELCOME BACK");
        		game.play();
        		game.printStatusGame();
    			}
    			catch(FileNotFoundException e){
    				System.out.println("File not found...start a new game instead");
    			}
    			catch(Exception e){
    				System.out.println("Something went (very) wrong :(");
    			}
    		}
    		else if(x.equalsIgnoreCase("reset")) {	//ELIMINA IL FILE DI SALVATAGGIO{
    			game=new Game(1,1);
    		}
    		else {
    			System.out.println("Invalid data");
    		}
    }
    	 
}