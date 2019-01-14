package com.necohorne.gymapp.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "measurement")
public class Measurement implements Parcelable {

    @PrimaryKey(autoGenerate = true) //this variable is auto generated by Room for the DB.
    private int primaryKey;

    private Date date;

    private double neck;
    private double chest;
    private double leftArm;
    private double rightArm;
    private double leftForearm;
    private double rightForearm;
    private double waist;
    private double leftLeg;
    private double rightLeg;
    private double leftCalf;
    private double rightCalf;
    private double weight;

    private int age;
    private boolean male;
    private double activityLevel;
    private double height;

    private double restingEnergyExpenditure;
    private double totalDailyEnergyExpenditure;
    private double proteinAmount;
    private double fatAmount;
    private double carbAmount;
    private double BMI;
    private String BMICategory;
    private int waistToHeightRatio;
    private String WITHCategory;

    @Ignore //this annotation is only to let Room know not to use this constructor.
    public Measurement() {
    }

    public Measurement(int primaryKey, Date date, double neck, double chest, double leftArm, double rightArm, double leftForearm, double rightForearm, double waist, double leftLeg, double rightLeg, double leftCalf, double rightCalf, double weight, int age, boolean male, double activityLevel, double height, double restingEnergyExpenditure, double totalDailyEnergyExpenditure, double proteinAmount, double fatAmount, double carbAmount, double BMI, String BMICategory, int waistToHeightRatio) {
        this.primaryKey = primaryKey;
        this.date = date;
        this.neck = neck;
        this.chest = chest;
        this.leftArm = leftArm;
        this.rightArm = rightArm;
        this.leftForearm = leftForearm;
        this.rightForearm = rightForearm;
        this.waist = waist;
        this.leftLeg = leftLeg;
        this.rightLeg = rightLeg;
        this.leftCalf = leftCalf;
        this.rightCalf = rightCalf;
        this.weight = weight;
        this.age = age;
        this.male = male;
        this.activityLevel = activityLevel;
        this.height = height;
        this.restingEnergyExpenditure = restingEnergyExpenditure;
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
        this.proteinAmount = proteinAmount;
        this.fatAmount = fatAmount;
        this.carbAmount = carbAmount;
        this.BMI = BMI;
        this.BMICategory = BMICategory;
        this.waistToHeightRatio = waistToHeightRatio;
    }

    protected Measurement(Parcel in) {
        date = (Date) in.readSerializable();
        primaryKey = in.readInt();
        neck = in.readDouble();
        chest = in.readDouble();
        leftArm = in.readDouble();
        rightArm = in.readDouble();
        leftForearm = in.readDouble();
        rightForearm = in.readDouble();
        waist = in.readDouble();
        leftLeg = in.readDouble();
        rightLeg = in.readDouble();
        leftCalf = in.readDouble();
        rightCalf = in.readDouble();
        weight = in.readDouble();
        age = in.readInt();
        male = in.readByte() != 0;
        activityLevel = in.readDouble();
        height = in.readDouble();
        restingEnergyExpenditure = in.readDouble();
        totalDailyEnergyExpenditure = in.readDouble();
        proteinAmount = in.readDouble();
        fatAmount = in.readDouble();
        carbAmount = in.readDouble();
        BMI = in.readDouble();
        BMICategory = in.readString();
        waistToHeightRatio = in.readInt();
        WITHCategory = in.readString();
    }

