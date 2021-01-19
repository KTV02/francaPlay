import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner s = new Scanner(System.in);
    String man;
    String woman;
    PlayerConfig config = PlayerConfig.ALCCLOTHED;
    LinkedList<ThirdTask> softMale;
    LinkedList<ThirdTask> softFemale;
    LinkedList<ThirdTask> hardMale;
    LinkedList<ThirdTask> hardFemale;
    TaskFiller tf;

    public Game() {
        tf = new TaskFiller();
        softMale = tf.getMF()[0];
        softFemale = tf.getMF()[1];
        start();
    }

    public void start() {
        boolean running = true;
        System.out.println("Name des Bois");
        man = s.nextLine().toLowerCase();
        System.out.println("Name des Gurls");
        woman = s.nextLine().toLowerCase();
        while (running) {
            System.out.println("Wer hat verloren?");
            String input = s.nextLine().toLowerCase();
            if (input.equals("exit")) {
                System.out.println("Viel Spaß");
                running = false;
            } else if (input.contains("nackt")) {
                if(config==PlayerConfig.ALCCLOTHED)
                config = PlayerConfig.ALCNAKED;
                else if(config==PlayerConfig.NOALCCLOTHED)
                    config= PlayerConfig.NOALCNAKED;
                System.out.println("sexy");
            }
            // Either man or woman has lost previous game -> chooses target of next task
            if (input.equals(man)) {
                System.out.println("Tja "+man+" dann mal los:");
                getMaleTask();
            } else if (input.equals(woman)) {
                System.out.println("Tja "+woman+" dann mal los:");
                getFemaleTask();
            }


        }
    }

    private void getMaleTask() {
        Random r = new Random();
        int choose= r.nextInt()*100;
        if (config == PlayerConfig.ALCCLOTHED) {
            if(chooseAlcClothed()==1)   // checks if Tasks needs to be delivered or different path -> e.g. shots
            System.out.println(softMale.get(r.nextInt(softMale.size())).getContent());
        }else if(config==PlayerConfig.ALCNAKED){
            System.out.println(hardMale.get(r.nextInt(hardMale.size())).getContent());
        }


    }

    private void getFemaleTask() {
        Random r = new Random();
        if (config == PlayerConfig.ALCCLOTHED) {
            if(chooseAlcClothed()==1)  // checks if Tasks needs to be delivered or different path -> e.g. shots
            System.out.println(softFemale.get(r.nextInt(softFemale.size())).getContent());
        }else if(config==PlayerConfig.ALCNAKED){
            System.out.println(hardFemale.get(r.nextInt(hardFemale.size())).getContent());
        }
    }

    private int chooseAlcClothed() {
        Random r = new Random();
        int rNumber = r.nextInt(100);
        System.out.println("RNumber "+rNumber);
        if (rNumber > 66) {
            //signals that main method needs to draw a Task
            return 1;
        } else if (rNumber > 33) {
            System.out.println("Trinke einen Shot!");
            return 0;

        } else{

            System.out.println("Ziehe ein Kleidungsstück aus");
            return 0;

        }
    }


    // ERweitern um Modus ohne Alkohol
    private void chooseClothed() {


    }
}


