package sample;

import javax.swing.*;

public class Check { //class containing the computation of daily calorie intake and BMI
    //class attributes
    private double calculatedBMR;
    private double AMR; //AMR corresponds to the daily recommended calrie intake
    private double result;
    private String bmiCategory;

    //getters
    public double getCalculatedBMR(){
        return calculatedBMR;
    }
    public double getAMR(){
        return AMR;
    }
    public double getResult(){
        return result;
    }
    public String getBmiCategory(){
        return bmiCategory;
    }

    public void BMR (double weight, double height, double age, String sex, String Activity){ //calculate the BMR and AMR


        if (sex.equalsIgnoreCase("FEMALE")){
            calculatedBMR = 655.1 + (9.563 * weight) + (1.850 * height) + (4.676 * age);
        }
        else
        {
            calculatedBMR = 66.47 + (13.75 * weight) + (5.003 * height) + (6.755 * age);
        }

        if(Activity.equalsIgnoreCase("Little/NoExercise") ){
            AMR = calculatedBMR *1.2;
        }
        else if(Activity.equalsIgnoreCase("Lightly_Active")){
            AMR = calculatedBMR * 1.375;
        }
        else if(Activity.equalsIgnoreCase("Moderately_Active")){
            AMR = calculatedBMR * 1.55;
        }
        else if(Activity.equalsIgnoreCase("Active")){
            AMR = calculatedBMR * 1.725;
        }
        else if(Activity.equalsIgnoreCase("Very_Active")){
            AMR = calculatedBMR * 1.9;
        }

    }


    public void BMICalculate(double height, double weight){ //calculate the BMI
        double height1;
        try{
            height1=height/100;
            result = weight / (height1*height1);

            if (result < 18.5){
                bmiCategory="Underweight";
            }
            else if (result >= 18.5 && result<= 24.9){
                bmiCategory="Normal";
            }
            else if (result >= 25 && result<= 29.9){
                bmiCategory="Overweight";
            }
            else if (result > 30){
                bmiCategory="Obesity";
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
}
