package pendu.model;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by microbox on 19/04/2017.
 */
public class Jeu
{
    private static HashSet<Joueur> joueurs = new HashSet<Joueur>();
    private Session session;

    public Jeu()
    {

    }

    public Joueur getJoueur()//Retourne le joueur actuel à partir de son pseudonyle
    {
        try
        {
            Iterator<Joueur> it =joueurs.iterator();
            boolean trouv = false;
            Joueur joueur=null;
            while (it.hasNext())
            {
                joueur=it.next();
                if (joueur.getPseudo().equals(session.getJoueurActuel().getPseudo()))
                {
                    trouv=true;
                }
            }
            return joueur;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public void setSession(Session session)
    {
        this.session = session;
    }

    public boolean connexion (Joueur joueur)
    {
        if (joueurs.contains(joueur))
        {
            System.out.println("Connexion effectuée.");
            //joueur =joueurs.get(joueurs.indexOf(joueur));
            return true;
        }
        else
        {
            System.out.println("Le pseudonyme est erroné.");
            return false;
        }
    }

    public boolean inscription(Joueur joueur) throws ConnexionException //Lève une exception si le pseudo dans session est déjà présent dans la liste
    {
        if (joueurs.contains(joueur))
        {
            System.out.println("Pseudonyme déjà utilisé."+joueur.getPseudo());
            throw new ConnexionException();
        }
        else
        {
            this.joueurs.add(joueur);
            System.out.println("Inscription effectuée.");
            return true;
        }
    }

    public static HashSet<Joueur> getJoueurs()
    {
        return joueurs;
    }

    public void afficheListeJoueurs()
    {
        System.out.println("Liste des joueurs inscrits dans le jeu : ");
        for (Joueur joueur : joueurs)
        {
            System.out.println("Pseudo : "+joueur.getPseudo()+", Score : "+joueur.getMeilleurScore());
        }
    }

    public void saveJoueurs() //Enregistre l'ensemble des joueurs dans un fichier
    {
        ObjectOutputStream out;
        try
        {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Joueurs.txt"),true)));
            out.writeObject(this.joueurs);
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception ex)
        {

        }
    }

    public HashSet<Joueur> getListeJoueurs() //Récupère la liste des joueurs présents dans le fichier
    {
        ObjectInputStream in;
        HashSet<Joueur> joueurs=null;
        try
        {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Joueurs.txt"))));
            joueurs =(HashSet<Joueur>) in.readObject();
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception ex)
        {

        }
        return joueurs;
    }

    public void setJoueurs(HashSet<Joueur> liste)
    {
        joueurs.clear();
        joueurs.addAll(liste);
    }
}
