package pendu.model;

import pendu.model.Joueur;
import pendu.model.Mot;
import pendu.model.TypeIndication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.util.*;

/**
 * Created by microbox on 19/04/2017.
 */
public class Session extends Observable
{
    static private final int nbMots = 10;
    private List<Mot> mots;
    private Joueur joueur;
    private int nombreEssaisRestantes = 6;
    private int numMotActuel = 1;

    public void setScoreActuel(int scoreActuel) {
        this.clearChanged();
        this.scoreActuel = scoreActuel;
        this.setChanged();
        this.notifyObservers(this.scoreActuel);
    }


    public void setNbEssaisRestants(int nbEssaisRestants) {
        this.nombreEssaisRestantes = nbEssaisRestants;
    }

    private int scoreActuel;

    public int getNumMotActuel() {
        return numMotActuel;
    }

    public void setNumMotActuel(int numMotActuel) {
        this.numMotActuel = numMotActuel;
    }

    public Session()
    {
        //this.joueurActuel = joueurActuel;
        this.scoreActuel = 0;
        Random random = new Random();
        FastScanner fastScanner = newInput();
        importerMots(random.nextInt(fastScanner.nbLignes()-nbMots-1));
    }

    private void importerMots(int pos) {
        mots = new ArrayList<Mot>();
        FastScanner fastScanner = newInput();
        Mot mot;
        String m;
        String type;
        TypeIndication typeIndication;
        String ind;

        for (int i=0; i<nbMots+pos+1; i++)
        {
            //System.out.println("ici");
            if (i<pos)
            {
                fastScanner.next();
                fastScanner.next();
                fastScanner.next();
            }
            else
            {
                type =  fastScanner.next();
                if (type.contains("\uFEFF")) {
                    type = type.substring(1);
                }
                ind = fastScanner.next();
                m = fastScanner.next();
                if (type.compareTo("SYNONYME")==0)
                {
                    typeIndication = TypeIndication.SYNONYME;
                }
                else
                {
                    if (type.compareTo("DEFINITION")==0)
                    {
                        typeIndication = TypeIndication.DEFINITION;
                    }
                    else
                    {
                        typeIndication = TypeIndication.ANTONYME;
                    }
                }
                mot = new Mot(m,typeIndication,ind);
                if (m.length()>6)
                {
                    mots.add(mot);
                }
            }
        }
    }


    public Joueur getJoueurActuel()
    {
        return this.joueur;
    }

    public List<Mot> getMots () {
        return mots;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void ajouterMot(Mot mot) {
        this.mots.add(mot);
    }

    static class FastScanner {
        static BufferedReader br;
        static StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        private FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine(),";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nbLignes () {
            return Integer.parseInt(br.lines().count()+"");
        }
    }

    private FastScanner newInput() {
        try {
            return new FastScanner(new File("src/mots.poo"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNbEssaisRestants () {
        return nombreEssaisRestantes;
    }

    public int getScoreActuel () {
        return scoreActuel;
    }
}
