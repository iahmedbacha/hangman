package pendu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class GameOverController implements Initializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize() {
        setImageView("/img/game_over_logo.png");
    }

    @FXML
    private ImageView imageView;

    public void setImageView(String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void rejouer (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Session.fxml").openStream());
            SessionController sessionController = (SessionController)loader.getController();
            sessionController.initialize(((Node)event.getSource()).getScene().getWindow());
            sessionController.setUsername(username);
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menu (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Menu.fxml").openStream());
            MenuController menuController = (MenuController)loader.getController();
            menuController.setUsername(username);
            menuController.initialize();
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
