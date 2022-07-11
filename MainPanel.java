package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainPanel { //Panel containing the tqo button towards Self-check Panel and Menu Panel

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnSelfCheck;

    @FXML
    private ImageView mainLogo;


    @FXML
    void btnSelfCheckPage(ActionEvent event){ //Calling or switching to the Self-Check Panel
        try{
            Stage nextScene = (Stage) btnSelfCheck.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("sample1.fxml"));
            nextScene.setTitle("IPSUM");
            nextScene.setScene(new Scene(root)); // set the second scene as the sample2.fxml in which its controller is the Controller2 Class


        }catch (Exception e) {e.printStackTrace(); e.getCause();}

    }


    @FXML
    void btnMenu(ActionEvent event) { //Calling or switching to the Menu Panel

        try{
            Stage nextScene = (Stage) btnSelfCheck.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("sample3.fxml"));
            nextScene.setTitle("IPSUM");
            nextScene.setScene(new Scene(root)); // set the second scene as the sample2.fxml in which its controller is the Controller2 Class


        }catch (Exception e) {e.printStackTrace(); e.getCause();}

    }

}