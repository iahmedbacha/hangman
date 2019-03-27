package pendu.model;

import java.util.*;

/**
 * Created by Ibrahim on 19/04/2017.
 */
public class Proposition extends ZeroChanceCase implements Malus {
    private char[] propositions;
    static private final int nbPropositions = 4 ;

    public Proposition (char lettreCorrecte)
    {
        super(lettreCorrecte,2);
        propositions = new char[nbPropositions];
        Random random = new Random();
        Set<Character> set = new HashSet<>();
        set.add(lettreCorrecte);
        while (set.size()<nbPropositions){
            set.add((char)('a'+Math.abs(random.nextInt(26))));
        }
        List<Character> list = new ArrayList<>(set);
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            propositions[i] = list.get(i);
        }
    }

    public int getMalus()
    {
        return 1;
    }

    public char[] getPropositions () {
        return propositions;
    }
}
