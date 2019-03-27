package pendu.model;

public class ZeroChanceCreator extends CaseCreator {

    @Override
    public Case factoryMethod(char lettreCorrecte) {
        return new ZeroChanceCase(lettreCorrecte);
    }
}
