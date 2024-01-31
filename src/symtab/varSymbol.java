package symtab;

public class varSymbol implements Symbol {

    //Wait faut gérer la valeur de prend le symbole aussi
      
    /**
     * Nom de la variable
     */
    private String name;

    /**
     * Offset de la variable. Peut être négtif pour les variables d'entrée de fonction. On incrémente l'offset de 1 par rapport à la variable précédente 
     */
    private int offset;

    /**
     * BasicType de la variable
     */
    private Type type;

    /**
     * Bloc de définition du symbole
     */
    private BasicBloc bloc;

    /**
     * Valeur de la variable sous forme de string
     */
    private String valeur;

    /**
     * Constructeur d'un nouveau symbole de variable, avec le nom et le type.
     * @param name le nom du symbole
     * @param offset l'offset du symbole
     */

    public varSymbol(String name, int offset) {
        this.name = name;
        this.offset = offset;
        this.valeur = "";
    }

    public varSymbol(String name, Type type, int offset, String valeur) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.valeur = valeur;
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

    public Type getType() {
        return type;
    }

    public void setType(BasicType type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String toString() {
        if(valeur == "") {
            return name + " : " + type.getName();
        } else {
            return name + " : " + type.getName() + " := " + valeur;
        }
    }

}
