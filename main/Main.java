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
    		game=new Game();							//Nuovo gioco 
    		game.play();
    		}
    		else if(x.equalsIgnoreCase("continue")){		//Continua da un salvataggio precedente, se non trova il file lancia un messaggio
    			try {
    			game=new Game(1);
    			System.out.println("WELCOME BACK");
        		game.play();
    			}
    			catch(FileNotFoundException e){
    				System.out.println("File not found...start a new game instead");
    			}
    			catch(Exception e){
    				System.out.println("Something went (very) wrong :(");
    			}
    		}
    		else if(x.equalsIgnoreCase("reset")) {	//Elimina il file di salvataggio
    			game=new Game(1,1);
    		}
    		else {
    			System.out.println("Invalid data");
    			
    		}
    		scanner.close();
    }
    	 
}