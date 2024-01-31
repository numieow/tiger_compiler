package symtab;


/**
 * Interface qui définit les symboles que l'on rentrera dans la table des symboles 
 */
public interface Symbol {
    
    /**
     * Retourne le nom du symbole
     * @return le nom du symbole
     */
    String getName();
    int getOffset();
    
    /**
     * Retourne le bloc 
     * @return le bloc 
     */
    BasicBloc getBloc();

    /**
     * Définit le bloc 
     * @param bloc le bloc 
     */
    void setBloc(BasicBloc bloc);
}
