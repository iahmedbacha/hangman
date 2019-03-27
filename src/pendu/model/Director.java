package pendu.model;

public class Director {
    private ISessionBuilder builder;

    public Director() {
        this.builder = new SessionBuilder();
    }

    public Session buildSession(Joueur joueur){
        return builder.getResult();
    }
}
