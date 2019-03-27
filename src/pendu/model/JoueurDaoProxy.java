package pendu.model;

public class JoueurDaoProxy implements IJoueurDao {

    private JoueurDao joueurDao;
    private Joueur joueur;

    public JoueurDaoProxy(JoueurDao joueurDao) {
        this.joueurDao = joueurDao;
    }

    @Override
    public void updateJoueur(Joueur joueur) {
        this.joueurDao.updateJoueur(joueur);
    }

    @Override
    public void saveJoueur(Joueur joueur) {
        this.joueurDao.saveJoueur(joueur);
    }

    @Override
    public Joueur getJoueur(String username, String password) {
        if (this.joueur.getPseudo().equals(username)){
            return this.joueur;
        }
        return this.joueurDao.getJoueur(username,password);
    }

    @Override
    public void saveScore(String username, int score) {
        this.joueurDao.saveScore(username,score);
    }
}
