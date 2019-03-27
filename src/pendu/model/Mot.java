package pendu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Ibrahim on 19/04/2017.
 */
public class Mot {
//    private String motCorrect;
    private List<Case> cases;
    private TypeIndication typeIndication;
    private String indication;
    private int nbCasesMalus;

    public Mot(String motCorrect, TypeIndication typeIndication, String indication) {
//        this.motCorrect = motCorrect;
        Random r = new Random();
        int nbZeroProp = r.nextInt(4);
        int nbZero = r.nextInt(nbZeroProp+1);
        int nbProp = nbZeroProp-nbZero;
        int nbMulti = motCorrect.length() - nbZeroProp;
        this.nbCasesMalus = nbMulti + nbProp;
        List<Case> list = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < nbZero; i++) {
            list.add(new ZeroChanceCase(motCorrect.charAt(position++)));
        }
        for (int i = 0; i < nbProp; i++) {
            list.add(new Proposition(motCorrect.charAt(position++)));
        }
        for (int i = 0; i < nbMulti; i++) {
            list.add(new MultiChanceCase(motCorrect.charAt(position++)));
        }
        Collections.shuffle(list);
        this.cases = list;
        this.typeIndication = typeIndication;
        this.indication = indication;
    }

    public int getScore()
    {
        int malus = 0;
        int score = 0;
        for (Case c: this.cases)
        {
            if (c.getValide() != null ){
                if (c.getValide() ){
                    score += c.getCoefficient();
                }else {
                    if (this.nbCasesMalus>5 && c instanceof Malus)
                    {
                        malus += ((Malus) c).getMalus();
                    }
                }
            }else {
                break;
            }
        }
        return score*typeIndication.getCoefficient() - malus;
    }

    public List<Case> getCases () {
        return cases;
    }

    public String getIndication () {
        return indication;
    }

    public String getTypeIndication () {
        return typeIndication.toString();
    }
}