    public static final Creator<Measurement> CREATOR = new Creator<Measurement>() {
        @Override
        public Measurement createFromParcel(Parcel in) {
            return new Measurement(in);
        }

        @Override
        public Measurement[] newArray(int size) {
            return new Measurement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(date);
        dest.writeInt(primaryKey);
        dest.writeDouble(neck);
        dest.writeDouble(chest);
        dest.writeDouble(leftArm);
        dest.writeDouble(rightArm);
        dest.writeDouble(leftForearm);
        dest.writeDouble(rightForearm);
        dest.writeDouble(waist);
        dest.writeDouble(leftLeg);
        dest.writeDouble(rightLeg);
        dest.writeDouble(leftCalf);
        dest.writeDouble(rightCalf);
        dest.writeDouble(weight);
        dest.writeInt(age);
        dest.writeByte((byte) (male ? 1 : 0));
        dest.writeDouble(activityLevel);
        dest.writeDouble(height);
        dest.writeDouble(restingEnergyExpenditure);
        dest.writeDouble(totalDailyEnergyExpenditure);
        dest.writeDouble(proteinAmount);
        dest.writeDouble(fatAmount);
        dest.writeDouble(carbAmount);
        dest.writeDouble(BMI);
        dest.writeString(BMICategory);
        dest.writeInt(waistToHeightRatio);
        dest.writeString(WITHCategory);
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getLeftArm() {
        return leftArm;
    }

    public void setLeftArm(double leftArm) {
        this.leftArm = leftArm;
    }

    public double getRightArm() {
        return rightArm;
    }

    public void setRightArm(double rightArm) {
        this.rightArm = rightArm;
    }

    public double getLeftForearm() {
        return leftForearm;
    }

    public void setLeftForearm(double leftForearm) {
        this.leftForearm = leftForearm;
    }

    public double getRightForearm() {
        return rightForearm;
    }

    public void setRightForearm(double rightForearm) {
        this.rightForearm = rightForearm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getLeftLeg() {
        return leftLeg;
    }

    public void setLeftLeg(double leftLeg) {
        this.leftLeg = leftLeg;
    }

    public double getRightLeg() {
        return rightLeg;
    }

    public void setRightLeg(double rightLeg) {
        this.rightLeg = rightLeg;
    }

    public double getLeftCalf() {
        return leftCalf;
    }

    public void setLeftCalf(double leftCalf) {
        this.leftCalf = leftCalf;
    }

    public double getRightCalf() {
        return rightCalf;
    }

    public void setRightCalf(double rightCalf) {
        this.rightCalf = rightCalf;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public double getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(double activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRestingEnergyExpenditure() {
        return restingEnergyExpenditure;
    }

    public void setRestingEnergyExpenditure(double restingEnergyExpenditure) {
        this.restingEnergyExpenditure = restingEnergyExpenditure;
    }

    public double getTotalDailyEnergyExpenditure() {
        return totalDailyEnergyExpenditure;
    }

    public void setTotalDailyEnergyExpenditure(double totalDailyEnergyExpenditure) {
        this.totalDailyEnergyExpenditure = totalDailyEnergyExpenditure;
    }

    public double getProteinAmount() {
        return proteinAmount;
    }

    public void setProteinAmount(double proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    public double getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(double fatAmount) {
        this.fatAmount = fatAmount;
    }

    public double getCarbAmount() {
        return carbAmount;
    }

    public void setCarbAmount(double carbAmount) {
        this.carbAmount = carbAmount;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getBMICategory() {
        return BMICategory;
    }

    public void setBMICategory(String BMICategory) {
        this.BMICategory = BMICategory;
    }

    public int getWaistToHeightRatio() {
        return waistToHeightRatio;
    }

    public void setWaistToHeightRatio(int waistToHeightRatio) {
        this.waistToHeightRatio = waistToHeightRatio;
    }

    public String getWITHCategory() {
        return WITHCategory;
    }

    public void setWITHCategory(String WITHCategory) {
        this.WITHCategory = WITHCategory;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "primaryKey=" + primaryKey +
                ", date=" + date +
                ", neck=" + neck +
                ", chest=" + chest +
                ", leftArm=" + leftArm +
                ", rightArm=" + rightArm +
                ", leftForearm=" + leftForearm +
                ", rightForearm=" + rightForearm +
                ", waist=" + waist +
                ", leftLeg=" + leftLeg +
                ", rightLeg=" + rightLeg +
                ", leftCalf=" + leftCalf +
                ", rightCalf=" + rightCalf +
                ", weight=" + weight +
                ", age=" + age +
                ", male=" + male +
                ", activityLevel=" + activityLevel +
                ", height=" + height +
                ", restingEnergyExpenditure=" + restingEnergyExpenditure +
                ", totalDailyEnergyExpenditure=" + totalDailyEnergyExpenditure +
                ", proteinAmount=" + proteinAmount +
                ", fatAmount=" + fatAmount +
                ", carbAmount=" + carbAmount +
                ", BMI=" + BMI +
                ", BMICategory='" + BMICategory + '\'' +
                ", waistToHeightRatio=" + waistToHeightRatio +
                ", WITHCategory='" + WITHCategory + '\'' +
                '}';
    }
}