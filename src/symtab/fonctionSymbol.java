package symtab;

import java.util.ArrayList;

public class fonctionSymbol implements Symbol {

    //Peut-être gérer la différence entre les symboles d'entrée des autres pour le bloc de fonction

    /**
     * Le nom du symbole
     */
    private String name;

    /**
     * Offset de la variable. Peut être négtif pour les variables d'entrée de fonction. On incrémente l'offset de 1 par rapport à la variable précédente 
     */
    private int offset;

    /**
     * Le bloc dans lequel se trouve le symbole : c'est le bloc dans lequel la fonction est définie
     */
    private BasicBloc bloc;

    /**
     * Le type de retour de la fonction
     */
    private BasicType typeRetour;
    
    /**
     * Le types des arguments de la fonction. Si les types ne sont pas définis, on mettra le nombre correct de nil
     */
    private ArrayList<recordField> typesEntrees = new ArrayList<recordField>();

    /**
     * Constructeur d'un nouveau symbole de fonction, avec le nom et l'offset de la fonction.
     */
    public fonctionSymbol(String name, int offset) {
        this.name = name;
        this.offset = offset;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BasicBloc getBloc() {
        return bloc;
    }

    @Override
    public void setBloc(BasicBloc bloc) {
        this.bloc = bloc;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Set le type d'entrée de la fonction
     * @param entree le type d'entrée de la fonction
     */
    public void setTypeEntrees(recordField entree) {
        this.typesEntrees.add(entree);
    }

    /**
     * Set un certain nombre de types d'entrée
     * @param entrees les types d'entrée, dans un array
     */
    public void setTypeEntrees(ArrayList<recordField> entrees) {
        this.typesEntrees = entrees;
    }

    public void addTypeEntrees(recordField entree) {
        this.typesEntrees.add(entree);
    }

    /**
     * Retourne le type d'entrée de la fonction
     * @return le type d'entrée de la fonction
     */
    public ArrayList<recordField> getTypeEntrees() {
        return this.typesEntrees;
    }

    /**
     * Set le type de retour de la fonction
     * @param retour le type de retour de la fonction
     */
    public void setTypeRetour(BasicType retour) {
        this.typeRetour = retour;
    }

    /**
     * Retourne le type de retour de la fonction
     * @return le type de retour de la fonction
     */
    public BasicType getTypeRetour() {
        return this.typeRetour;
    }
    
}
