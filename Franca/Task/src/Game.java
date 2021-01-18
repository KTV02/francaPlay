import java.util.Locale;
import java.util.Scanner;

public class Game {
    public Game() {
        start();
    }
    public void start(){
        boolean running=true;
        Scanner s = new Scanner(System.in);
        while(running){
            String input = s.nextLine().toLowerCase();
            System.out.println("Wer hat verloren?");
            if(input.equals("exit")){
                System.out.println("Viel Spaß");
                running=false;
            }
        }

    }
}

