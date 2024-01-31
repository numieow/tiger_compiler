package symtab;

public class forBloc extends BasicBloc {
    
    //Stocke le compteur ( à enlever si pas pratique)
    private String compteur;

    private String condDebut;

    private String condFin;

    public forBloc(BasicBloc parent, String compteur, String condDebut, String condFin) {
        super(parent);
        this.compteur = compteur;
        this.condDebut = condDebut;
        this.condFin = condFin;
        varSymbol compteurSymbol = new varSymbol(compteur, new BasicType("int"), 0, condDebut);
        this.addSymbol(compteurSymbol);
    }

    public String getName() {
        return "boucle for du bloc " + getBlocEnglobant().getName();
    }

    public String getCompteur() {
        return compteur;
    }

    public void setCompteur(String s) {
        compteur = s;
    }

    public String getCondDebut() {
        return condDebut;
    }

    public void setCondDebut(String s) {
        condDebut = s;
    }

    public String getCondFin() {
        return condFin;
    }

    public void setCondFin(String s) {
        condFin = s;
    }

}
