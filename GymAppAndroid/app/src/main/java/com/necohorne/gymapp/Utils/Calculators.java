package com.necohorne.gymapp.Utils;

public class Calculators {

    /**
     * This Class houses the functions for calculating:
     * -Resting Energy Expenditure
     * -Total Daily Energy Expenditure
     * -Daily macro nutrients protein, fat, carbs
     * -BMI
     * -Waist to height
     */

    //Activity Level
    public static final double SEDENTERAY = 1.2;
    public static final double LIGHT = 1.375;
    public static final double MODERATE = 1.55;
    public static final double VERY_ACTIVE = 1.725;
    public static final double EXTREMELY_ACTIVE = 1.975;

    //Conversions
    private static final double KG_TO_POUND = 2.205;
    private static final double CM_TO_INCHES = 2.54;
    private static final double PROTEIN_PER_POUND = 1.2;
    private static final double KILOJOULE_TO_CALORIE = 4.1868;

    //Calories per macro
    private static final int PROTEIN_CAL_GRAM = 4;
    private static final int FAT_CAL_GRAM = 9;
    private static final int CARBS_CAL_GRAM = 4;

    public static double getRestingEnergyExpenditure(double weight, double height, int age, boolean male){
        //weight in kg, heigh in cm
        //return in calories
        if(male){
            return (10*weight) + (6.25*height) - (5*age) + 5;
        }else {
            return (10*weight) + (6.25*height) - (5*age) - 161;
        }
    }

    public static double getTotalDailyEnergyExpenditure(double ree, double activityLevel){
        //in calories
        return ree*activityLevel;
    }

    public static double getProteinAmount(double weight){
        //in grams
        return (weight*KG_TO_POUND)*PROTEIN_PER_POUND;
    }

    public static double getFatAmount(double tdee){
        //in grams
        return (tdee*0.25)/FAT_CAL_GRAM;
    }

    public static double getCarbAmount(double protein, double fat, double tdee){
        //in grams
        return (tdee - (protein*PROTEIN_CAL_GRAM) - (fat*FAT_CAL_GRAM))/CARBS_CAL_GRAM;
    }

    public static double getBMI(double height, double weight, boolean metric){
        if(metric){
            height = height/100; //get height in meters
            //weight in kg
            return weight/(height*height);
        }else {
            //weight in pounds, height in inches.
            return weight/(height*height)*703;
        }
    }

    public static String getBMICatagory(double bmi){

        if(bmi < 15){
            return "Very severely underweight";
        }else if(bmi >= 15 && bmi < 16){
            return "Severely underweight";
        }else if(bmi >= 16 && bmi < 18.5){
            return "Underweight";
        }else if(bmi >= 18.5 && bmi < 25){
            return "Normal (healthy weight)";
        }else if(bmi >= 25 && bmi < 30){
            return "Overweight";
        }else if(bmi >= 30 && bmi < 35){
            return "Moderately obese";
        }else if(bmi >= 35 && bmi < 40){
            return "Severely obese";
        }else if(bmi >= 40){
            return "Very severely obese";
        }
        return "error";
    }

    public static int waistToHeightRatio(double waist, double height){
        return (int) Math.round((waist/height)*100);
    }

    public static String getWTHCatagory(int ratio, boolean male) {

        if(male) {
            if(ratio < 42) {
                return "Underweight";
            } else if(ratio >= 43 && ratio <= 52) {
                return "Healthy weight";
            } else if(ratio >= 53 && ratio <= 62) {
                return "Overweight";
            } else if(ratio >= 63)
                return "Obese";
        } else {

            if(ratio < 42) {
                return "Underweight";
            } else if(ratio >= 43 && ratio <= 48) {
                return "Healthy weight";
            } else if(ratio >= 49 && ratio <= 57) {
                return "Overweight";
            } else if(ratio >= 58) {
                return "Obese";
            }
        }
        return "error";
    }
}
