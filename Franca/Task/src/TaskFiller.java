import java.io.CharConversionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class TaskFiller {
    private String thirdTaskFile = "thirdTasks.txt";
    private String hardcoreFile =  "hardcoreTasks.txt";
    private boolean softInitialized;
    private boolean hardInitialized;

    public TaskFiller() {

    }

    /**
     * Converts all ThirdTasks from ThirdTasks.txt into Objects
     *
     * @since 18.01.2021
     */

    //this code is suicidal
    private LinkedList<AbstractTask> fillFromFile(String path) {
        //extracting all Tasks from ThirdTasks.txt and converting them into ThirdTask Objects
        LinkedList<AbstractTask> taskList = new LinkedList<>();
        String content = getContent(path);
        System.out.println(content);
        String[] tasks = content.split(";");
        for (String task : tasks) {
            String[] arguments = task.split("-");
            //For Every Task in Textfile there will be created one Object with the corresponding arguments
            //all parameters
            boolean female = false;
            boolean male = false;
            int factor;
            boolean once = false;
            String contentCurrent;
            // set parameters to configuration specified in textfile
            if (arguments.length >= 5) {

                if (arguments[1].equals("y"))
                    female = true;

                if (arguments[2].equals("y")) {
                    male = true;
                }
                factor = Integer.parseInt(arguments[3]);
                if (arguments[4].equals("y")) {
                    once = true;
                }
                contentCurrent = arguments[5];
                System.out.println(arguments.length);
                if (arguments.length == 6) {
                    ThirdTask temp = new ThirdTask(female, male, factor, once, contentCurrent);
                    taskList.add(temp);
                }
                if (arguments.length == 7) {
                    String duration = arguments[6];
                    HardcoreTask temp = new HardcoreTask(female, male, factor, once, contentCurrent, duration);
                    taskList.add(temp);
                }
            }


            //verified if contains all parameters -> prevents empty Tasks from being created

        }
        for(AbstractTask t:taskList){
            System.out.println("contents: "+ t.getContent());
        }

        return taskList;
    }

    public LinkedList<ThirdTask>[] getSoftMF() {
            LinkedList<AbstractTask> all;
            LinkedList<ThirdTask> male = new LinkedList<>();
            LinkedList<ThirdTask> female = new LinkedList<>();
            all = fillFromFile(thirdTaskFile);
            for (AbstractTask temp : all) {
                if (temp.getM()) {
                    male.add((ThirdTask) temp);
                }
                if (temp.getF()) {
                    System.out.println("FEmale");
                    female.add((ThirdTask) temp);
                }

            }
            LinkedList<ThirdTask>[] mf = new LinkedList[2];
            mf[1] = male;
            mf[0] = female;
            return mf;
        }

    public LinkedList<HardcoreTask>[] getHardMF(){
        LinkedList<AbstractTask> all;
        LinkedList<HardcoreTask> male= new LinkedList<>();
        LinkedList<HardcoreTask> female= new LinkedList<>();
        all = fillFromFile(hardcoreFile);
        for (AbstractTask temp : all) {
            if (temp.getM()) {
                male.add((HardcoreTask) temp);
            }if(temp.getF()){
                System.out.println("Female Hardcore added");
                female.add((HardcoreTask) temp);
            }

        }
        LinkedList<HardcoreTask>[] mf= new LinkedList[2];
        mf[1]=male; mf[0]=female;
        return mf;
    }



    //Converts Text file to String Object
    private String getContent(String taskFile) {
        try {
            File myObj = new File(taskFile);
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data =data+ myReader.nextLine();
            }
            myReader.close();
            data=data.trim();
            System.out.println("Anfang"+data+"Ende");
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the thirdFile textfile!!!");
            e.printStackTrace();
            return "";
        }
    }
}




