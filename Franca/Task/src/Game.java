import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner s = new Scanner(System.in);
    String man;
    String female;
    PlayerConfig config = PlayerConfig.ALCCLOTHED;
    LinkedList<ThirdTask> softMale;
    LinkedList<ThirdTask> softFemale;
    LinkedList<HardcoreTask> hardMale;
    LinkedList<HardcoreTask> hardFemale;
    TaskFiller tf;
    GUI gui;

    public Game(GUI g) {
        gui = g;
        tf = new TaskFiller();
        LinkedList[] softTemp = tf.getSoftMF();
        LinkedList[] hardTemp = tf.getHardMF();
        softMale = softTemp[1];
        softFemale = softTemp[0];
        hardFemale = hardTemp[0];
        hardMale = hardTemp[1];
    }

    public void buttonTriggered(String loser) {
        if (loser.equals(man)) {
            System.out.println("Tja " + man + " dann mal los:");
            getMaleTask();
        } else if (loser.equals(female)) {
            System.out.println("Tja " + female + " dann mal los:");
            getFemaleTask();
        }

    }

    public String getMale() {
        return man;
    }

    public String getFemale() {
        return female;
    }

    public void setMale(String maleName) {
        man = maleName.toLowerCase();


    }

    public void setFemale(String femaleName) {
        female = femaleName.toLowerCase();
    }


    private void getMaleTask() {
        Random r = new Random();
        AbstractTask current;
        if (config == PlayerConfig.ALCCLOTHED) {
            if (chooseAlcClothed() == 1) { // checks if Tasks needs to be delivered or different path -> e.g. shots
                if (softMale.size()==0)
                    softMale.add(new ThirdTask(false, true, 1, false, "strip naked"));
                int random = r.nextInt(softMale.size());
                current = softMale.get(random);
                gui.display(current.getContent());
                System.out.println(current.getContent());
                checkOnce(current, 0);
            }
        } else if (config == PlayerConfig.ALCNAKED) {


            if (hardMale.size()==0)
                hardMale.add(new HardcoreTask(false, true, 1, false, "Hardcoretaskliste Male leer!", "0 Sek"));
            int random = r.nextInt(hardMale.size());
            current = hardMale.get(random);
            gui.display(current.getContent() + " fuer " + ((HardcoreTask) current).getDuration());
            System.out.println(current.getContent() + " fuer " + ((HardcoreTask) current).getDuration());
            checkOnce(current, 2);
        }


    }

    private void getFemaleTask() {
        Random r = new Random();
        AbstractTask current;
        if (config == PlayerConfig.ALCCLOTHED) {
            if (chooseAlcClothed() == 1)  // checks if Tasks needs to be delivered or different path -> e.g. shots
            {

                System.out.println("Soft Female Size: "+softFemale.size());
                if (softFemale.size()==0)
                {
                    System.out.println("softFemale empty "+softFemale.size());
                    softFemale.add(new ThirdTask(true, false, 1, false, "strip naked"));
                }
                int random = r.nextInt(softFemale.size());

                current = softFemale.get(random);
                gui.display(current.getContent());
                System.out.println(current.getContent());
                checkOnce(current, 1);
            }

        } else if (config == PlayerConfig.ALCNAKED) {
            if (hardFemale.size()==0)
                hardFemale.add(new HardcoreTask(true, false, 1, false, "Hardcore Taskliste Female leer!!", "0 Sekunden"));
            int random = r.nextInt(hardFemale.size());
            current = hardFemale.get(random);
            gui.display(current.getContent() + " fuer " + ((HardcoreTask) current).getDuration());
            System.out.println(current.getContent() + " fuer " + ((HardcoreTask) current).getDuration());
            checkOnce(current, 3);
        }

    }

    private void checkOnce(AbstractTask check, int list) {
        if (check.isOnce()) {
            switch (list) {
                case (0):
                    if (!softMale.isEmpty())
                        softMale.remove(check);
                        break;
                case (1):
                    if (!softFemale.isEmpty())
                        softFemale.remove(check);
                        break;
                case (2):
                    if (!hardMale.isEmpty())
                        hardMale.remove(check);
                        break;
                case (3):
                    if (!hardFemale.isEmpty())
                        hardFemale.remove(check);
            }
        }

    }

    public void setMode(PlayerConfig update) {
        config = update;
    }

    public PlayerConfig getMode() {
        return config;
    }

    private int chooseAlcClothed() {
        Random r = new Random();
        int rNumber = r.nextInt(100);
        if (rNumber > 66) {
            //signals that main method needs to draw a Task
            return 1;
        } else if (rNumber > 33) {
            gui.display("Trinke einen Shot!");
            System.out.println("Trinke einen Shot!");
            return 0;

        } else {
            gui.display("Ziehe ein Kleidungsstück aus");
            System.out.println("Ziehe ein Kleidungsstück aus");
            return 0;

        }
    }


    // ERweitern um Modus ohne Alkohol
    private void chooseClothed() {


    }
}


