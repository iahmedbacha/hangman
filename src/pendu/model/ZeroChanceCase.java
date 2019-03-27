package pendu.model; /**
 * Created by Ibrahim on 19/04/2017.
 */
public class ZeroChanceCase extends Case {

    public ZeroChanceCase(char lettreCorrecte, int coefficient) {
        super(lettreCorrecte, coefficient);
    }

    public ZeroChanceCase (Character lettreCorrecte)
    {
        super(lettreCorrecte,3);
    }
}
