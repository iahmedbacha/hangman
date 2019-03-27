package pendu.model;
import java.sql.*;

/**
 * Created by Ibrahim on 21/04/2017.
 */
public class SqliteConnection {
    static private  SqliteConnection instance;
    private Connection connection;
    public static SqliteConnection getInstance() {
        if (instance==null){
            instance= new SqliteConnection();
        }
        return instance;
    }

    private SqliteConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Pendu.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
