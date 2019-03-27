package pendu.model; /**
 * Created by Ibrahim on 19/04/2017.
 */
public abstract class Case{
    private char lettreCorrecte;
    private int coefficient;
    private Boolean isValide;

    public Case(char lettreCorrecte, int coefficient) {
        this.lettreCorrecte = lettreCorrecte;
        this.coefficient = coefficient;
        this.isValide = null;
    }

    public char getLettreCorrecte() {
        return lettreCorrecte;
    }

    public int getCoefficient() {
        return coefficient;
    }

    protected boolean setValide(boolean valide) {
        isValide = valide;
        return valide;
    }

    protected Boolean getValide() {
        return isValide;
    }

    public Boolean validate (char lettre)
    {
        if (this.isValide == null){
            if (this.getLettreCorrecte() == lettre) {
                return this.setValide(true);
            } else {
                return this.setValide(false);
            }
        }else {
            return this.isValide;
        }
    }

}
