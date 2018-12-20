package Data;

import Models.Exercise;
import Utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseUtils {

    /**
     * Database utility class
     * This class will take care of the database creation and adding the exercise data.
     * Change PATH variable to your correct path
     */

    private static final String DRIVER = "jdbc:sqlite:";
    private static final String PATH = "C:/Users/necoh/Desktop/New_Projects/Capstone-Project/Parser/db/";
    private static final String DB_NAME = "exercise.db";
    private static final String DB_URL = DRIVER + PATH + DB_NAME;

    public static void createNewDatabase() {
        try {
            //get a connection to the db.
            Connection conn = DriverManager.getConnection(DB_URL);
            //check that connection is not null
            if (conn != null) {
                File database = new File("db/" + DB_NAME);
                if (database.exists()){
                    System.out.println("DB exists");

                    //check to see if the table exists
                    DatabaseMetaData meta = conn.getMetaData();
                    ResultSet resultSet = meta.getTables(null, null, Constants.EXERCISES, null);
                    resultSet.next();
                    if (resultSet.getRow() <= 0){
                        Statement statement = conn.createStatement();
                        statement.execute("CREATE TABLE exercises (name TEXT, muscleTargeted TEXT, equipmentType TEXT, exerciseType TEXT, instructions TEXT)");
                        System.out.println("A new database has been created.");
                        statement.close();
                        conn.close();
                    }else {
                        System.out.println("Table Already exists");
                        conn.close();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addExerciseToDB(Exercise exercise){
        try {
            //get a connection to the db.
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO exercises (name, muscleTargeted, equipmentType, exerciseType,instructions ) "
            + "VALUES('"+exercise.getName()+"'," +
                    "'"+exercise.getMuscleTargeted()+"'," +
                    " '"+exercise.getEquipmentType()+"'," +
                    " '"+exercise.getExerciseType()+"'," +
                    " '"+convertArrayList(exercise.getInstructions())+"')");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String convertArrayList(ArrayList<String> arrayList){
        //this method converts the steps ArrayList into a json object then a string to easily insert into the db without issue.
        JSONObject jsonObject = new JSONObject();
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < arrayList.size(); i++) {
            jsArray.put(arrayList.get(i));
        }
        jsonObject.put(Constants.EXERCISE_INSTRUCTIONS, jsArray);
        return jsonObject.toString();
    }

}
