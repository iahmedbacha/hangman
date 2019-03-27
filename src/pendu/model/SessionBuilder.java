package pendu.model;

public class SessionBuilder implements ISessionBuilder {
    private Session result;

    @Override
    public void init() {
        result = new Session();
    }

    @Override
    public void setJoueur(Joueur joueur) {
        result.setJoueur(joueur);
    }

    @Override
    public void ajouterMot(String chaine) {
        String[] strings = chaine.split(";");
        TypeIndication typeIndication = TypeIndication.DEFINITION;
        switch (strings[0]){
            case "SYNONYME":
                typeIndication = TypeIndication.SYNONYME;
                break;
            case "DEFINITION":
                typeIndication = typeIndication.DEFINITION;
                break;
            case "ANTONYME":
                typeIndication = typeIndication.ANTONYME;
                break;
        }
        result.ajouterMot(new Mot(strings[2],typeIndication,strings[1]));
    }

    public Session getResult() {
        return result;
    }
}
