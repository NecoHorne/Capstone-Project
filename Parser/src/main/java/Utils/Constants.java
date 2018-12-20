package Utils;

public class Constants {

    //DB constants
    public static final String EXERCISES = "exercises";
    public static final String EXERCISE_INSTRUCTIONS = "instructions";

    //Parser Data Constants. These fields correspond to the website's HTML classes, which will be used by jsoup to get the data.
    static final String BASE_URL = "https://www.bodybuilding.com";
    static final String DIV_MAIN = "div.ExCategory-results";
    static final String DIV_SECTION = "div.ExResult-cell.ExResult-cell--nameEtc";
    static final String EXERCISE_NAME = "h3.ExHeading.ExResult-resultsHeading";
    static final String EXERCISE_MUSCLE_DIV = "div.ExResult-details.ExResult-muscleTargeted";
    static final String EXERCISE_EQUIPMENT_DIV = "div.ExResult-details.ExResult-equipmentType";
    static final String EXERCISE_BREAKDOWN_LIST = "ul.bb-list--plain";
    static final String EXERCISE_INSTRUCTIONS_STEP = "li.ExDetail-descriptionStep";
    static final String EXERCISE_TYPE = "a[itemprop = exerciseType]";

}
