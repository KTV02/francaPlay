import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;


public class TaskFiller {
    private String thirdTaskFile = "/home/ktv/Downloads/tasks.txt";

    public TaskFiller() {

    }

    /**
     * Converts all ThirdTasks from ThirdTasks.txt into Objects
     *
     * @throws IOException
     * @since 18.01.2021
     */
    public LinkedList<ThirdTask> fillFromFileThird() throws IOException {
        //extracting all Tasks from ThirdTasks.txt and converting them into ThirdTak Objects
        LinkedList<ThirdTask> thirds = new LinkedList<>();
        String content = getContent();
        System.out.println(content);
        String[] tasks = content.split(";");
        for (String task : tasks) {
            boolean verified = false;
            String[] arguments = task.split("-");
            //For Every Task in Textfile there will be created one Object with the corresponding arguments
            //all parameters
            boolean female = false;
            boolean male = false;
            int factor = 0;
            boolean once = false;
            String contentCurrent = "";
            // set parameters to configuration specified in textfile
            if (arguments[1].equals("y")) {
                female = true;
            }
            if (arguments[2].equals("y")) {
                male = true;
            }
            factor = Integer.parseInt(arguments[3]);
            if (arguments[4].equals("y")) {
                once = true;
            }
            contentCurrent = arguments[5];
            if (!(task.replace(" ", "").length() < 5)) {
                verified = true;
            }

            //verified if contains all parameters -> prevents empty Tasks from being created
            if (verified) {
                ThirdTask temp = new ThirdTask(female, male, factor, once, contentCurrent);
                thirds.add(temp);
            }
        }
        return thirds;
    }

    //Converts Text file to String Object
    private String getContent() {
        try {
            File myObj = new File(thirdTaskFile);
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the thirdFile textfile!!!");
            e.printStackTrace();
            return "";
        }
    }
}



