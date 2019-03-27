package pendu.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by microbox on 19/04/2017.
 */
public class Joueur implements Comparable<Joueur>, Serializable
{
    private String pseudo;
    private transient int meilleurScore;
    private transient String pwd;
    private Set<Integer> scores;

    public Joueur(String pseudo, Set<Integer> scores,int bestScore){
        this.pseudo=pseudo;
        this.scores=scores;
//        this.bestScore=bestScore;
    }

    public Joueur(String pseudo,String pwd) throws ConnexionException //Lève une exception si le premier caratère n'est pas une lettre
    {
            if (!isAlpha(pseudo.charAt(0)))//Si le premier caractère n'est pas une lettre
            {
                System.out.println("Le pseudonyme doit commencer avec une lettre.");
                throw new ConnexionException();
            } else//Si c'est une lettre
            {
                this.pseudo = pseudo;
                this.pwd = pwd;
                this.meilleurScore = 0;
                this.scores=new TreeSet<>();
            }
    }
    public Joueur(String pseudo)
    {
        this.pseudo = pseudo;
        this.scores = new TreeSet<>();
    }

    public String getPseudo()
    {
        return this.pseudo;
    }

    public int getMeilleurScore()
    {
        return this.meilleurScore;
    }

//    public TreeSet<Integer> getScores()
//    {
//        return this.scores;
//    }

    public  void setScores()
    {

    }
    public void setMeilleurScore()
    {

    }

    public int compareTo(Joueur o)
    {
        return (this.meilleurScore - o.getMeilleurScore());
    }

    public boolean equals(Object o)
    {
        return (this.pseudo.equals(((Joueur) o).getPseudo()));
    }

    public int hashCode()
    {
        return this.pseudo.hashCode();
    }


    //-------------------Procédures de manipulation de caractère (ASCII)------------//
    private static final int[] ctype = new int[]{8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 26624, 10240, 10240, 10240, 10240, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 8192, 18432, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, '萀', '萁', '萂', '萃', '萄', '萅', '萆', '萇', '萈', '萉', 4096, 4096, 4096, 4096, 4096, 4096, 4096, '脊', '脋', '脌', '脍', '脎', '脏', 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 4096, 4096, 4096, 4096, 69632, 4096, '舊', '舋', '舌', '舍', '舎', '舏', 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 4096, 4096, 4096, 4096, 8192};
    static int getType(int var0) { return (var0 & -128) == 0?ctype[var0]:0; }
    static boolean isType(int var0, int var1) { return (getType(var0) & var1) != 0; }
    static boolean isAlpha(int var0) { return isType(var0, 768); }
    //------------------------------------------------------------------------------//
}
