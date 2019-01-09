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
    private static final double SEDENTERAY = 1.2;
    private static final double LIGHT = 1.375;
    private static final double MODERATE = 1.55;
    private static final double VERY_ACTIVE = 1.725;
    private static final double EXTREMELY_ACTIVE = 1.975;

    //Conversions
    private static final double KG_TO_POUND = 2.205;
    private static final double CM_TO_INCHES = 2.54;
    private static final double PROTEIN_PER_POUND = 0.825;
    private static final double KILOJOULE_TO_CALORIE = 4.1868;

    //Calories per macro
    private static final int PROTEIN_CAL_GRAM = 4;
    private static final int FAT_CAL_GRAM = 9;
    private static final int CARBS_CAL_GRAM = 4;

    public double getRestingEnergyExpenditure(double weight, double height, int age, boolean male){
        //weight in kg, heigh in cm
        //return in calories
        if(male){
            return (10*weight) + (6.25*height) - (5*age) + 5;
        }else {
            return (10*weight) + (6.25*height) - (5*age) - 161;
        }
    }

    public double getTotalDailyEnergyExpenditure(double ree, double activityLevel){
        //in calories
        return ree*activityLevel;
    }

    public double getProteinAmount(double weight){
        //in grams
        return (weight*KG_TO_POUND)*PROTEIN_PER_POUND;
    }

    public double getFatAmount(double tdee){
        //in grams
        return (tdee*0.25)/FAT_CAL_GRAM;
    }

    public double getCarbAmount(double protein, double fat, double tdee){
        //in grams
        return (tdee - (protein*PROTEIN_CAL_GRAM) - (fat*FAT_CAL_GRAM))/CARBS_CAL_GRAM;
    }

    public double getBMI(double height, double weight, boolean metric){
        if(metric){
            height = height/100; //get height in meters
            //weight in kg
            return weight/(height*height);
        }else {
            //weight in pounds, height in inches.
            return weight/(height*height)*703;
        }
    }

    public String getBMICatagory(double bmi){

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

    public int waistToHeightRatio(double waist, double height){
        return (int) Math.round((waist/height)*100);
    }

    public String getWTHCatagory(int ratio, boolean male) {

        if(male) {
            if(ratio < 42) {
                return "underweight";
            } else if(ratio >= 43 && ratio <= 52) {
                return "healthy weight";
            } else if(ratio >= 53 && ratio <= 62) {
                return "overweight";
            } else if(ratio >= 63)
                return "obese";
        } else {

            if(ratio < 42) {
                return "underweight";
            } else if(ratio >= 43 && ratio <= 48) {
                return "healthy weight";
            } else if(ratio >= 49 && ratio <= 57) {
                return "overweight";
            } else if(ratio >= 58) {
                return "obese";
            }
        }
        return "error";
    }
}
