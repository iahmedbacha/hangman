package pendu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import pendu.model.SqliteConnection;
import java.sql.*;


/**
 * Created by Ibrahim on 21/04/2017.
 */
public class ScoresController implements Initializable {

    Connection connection = SqliteConnection.getInstance().getConnection();

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
        setImageView("/img/scores_logo.png");
        setTableView();
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn tableColumn;

    public void setImageView(String path) {
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
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

    public void setTableView () {
        try {
            final ObservableList <Scores> data = FXCollections.observableArrayList() ;
            tableColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

            //region userScores
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM Scores WHERE Username = ? ORDER BY Score DESC";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,username);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    data.add(new Scores(resultSet.getString("Score")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preparedStatement.close();
                resultSet.close();
            }
            //endregion

            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class Scores {

        private String score;

        public Scores(String score) {
            this.score = score;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }


}
