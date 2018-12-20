# Data Parser
The parser program will be run once to create an exercise database from web data scraped from a popular fitness website. The database will then be copied to the Android app.

## Usage
in `DatabaseUtils.java` class change the PATH variable to the correct PATH.

Then just hit run on the main method `Main.java` in your IDE . This will initiate the parsing of the html web data in the `resources/raw` folder. Ensure internet is connected as the program will download and parse additional pages te get all the necessary information.

After the program has run an `exercise.db` file should have been created in `Parser/db/` directory.
Check the logs, 997 excesses should be logged in the DB.

## Extra Info
Java 8 with a Gradle build system.

jsoup used to parse HTML data.

SQLite Database used

## License
[MIT](https://choosealicense.com/licenses/mit/)