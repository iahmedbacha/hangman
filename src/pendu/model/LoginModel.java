package pendu.model;

import java.sql.*;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class LoginModel {

    Connection connection;

    public LoginModel () {
        connection = SqliteConnection.getInstance().getConnection();
        if (connection == null) {
            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin (String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Joueurs WHERE Username = ? AND Password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public boolean isUser (String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Joueurs WHERE Username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public void isSignup (String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Joueurs (Username, Password) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }
    }

    public ResultSet userScores (String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Scores WHERE Username = ? ORDER BY Score DESC";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public void sauvegarderScore (String username, int score) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Scores (Username, Score) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,score);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }
    }

    public int getMeilleurScore (String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Joueurs WHERE Username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("Highscore");
            }
            else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    public void setMeilleurScore (String username, int meilleurScore) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "UPDATE Joueurs SET Highscore = ? WHERE Username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,meilleurScore);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }
    }
}