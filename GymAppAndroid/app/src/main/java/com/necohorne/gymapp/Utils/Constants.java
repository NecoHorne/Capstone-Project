package com.necohorne.gymapp.Utils;

public class Constants {

    //https://human.biodigital.com

    //DB constants
    public static String DB_NAME = "exercise";
    public static String TABLE_NAME = "exercises";
    public static final String EXERCISE_NAME = "name";
    public static final String EXERCISE_MUSCLE = "muscleTargeted";
    public static final String EXERCISE_EQUIPMENT = "equipmentType";
    public static final String EXERCISE_TYPE = "exerciseType";
    public static final String EXERCISE_INSTRUCTIONS = "instructions";

    //Intents
    public static final String INTENT_EXERCISE = "intentExercise";

    //Shared Preferences
    public static final String PREFS = "prefs";
    public static final String AGE = "age_prefs";
    public static final String SEX = "sex_prefs";
    public static final String ACTIVITY = "activity_prefs";
    public static final String HEIGHT = "height_prefs";

    //Activity Levels
    public static final String SEDENTARY = "Sedentary";
    public static final String LIGHT = "Light";
    public static final String MODERATE = "Moderate";
    public static final String VERY_ACTIVE = "Very Active";
    public static final String EXTREMELY_ACTIVE = "Extremely Active";

    //Days
    public static final String DAY = "day";
    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";


    //Exercise Types
    public static final String STRENGTH = "Strength";
    public static final String STRETCHING = "Stretching";
    public static final String PLYOMETRICS = "Plyometrics";
    public static final String STRONGMAN = "Strongman";
    public static final String CARDIO = "Cardio";
    public static final String POWERLIFTING = "Powerlifting";
    public static final String OLYMPIC_LIFTING = "Olympic Weightlifting";

    //Exercise Equipment
    public static final String BANDS = "Bands";
    public static final String FOAM_ROLL = "Foam Roll";
    public static final String KETTLE_BELLS = "Kettlebells";
    public static final String BODY = "Body Only";
    public static final String MACHIN = "Machine";
    public static final String CABLE = "Cable";
    public static final String MEDICINE_BALL = "Medicine Ball";
    public static final String DUMBBELL = "Dumbbell";
    public static final String NONE = "None";
    public static final String EZ_BAR = "E-Z Curl Bar";
    public static final String OTHER = "Other";
    public static final String EXERCISE_BALL = "Exercise Ball";

    //Muscles
    public static final String ABDUCTORS = "Abductors";
    public static final String ABS = "Abdominals";
    public static final String ADDUCTOR = "Adductors";
    public static final String BICEPS = "Biceps";
    public static final String CALVES = "Calves";
    public static final String CHEST = "Chest";
    public static final String FOREARMS = "Forearms";
    public static final String GLUTES = "Glutes";
    public static final String HAMSTRINGS = "Hamstrings";
    public static final String LATS = "Lats";
    public static final String LOWER_BACK = "Lower Back";
    public static final String MIDDLE_BACK = "Middle Back";
    public static final String NECK = "Neck";
    public static final String QUADS = "Quadriceps";
    public static final String SHOULDERS = "Shoulders";
    public static final String TRAPS = "Traps";
    public static final String TRICEPS = "Triceps";

    public static final String ABDUCTORS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FAbductors.png?alt=media&token=7cb52ba3-8ba5-4fd9-baa6-2eadcac7f9a2";
    public static final String ABS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FAbdominis.png?alt=media&token=1f43903c-5103-4dfc-947d-9c2ad73e46b6";
    public static final String ADDUCTOR_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FAdductors.png?alt=media&token=ec35137d-ea74-4fab-bf15-376eae3c2e27";
    public static final String BICEPS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FBicep.png?alt=media&token=c33ea3c9-731a-4f2e-8863-03a4054ed025";
    public static final String CALVES_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FCalves.png?alt=media&token=a7d14393-4f58-4ae5-99b5-cf77b903aa14";
    public static final String CHEST_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FChest.png?alt=media&token=7c2f73ea-7861-4511-814d-f43041eff7d2";
    public static final String FOREARMS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FForearms.png?alt=media&token=1d14b5cc-5036-45d6-8e93-0e8ee8ecc077";
    public static final String GLUTES_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FGlutes.png?alt=media&token=8451c1f3-a655-41f7-81a3-db24f2d489a5";
    public static final String HAMSTRINGS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FHamstrings.png?alt=media&token=5adf3614-a284-4e19-936d-bef99d2c31a7";
    public static final String LATS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FLats.png?alt=media&token=d503ff4a-d41a-4e17-b2c1-c643a32562d1";
    public static final String LOWER_BACK_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2Flowerback.png?alt=media&token=e2d8c076-d1ac-4705-996d-ec4941a06a8e";
    public static final String MIDDLE_BACK_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2Fmiddleback.png?alt=media&token=fcfd3cb8-c57c-4f53-946c-7225664183d3";
    public static final String NECK_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FNeck.png?alt=media&token=07eb5e55-e971-4db4-bd9f-39867be72816";
    public static final String QUADS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FQuadriceps.png?alt=media&token=4692c5c6-a7ae-4730-b807-a01e74247d4d";
    public static final String SHOULDERS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FShoulders.png?alt=media&token=6580b947-111c-41d9-9ed7-a8dfa80375b0";
    public static final String TRAPS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FTraps.png?alt=media&token=390a5d2a-96f5-4df6-b7ff-82b75f6464ce";
    public static final String TRICEPS_URL = "https://firebasestorage.googleapis.com/v0/b/gym-app-223813.appspot.com/o/assets%2FTriceps.png?alt=media&token=8cc0df69-035d-4a8a-a63a-1b8e0ad9de70";
}
