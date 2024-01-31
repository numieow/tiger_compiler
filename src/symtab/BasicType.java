package symtab;


import java.util.Arrays;

/**
 * Interface qui définit les types des variables que l'on rentrera dans la table des symboles
 */
public class BasicType implements Type {

    /**
     * Nom du type
     */
    private String name;

    private String alias;
    /**
     * Les types reconnus par le langage Tiger. On y ajoutera les types crées par le programme. Faut que ça soit un attribut de classe
     */
    private static String[] typeAcceptes = {"int", "string", "void", "nil"};


    /**
     * Constructeur
     *
     * @param name le nom du type
     */
    public BasicType(String name) {

        this.name = name;
    }

    /**
     * Retourne le nom du type
     *
     * @return le nom du type
     */
    public String getName() {
        return this.name;
    }

    public static void addType(String type) {
        typeAcceptes = Arrays.copyOf(typeAcceptes, typeAcceptes.length + 1);
        typeAcceptes[typeAcceptes.length - 1] = type;
    }

    public static String[] getTypeAcceptes() {
        return typeAcceptes;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public boolean isAlias() {
        return alias != null;
    }

    public void printTypeAcceptes() {
        for (int i = 0; i < typeAcceptes.length; i++) {
            System.out.print(typeAcceptes[i] + " ");
        }
        System.out.println();


    }

    public int getSize() {
        //*pour le moment on considère que tous les types ont la même taille
        return 4;
    }

}
