package pendu.controller;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pendu.model.ConnexionException;
import pendu.model.Jeu;
import pendu.model.Joueur;
import pendu.model.LoginModel;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class LoginController{


    public LoginModel loginModel = new LoginModel();

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label message;

    public void login(ActionEvent event) { //Connexion
        try {
            Joueur joueur = new Joueur(username.getText(), password.getText());
            if (loginModel.isLogin(username.getText(), password.getText())) {
                message.setText("Pseudonyme et mot de passe sont corrects");
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("../view/Menu.fxml").openStream());
                MenuController menuController = (MenuController) loader.getController();
                menuController.setUsername(username.getText());
                menuController.initialize();
                //primaryStage.initStyle(StageStyle.UTILITY);
                primaryStage.setTitle("Le pendu");
                primaryStage.setScene(new Scene(root, 500, 400));
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                if (!loginModel.isUser(username.getText())) {
                    message.setText("Pseudonyme n'est pas correct");
                } else {
                    message.setText("Mot de passe n'est pas correct");
                }
            }
        } catch (SQLException e) {
            message.setText("Pseudonyme et mot de passe ne sont pas corrects");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ConnexionException e)
        {
            message.setText("Le "+e.getMessage());
        }
    }

    public void signup(ActionEvent event) { // Inscription

        try {
            Joueur joueur = null;
            joueur = new Joueur(username.getText(), password.getText());
            if (!isAlpha(username.getText().charAt(0)))//Si le premier caractère n'est pas une lettre
            {
                message.setText("Le pseudonyme doit commencer avec une lettre.");
            }
            else {
                Jeu jeu = new Jeu();
                HashSet<Joueur> joueurs = null;
                if (loginModel.isUser(username.getText())) {
                    message.setText("Pseudonyme existe déjà");
                } else {
                    joueurs = jeu.getListeJoueurs();
                    try {
                        if (jeu.inscription(joueur)) {
                            message.setText("Inscription est faite avec succès");
                        }
                    } catch (ConnexionException e) {
                        message.setText("Pseudonyme existe déjà !"+e.getMessage());
                        e.printStackTrace();
                    }
                }
                jeu.saveJoueurs();
                loginModel.isSignup(username.getText(), password.getText());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ConnexionException ex)
        {

        }
    }
    //-------------------Procédures de manipulation de caractère (ASCII)------------//
    private static final int[] ctype = new int[]{8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 26624, 10240, 10240, 10240, 10240, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 18432, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, '萀', '萁', '萂', '萃', '萄', '萅', '萆', '萇', '萈', '萉', 4096, 4096, 4096, 4096, 4096, 4096, 4096, '脊', '脋', '脌', '脍', '脎', '脏', 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 4096, 4096, 4096, 4096, 69632, 4096, '舊', '舋', '舌', '舍', '舎', '舏', 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 4096, 4096, 4096, 4096, 8192};
    static int getType(int var0) { return (var0 & -128) == 0?ctype[var0]:0; }
    static boolean isType(int var0, int var1) { return (getType(var0) & var1) != 0; }
    static boolean isAlpha(int var0) { return isType(var0, 768); }

    //------------------------------------------------------------------------------//
}
