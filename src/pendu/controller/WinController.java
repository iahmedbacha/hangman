package pendu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pendu.model.Jeu;
import pendu.model.Joueur;
import pendu.model.LoginModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class WinController implements Initializable {

    public LoginModel loginModel = new LoginModel();

    private String username;

    private int score;

    @FXML
    private Label scoreLabel;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize() {
        scoreLabel.setText(score+"");
        setImageView("/img/congratulations.png");
    }

    @FXML
    private ImageView imageView;

    public void setImageView(String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void oui (ActionEvent event) {
        try {
            loginModel.sauvegarderScore(username,score);
            Jeu jeu = new Jeu();
            HashSet<Joueur> joueurs = null;
            Joueur joueur=new Joueur(username),inter=null;
            if (score>loginModel.getMeilleurScore(username))
            {
                loginModel.setMeilleurScore(username,score);
            }
            joueurs=jeu.getListeJoueurs();
            if (joueurs.contains(joueur))
            {
                inter=joueur;
                joueurs.remove(joueur);
//                inter.getScores().add(score);
                joueurs.add(inter);
                jeu.setJoueurs(joueurs);
                jeu.saveJoueurs();
            }
            if (score>loginModel.getMeilleurScore(username))
            {
                loginModel.setMeilleurScore(username,score);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Le score est sauvegardé avec succès!");
            alert.showAndWait();
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Menu.fxml").openStream());
            MenuController menuController = (MenuController) loader.getController();
            menuController.setUsername(username);
            menuController.initialize();
            primaryStage.setTitle("Le pendu");
            primaryStage.setScene(new Scene(root, 500, 400));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void non (ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../view/Menu.fxml").openStream());
            MenuController menuController = (MenuController) loader.getController();
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
