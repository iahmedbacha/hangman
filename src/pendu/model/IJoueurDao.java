package pendu.model;

import pendu.model.Joueur;

public interface IJoueurDao {
    public void updateJoueur(Joueur joueur);
    public void saveJoueur(Joueur joueur);
    public Joueur getJoueur(String username, String password);
    public void saveScore(String username, int score);
}
