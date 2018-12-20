import Data.DatabaseUtils;
import Utils.Parser;

public class Main {

    /**
     * Program Main
     * Ensure internet is connected and run once to populate database
     * Check logs to ensure full creation and run to completion without issues.
     */

    public static void main(String [ ] args){
        //create database
        DatabaseUtils.createNewDatabase();
        //run the parser and populate the database
        Parser.runParse();
    }

}
