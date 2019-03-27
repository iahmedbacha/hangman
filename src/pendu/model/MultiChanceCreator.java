package pendu.model;

public class MultiChanceCreator extends CaseCreator {

    @Override
    public Case factoryMethod(char lettreCorrecte) {
        return new MultiChanceCase(lettreCorrecte);
    }
}
