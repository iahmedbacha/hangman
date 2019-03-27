package pendu.model;

public interface ISessionBuilder {
    public void init();
    public void setJoueur(Joueur joueur);
    public void ajouterMot(String chaine);
    public Session getResult();
}
