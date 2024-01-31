package symtab;

import java.util.ArrayList;
/**
 * Classe qui contient les informations d'un bloc créé depuis une fonction
 */
public class fonctionBloc extends BasicBloc {

    /**
     * Le nom de la fonction responsable du bloc
     */
    private String name;

    /**
     * Contient tous les paramètres de la fonction
     */
    private ArrayList<recordField> parametres = new ArrayList<recordField>();

    /**
     * Constructeur
     * @param name le nom de la fonction responsable du bloc
     * @param parent le bloc parent
     */
    public fonctionBloc(String name, BasicBloc parent) {
        super(parent);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    /**
     * Ajoute un unique paramètre à la fonction
     * @param param le paramètre à ajouter
     */
    public void setParam(recordField param) {
        parametres.add(param);
        varSymbol paramSymbol = new varSymbol(param.getName(), 0); // TODO :
        paramSymbol.setType(param.getType());
        //System.out.println("typeuh"+param.getType().getName()+"fin");
        try{
            this.addSymbol(paramSymbol);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Paramètre " + param.getName() + " existe déjà dans " + getName());
        }
        
    }

    /**
     * Ajoute un certain nombre de paramètres à la fonction
     */
    public void setParametres(ArrayList<recordField> param) {
        parametres = param;
    }

    public ArrayList<recordField> getParametres() {
        return parametres;
    }
    
    
}
