package symtab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class tableDeSymboles {

    /**
     * Bloc global. Tous les autres blocs seront contenus dedans.
     */
    public BasicBloc BlocGlobal;

    /**
     * Bloc des fonctions prédéfinies. C'est ici que sont définies toutes les
     * fonctions prédéfinies.
     * Tous les blocs ont accès à ces fonctions.
     */
    private FonctionPredefinies FonctionsPredefinies;

    /**
     * Contient tous les types crées au cours de la compilation, que ce soient les
     * types normaux ou les types records ou array.
     */
    protected Map<String, Type> createdTypes = new LinkedHashMap<String, Type>();

    /**
     * Contient tous les types prédéfinis. Connus de tous les blocs.
     */
    protected static String[] typeAcceptes = {"int", "string", "void", "nil"};
    /**
     * Bloc courant. C'est le bloc dans lequel on se trouve actuellement.
     */
    private BasicBloc blocCourant;
    
    /**
     * Garde une trace de toutes les erreurs rencontrées
     */
    private ArrayList<String> erreurs = new ArrayList<String>();

    /**
     * Constructeur de la table de symboles
     */
    public ArrayList<BasicBloc> allBlocs= new ArrayList<BasicBloc>();
    public tableDeSymboles() {
        FonctionsPredefinies = new FonctionPredefinies();
        fillFonctionPredef();
        BlocGlobal = new BlocGlobal(FonctionsPredefinies);
        blocCourant = BlocGlobal;

    }

    /**
     * Renvoie un objet Type du dictionnaire createdTypes si le paramètre d'entrée existe dedans, sinon un nouveau BasicType si le type est un type accepté, sinon un objet null.
     * @param name le nom du type à chercher
     * @return un objet Type du dictionnaire createdTypes si le paramètre d'entrée existe dedans, sinon un nouveau BasicType si le type est un type accepté, sinon un objet null.
     */
    public Type getType(String name) {
        if (createdTypes.containsKey(name)) {
            return createdTypes.get(name);
        } else if (Arrays.asList(typeAcceptes).contains(name)) {
            return new BasicType(name);
        } else {
            return null;
        }
    }
    public void addAllBloc(BasicBloc bloc){
        this.allBlocs.add(bloc);
    }
    public void addCreatedType(String name, Type type) {
        if (Arrays.asList(typeAcceptes).contains(type.getName()) || createdTypes.containsKey(type.getName())) { // Si le type existe déjà
            throw new IllegalArgumentException("Symbol " + type.getName() + " already exists in this bloc");
        }
        createdTypes.put(name, type);
    }

    public void ajouteSymbolePredefini(Symbol symbol) {
        FonctionsPredefinies.addSymbol(symbol);
    }

    public void addSymbol(Symbol symbol) {
        blocCourant.addSymbol(symbol);
    }

    public void printTable() {
        // System.out.println(FonctionsPredefinies.getName());
        // System.out.println(BlocGlobal.getBlocEnglobant().getName());
        System.out.println(ConsoleColors.ANSI_RED + "Table des symboles : " + ConsoleColors.ANSI_RESET);
        this.FonctionsPredefinies.printBlocSymbols();

    }

    public void setBlocCourant(BasicBloc blocCourant) {
        this.blocCourant = blocCourant;
    }

    public BasicBloc getBlocCourant() {
        return blocCourant;
    }

    public void getErreursMessage() {
        if(this.erreurs.size() == 0) {
            System.out.println("Aucune erreur rencontrée lors de l'exécution.");
        } else {
            System.out.println(ConsoleColors.ANSI_RED+"Voici les erreurs rencontrées : \n"+ConsoleColors.ANSI_RESET);
            for(String erreur : this.erreurs) {
                System.out.println(erreur);
            }
        }
    } 

    public ArrayList<String> getErreurs() {
        return this.erreurs;
    }

    public void addErreur(String erreur) {
        this.erreurs.add(erreur);
    }


    public int searchSymbolOffset(String name,int imbrication,int region) {
        //On cherche le symbole dans toute la table
       /* Symbol symbol = this.BlocGlobal.searchSymbol(name);
        if(symbol == null) {
            return -1;
        }
        if (symbol instanceof varSymbol){
            return ((varSymbol) symbol).getOffset();
        } else {
            return -1;
        }*/
        for(int i=region;i>=1;i--){
        if(i>1){//* ici on cherche ds les blocs autre que le bloc global
            if(this.allBlocs.get(i-2).getImbrication()<=imbrication){
                if(this.allBlocs.get(i-2).getSymbol(name)!=null){
                    return this.allBlocs.get(i-2).getSymbol(name).getOffset();
                }
            }
        }else{
            System.out.println("aled ya un pb"
                );
            return 0;
        }
    } System.out.println("aled ya un pb"
        );
        return 0;
    }

    public BasicBloc getBlocGlobal() {
        return BlocGlobal;
    }

    private void fillFonctionPredef() {
        ArrayList<recordField> typeEntrees_print = new ArrayList<recordField>();
        // creation symbole pour la fonction print
        fonctionSymbol print = new fonctionSymbol("print", 0);
        print.setTypeRetour(new BasicType("nil"));
        typeEntrees_print.add(new recordField("s", new BasicType("string")));
        print.setTypeEntrees(typeEntrees_print);
        print.setBloc(BlocGlobal);

        // creation symbole pour la fonction flush
        fonctionSymbol flush = new fonctionSymbol("flush", 0);
        flush.setTypeRetour(new BasicType("nil"));
        ArrayList<recordField> typeEntrees_flush = new ArrayList<recordField>();
        flush.setTypeEntrees(typeEntrees_flush);
        flush.setBloc(BlocGlobal);

        // creation symbole pour la fonction getchar
        fonctionSymbol getchar = new fonctionSymbol("getchar", 0);
        getchar.setTypeRetour(new BasicType("string"));
        ArrayList<recordField> typeEntrees_getchar = new ArrayList<recordField>();
        getchar.setTypeEntrees(typeEntrees_getchar);
        getchar.setBloc(BlocGlobal);

        // creation symbole pour la fonction ord
        fonctionSymbol ord = new fonctionSymbol("ord", 0);
        ord.setTypeRetour(new BasicType("int"));
        ArrayList<recordField> typeEntrees_ord = new ArrayList<recordField>();
        typeEntrees_ord.add(new recordField("s", new BasicType("string")));
        ord.setTypeEntrees(typeEntrees_ord);
        ord.setBloc(BlocGlobal);

        // creation symbole pour la fonction chr
        fonctionSymbol chr = new fonctionSymbol("chr", 0);
        chr.setTypeRetour(new BasicType("string"));
        ArrayList<recordField> typeEntrees_chr = new ArrayList<recordField>();
        typeEntrees_chr.add(new recordField("i", new BasicType("int")));
        chr.setTypeEntrees(typeEntrees_chr);
        chr.setBloc(BlocGlobal);

        // creation symbole pour la fonction size
        fonctionSymbol size = new fonctionSymbol("size", 0);
        size.setTypeRetour(new BasicType("int"));
        ArrayList<recordField> typeEntrees_size = new ArrayList<recordField>();
        typeEntrees_size.add(new recordField("s", new BasicType("string")));
        size.setTypeEntrees(typeEntrees_size);
        size.setBloc(BlocGlobal);

        // creation symbole pour la fonction substring
        fonctionSymbol substring = new fonctionSymbol("substring", 0);
        substring.setTypeRetour(new BasicType("string"));
        ArrayList<recordField> typeEntrees_substring = new ArrayList<recordField>();
        typeEntrees_substring.add(new recordField("s", new BasicType("string")));
        typeEntrees_substring.add(new recordField("first", new BasicType("int")));
        typeEntrees_substring.add(new recordField("n", new BasicType("int")));
        substring.setTypeEntrees(typeEntrees_substring);
        substring.setBloc(BlocGlobal);

        // creation symbole pour la fonction concat
        fonctionSymbol concat = new fonctionSymbol("concat", 0);
        concat.setTypeRetour(new BasicType("string"));
        ArrayList<recordField> typeEntrees_concat = new ArrayList<recordField>();
        typeEntrees_concat.add(new recordField("s1", new BasicType("string")));
        typeEntrees_concat.add(new recordField("s2", new BasicType("string")));
        concat.setTypeEntrees(typeEntrees_concat);
        concat.setBloc(BlocGlobal);

        // creation symbole pour la fonction not
        fonctionSymbol not = new fonctionSymbol("not", 0);
        not.setTypeRetour(new BasicType("int"));
        ArrayList<recordField> typeEntrees_not = new ArrayList<recordField>();
        typeEntrees_not.add(new recordField("i", new BasicType("int")));
        not.setTypeEntrees(typeEntrees_not);
        not.setBloc(BlocGlobal);

        // creation symbole pour la fonction exit
        fonctionSymbol exit = new fonctionSymbol("exit", 0);
        exit.setTypeRetour(new BasicType("nil"));
        ArrayList<recordField> typeEntrees_exit = new ArrayList<recordField>();
        typeEntrees_exit.add(new recordField("i", new BasicType("int")));
        exit.setTypeEntrees(typeEntrees_exit);
        exit.setBloc(BlocGlobal);

        // ajout des symboles dans la table de symboles
        FonctionsPredefinies.addSymbol(print);
        FonctionsPredefinies.addSymbol(flush);
        FonctionsPredefinies.addSymbol(getchar);
        FonctionsPredefinies.addSymbol(ord);
        FonctionsPredefinies.addSymbol(chr);
        FonctionsPredefinies.addSymbol(size);
        FonctionsPredefinies.addSymbol(substring);
        FonctionsPredefinies.addSymbol(concat);
        FonctionsPredefinies.addSymbol(not);
        FonctionsPredefinies.addSymbol(exit);

    }

}
