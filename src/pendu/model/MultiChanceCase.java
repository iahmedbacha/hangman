package pendu.model;
public class MultiChanceCase extends Case implements Malus {

    private int nbChancesRestantes;

    public int getNbChancesRestantes() {
        return nbChancesRestantes;
    }

    public MultiChanceCase (Character lettreCorrecte)
    {
        super(lettreCorrecte,1);
        this.nbChancesRestantes = 2;
    }

    public int getMalus()
    {
        return (2-nbChancesRestantes)*2;
    }

    @Override
    public Boolean validate(char lettre) {
        if (this.getValide() == null){
            if (this.getLettreCorrecte() == lettre){
                return this.setValide(true);
            }else {
                nbChancesRestantes--;
                if (nbChancesRestantes == 0){
                    return this.setValide(false);
                }else {
                    return this.getValide();
                }
            }
        }else {
            return this.getValide();
        }
    }
}
