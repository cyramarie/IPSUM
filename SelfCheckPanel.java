package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.Random;

public class SelfCheckPanel extends Check implements Initializable {
    double height, weight, age;
    String Sex1;
    String Activity1;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnRecommendation;

    @FXML
    private Button btnBack1;

    @FXML
    private ChoiceBox<String> chActivity;
    private String[] Activity = {"Little/NoExercise", "Lightly_Active", "Moderately_Active" , "Active" , "Very_Active"};

    @FXML
    private ChoiceBox<String> chGender;
    private String[] Gender = {"FEMALE", "MALE"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chActivity.getItems().addAll(Activity);
        chGender.getItems().addAll(Gender);
    }

    @FXML
    private ImageView logoSelfPage;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtBMI;

    @FXML
    private TextField txtBMIresult;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtcalorie;


    @FXML
    void btnCheck(ActionEvent event) {

        try {
            //getting the value from the text box and storing it in the class variables
            height = Double.parseDouble(txtHeight.getText());
            weight = Double.parseDouble(txtWeight.getText());
            age = Double.parseDouble(txtAge.getText());
            Sex1 = chGender.getValue();
            Activity1 = chActivity.getValue();

            Check myCheck = new Check(); //create new object of Check Class
            myCheck.BMR(weight, height, age, Sex1, Activity1); // calling BMR method from Check Class
            myCheck.BMICalculate(height, weight); //Calling the BMICalculate method from Check Class

            txtcalorie.setText(Double.toString(Math.round(myCheck.getAMR())));
            txtBMI.setText(String.valueOf((Math.round(myCheck.getResult()))));
            txtBMIresult.setText(myCheck.getBmiCategory());


        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Make sure to complete all the right needed information!", "Error", JOptionPane.ERROR_MESSAGE); }
    }

    @FXML
    void btnBack(ActionEvent event) { //Calling or switching to the Main Panel
        try{
            int result=JOptionPane.showConfirmDialog(null,"Are you sure you want to go back?");
            if(result==JOptionPane.YES_OPTION) {
                Stage nextScene = (Stage) btnBack1.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                nextScene.setTitle("IPSUM");
                nextScene.setScene(new Scene(root)); // set the second scene as the sample2.fxml in which its controller is the Controller2 Class
            }

        }catch (Exception e) {e.printStackTrace(); e.getCause();}

    }


    @FXML
    void Recommendation(ActionEvent event) {
        String recommendedDrink;
        String message = "none";

        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.orange);
        UI.put("Panel.background", Color.orange);
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));



        String[] option ={ "None", "Low Calories", "Low Carbohydrates", "High Protein", "Low Fat"};
        String[] lowCalories ={"VIBRANT DELIGHT","PERFECT DETOX", "SUMMER POTION", "KALEN PEAR", "TROPICAL MINT", "IRON BOOST", "ANTI-INFLAMMATORY TONIC"};
        String[] lowCarb = {"PERFECT DETOX", "SUMMER POTION", "KALEN PEAR",  "IRON BOOST" };
        String[] highProtein ={"ANTI-INFLAMMATORY TONIC", "KALEN PEAR", "STAY IN PINK", "VIT & MIN TREAT", "VIBRANT DELIGHT"};
        String[] lowFat = {"VIBRANT DELIGHT", "STAY IN PINK", "KALEN PEAR", "IRON BOOST", "ANTI-INFLAMMATORY TONIC"};
        String[] none = {"VIBRANT DELIGHT","PERFECT DETOX","VIT & MIN TREAT", "SUMMER POTION",  "STAY IN PINK",  "KALEN PEAR", "TROPICAL MINT", "IRON BOOST", "ANTI-INFLAMMATORY TONIC"};

        int diet = JOptionPane.showOptionDialog(null, "Do you have a specific diet?", "CHOOSE YOUR DIET", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if(diet==0){
            recommendedDrink =  (none[new Random().nextInt(none.length)]);
            message = "A drink can go a long way.\nEssential macros and nutrients is essential to your body.\n--\n--\nGet all the benefits with this IPSUM DRINK!";}

        else if (diet==1)
        {recommendedDrink =  (lowCalories[new Random().nextInt(lowCalories.length)]);
            message = "We must make that calories low.\n--\n--\nGet all the benefits with this low calorie IPSUM DRINK!";}
        else if (diet==2)
        {recommendedDrink =  (lowCarb[new Random().nextInt(lowCarb.length)]);
            message = "You can enjoy this without carbohydrate guilt.\n--\n--\nGet all the benefits with this low carb IPSUM DRINK!";  }
        else if (diet==3)
        {recommendedDrink =  (highProtein[new Random().nextInt(highProtein.length)]);
            message = "This drink is rich in protein which is essential for muscle growth.\n--\n--\nGet all the benefits with this high protein IPSUM DRINK!";}
        else if (diet==4)
        {recommendedDrink =  (lowFat[new Random().nextInt(lowFat.length)]);
            message = "This drink has no fats.\n--\n--\nGet all the benefits with this low fat IPSUM DRINK!"; }
        else {recommendedDrink = "none"; }


        if(recommendedDrink.equalsIgnoreCase("VIBRANT DELIGHT")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide1.PNG");
            JOptionPane.showMessageDialog(null, "Ginger and Carrots will keep you Vibrant!\nTheir health benefits will keep you delighted!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("PERFECT DETOX")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide2.PNG");
            JOptionPane.showMessageDialog(null, "Eliminate toxins from your body, improve health, and loss weight!\nDill, Carrots, Lemon, and Apple will keep you detoxified!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("VIT & MIN TREAT")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide3.PNG");
            JOptionPane.showMessageDialog(null, "Get all the vitamins and minerals from Apple, Beetroot, and Carrots!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("SUMMER POTION")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide4.PNG");
            JOptionPane.showMessageDialog(null, "Summer Potion contains Cucumber, Apple, and Ginger!\nAll their benefit will be all yours in this summer vibe!\nMoreover... "+ message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("STAY IN PINK")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide5.PNG");
            JOptionPane.showMessageDialog(null, "Stay in Pink contains Ginger, Beetroot, Pineapple, and Pear.\nShow your pink healthy spirit!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("KALEN PEAR")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide6.PNG");
            JOptionPane.showMessageDialog(null, " Kale and Pear are the most nutritious, who says you can't have both?\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("TROPICAL MINT")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide7.PNG");
            JOptionPane.showMessageDialog(null, "Tropical Mint contains Lemon, Spinach, Pineapple, Mint, Celery, and Cucumber.\nThis drink will freshen you in this tropical day while getting all those nutrients!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("IRON BOOST")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide8.PNG");
            JOptionPane.showMessageDialog(null, "Iron Boost contains Lime, Apple, Broccoli, Romaine Lettuce, Celery, and Cucumber.\nIron is a mineral that the body needs for growth and development.\nLet this drink boost your iron intake!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);

        }
        else if(recommendedDrink.equalsIgnoreCase("ANTI-INFLAMMATORY TONIC")){
            ImageIcon icon = new ImageIcon("src/sample/drink/Slide9.PNG");
            JOptionPane.showMessageDialog(null, "Anti-Inflammatory Tonic is made of Turmeric, Carrots, Ginger, Orange, Lemon, and Celery.\nInflammation is a significant risk factor for developing many chronic conditions.\nLet this drink reduce inflammation!\nMoreover... "+message,"Recommended Drink From IPSUM", JOptionPane.PLAIN_MESSAGE, icon);
        }
    }
}

