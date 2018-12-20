package Utils;

import Data.DatabaseUtils;
import Models.Exercise;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static Utils.Constants.*;

public class Parser {

    /**
     * This class is used to parse the scraped web data found in the resources/raw folder and crawl the site to get more information.
     * It will then create exercise objects that will be added to the database.
     * This class is a one time use to create the initial database, we do not want to continually re-write the database with information that we only need to get once.
     */

    //Logger for debugging
    private static Logger logger = Logger.getLogger("com.necohorne.Parser");
    private static int logNum = 0;

    private static void getExercises(InputStream inputStream) throws IOException {

        //this method will parse the html string data that gets provided by getInputString

        String file = getInputString(inputStream);

        Exercise exercise = new Exercise();

        Document doc = Jsoup.parse(file, "UTF-8");
        Elements content = doc.select(DIV_MAIN);
        Elements selection = content.select(DIV_SECTION);

        //get each exercise in the data
        for(Element section: selection) {
            String name = section.select(EXERCISE_NAME).text();
            Elements link = section.select(EXERCISE_NAME);
            String url = link.select("a").attr("href");
            String exerciseUrl = BASE_URL + url;

            //open detail web page and retrieve detail data of the exercise.
            Document detail = Jsoup.connect(exerciseUrl).get();
            Elements exerciseDetails = detail.select(EXERCISE_BREAKDOWN_LIST);
            String exerciseType = exerciseDetails.select(EXERCISE_TYPE).text();

            //create a list of steps for the exercise
            ArrayList<String> stepsList = new ArrayList<>();
            for(Element element: detail.select(EXERCISE_INSTRUCTIONS_STEP)) {
                String step = element.text();
                stepsList.add(convertInputs(step));
            }

            //continue creating exercise object from scraped data
            Elements muscleDiv = section.select(EXERCISE_MUSCLE_DIV);
            String muscle = muscleDiv.select("a").text();
            Elements equipmentDiv = section.select(EXERCISE_EQUIPMENT_DIV);
            String equipment = equipmentDiv.select("a").text();

            exercise.setName(convertInputs(name));
            exercise.setMuscleTargeted(muscle);
            exercise.setEquipmentType(equipment);
            exercise.setExerciseType(exerciseType);
            exercise.setInstructions(stepsList);

            //Add the exercise object to the database
            DatabaseUtils.addExerciseToDB(exercise);

            //check log to ensure DB was fully created and populated without issues. 997 db entries should be created.
            logNum++;
            logger.log(Level.INFO, "getExercises: logged: " + logNum );
        }

    }

    private static String convertInputs(String string){

        //this method formats the inputs so that there are no issues when inputting the values in the DB.

        String output = string.replace("/", "\\/");
        output = output.replace("'", "''");
        return output;
    }

    private static String getInputString(InputStream inputStream) {

        //convert inputStream html data into a String object that can be parsed by JSOUP.

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\A");

        boolean hasInput = scanner.hasNext();
        if (hasInput) {
            return scanner.next();
        } else {
            return null;
        }
    }

    private static void listRaw() throws IOException {

        //this method will first create a list of files in the resources/raw folder which will then be opened as input streams that will be sent to the getExercises method.

        List<File> filesInFolder = Files.walk(Paths.get("src\\main\\resources\\raw"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        for (File file : filesInFolder) {
            //create an input stream for each file in raw.
            InputStream inputStream = new FileInputStream(file);
            try {
                //run the get exercises method for each new input stream.
                getExercises(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void runParse(){
        //parsing to be done off the main thread.
        Thread thread = new Thread(() -> {
            try  {
                listRaw();
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARNING, "run: " + e );
            }
        });
        thread.start();
    }

}
