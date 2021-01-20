import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner s = new Scanner(System.in);
    String man;
    String woman;
    PlayerConfig config = PlayerConfig.ALCCLOTHED;
    LinkedList<ThirdTask> softMale;
    LinkedList<ThirdTask> softFemale;
    LinkedList<HardcoreTask> hardMale;
    LinkedList<HardcoreTask> hardFemale;
    TaskFiller tf;

    public Game() {
        tf = new TaskFiller();
        softMale = tf.getSoftMF()[1];
        softFemale = tf.getSoftMF()[0];
        hardFemale=tf.getHardMF()[0];
        hardMale=tf.getHardMF()[1];
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
        AbstractTask current;
        if (config == PlayerConfig.ALCCLOTHED) {
            if(chooseAlcClothed()==1) { // checks if Tasks needs to be delivered or different path -> e.g. shots
                current = softMale.get(r.nextInt(softMale.size()));
                System.out.println(current.getContent());
                checkOnce(current,0);
            }
        }else if(config==PlayerConfig.ALCNAKED){
            current=hardMale.get(r.nextInt(hardMale.size()));
            System.out.println(current.getContent()+" fuer "+((HardcoreTask) current).getDuration());
            checkOnce(current,3);
        }


    }

    private void getFemaleTask() {
        Random r = new Random();
        AbstractTask current;
        if (config == PlayerConfig.ALCCLOTHED) {
            if(chooseAlcClothed()==1)  // checks if Tasks needs to be delivered or different path -> e.g. shots
            {
                current=softFemale.get(r.nextInt(softFemale.size()));
                System.out.println(current.getContent());
                checkOnce(current,1);
            }

        }else if(config==PlayerConfig.ALCNAKED){
            current=hardFemale.get(r.nextInt(hardFemale.size()));
            System.out.println(current.getContent()+" fuer "+((HardcoreTask) current).getDuration());
            checkOnce(current,3);
        }

    }
    private void checkOnce(AbstractTask check,int list){
        if(check.isOnce()){
            switch (list){
                case(0):
                    if(!softMale.isEmpty())
                    softMale.remove(check);
                case(1):
                    if(!softFemale.isEmpty())
                    softFemale.remove(check);
                case(2):
                    if(!hardMale.isEmpty())
                    hardMale.remove(check);
                case(3):
                    if(!hardFemale.isEmpty())
                    hardFemale.remove(check);
            }
        }

    }
    public void changeMode(PlayerConfig update){
        config=update;
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


