package pendu.model;

public class PropositionCreator extends CaseCreator {

    @Override
    public Case factoryMethod(char lettreCorrecte) {
        return new Proposition(lettreCorrecte);
    }
}