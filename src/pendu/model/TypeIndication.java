package pendu.model; /**
 * Created by Ibrahim on 19/04/2017.
 */
public enum TypeIndication {
    DEFINITION(3), SYNONYME(2), ANTONYME(1);
    private int coefficient;

    TypeIndication(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }
}
