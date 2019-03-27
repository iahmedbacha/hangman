package pendu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class MenuController implements Initializable {

    private String username;

    @FXML
    private ImageView imageView;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize() {
        setImageView("/img/Menu_Logo.jpg");
    }

    public void setImageView(String path){
        File file = new File(path);
        imageView.setImage(new Image(path));
    }

    public void jouer (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Session.fxml").openStream());
            SessionController sessionController = (SessionController)loader.getController();
            sessionController.setUsername(username);
            sessionController.initialize(((Node)event.getSource()).getScene().getWindow());
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scores (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Scores.fxml").openStream());
            ScoresController scoresController = (ScoresController)loader.getController();
            scoresController.setImageView("/img/scores_logo.png");
            scoresController.setUsername(username);
            scoresController.initialize();
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signout (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Login.fxml").openStream());
            LoginController loginController = (LoginController)loader.getController();
            //loginController.setImageView("/img/jeu_pendu.png");
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void apropos (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Ce jeu est réalisé par: " +
                "\n- AHMED BACHA Ibrahim" +
                "\n- SI-MOHAMMED Samir M'hamed");
        alert.setHeaderText("Le pendu v1.0");
        alert.setTitle("A propos");
        alert.showAndWait();
    }

    public void aide (ActionEvent event) {
        try {
            Desktop desk = Desktop.getDesktop();
            desk.open(new File("src/Guide.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
