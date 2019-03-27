package pendu.model;

import java.sql.Connection;

public class JoueurDao implements IJoueurDao{

    Connection connection = SqliteConnection.getInstance().getConnection();

    @Override
    public void updateJoueur(Joueur joueur) {
    }

    @Override
    public void saveJoueur(Joueur joueur) {

    }

    @Override
    public Joueur getJoueur(String username, String password) {
        return null;
    }

    @Override
    public void saveScore(String username, int score) {

    }
}
