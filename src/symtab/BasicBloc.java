package symtab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe abstraite qui définit les fonctions de base pour un bloc.
 */
public abstract class BasicBloc {

    protected  int region;
    protected int imbrication;

    protected String name;
    protected tableDeSymboles table;
    protected BasicBloc blocEnglobant;




    protected Map<String, Symbol> symbols = new LinkedHashMap<>();


    protected ArrayList<BasicBloc> blocs = new ArrayList<BasicBloc>();

    public BasicBloc() {

    }

    public BasicBloc(BasicBloc blocEnglobant) {
        setBlocEnglobant(blocEnglobant);
        this.imbrication = blocEnglobant.getImbrication() + 1;




    }


    public BasicBloc getBlocEnglobant() {
        return blocEnglobant;
    }

    public void setBlocEnglobant(BasicBloc blocEnglobant) {
        this.blocEnglobant = blocEnglobant;
        this.blocEnglobant.addBloc(this);
        this.imbrication = blocEnglobant.getImbrication() + 1;
    }



    public String getName(){
        return this.name;
    }

    public void addSymbol(Symbol symbol) throws IllegalArgumentException {
        if (symbols.containsKey(symbol.getName())) {
            throw new IllegalArgumentException("Symbole " + symbol.getName() + " existe déjà dans " + getName());
            
        }
        symbol.setBloc(this);
        symbols.put(symbol.getName(), symbol);
    }

    public Symbol searchSymbol(String name) {
        Symbol s = symbols.get(name);
        if (s != null) {
            return s;
        } else {
            BasicBloc parent = getBlocEnglobant();
            if (parent != null) {
                return parent.searchSymbol(name);
            } else {
                return null;
            }
        }
    }

    public Symbol getSymbol(String name) {
        return symbols.get(name);
    }

    public Symbol getLastSymbol(){
        return (Symbol) symbols.values().toArray()[symbols.size()-1];
    }
    public Symbol getSymbol(int index){
        return (Symbol) symbols.values().toArray()[index];
    }

    public ArrayList<Symbol> getSymbolsList() {
        return new ArrayList<>(symbols.values());
    }
    public Map<String, Symbol> getSymbols() {

        return symbols;
    }

    public void addBloc(BasicBloc bloc) {
        blocs.add(bloc);
    }

    public  ArrayList<BasicBloc> getBlocs() {
        return blocs;
    }

    public void printBlocSymbols() {
        //si symbols n'est pas vide
        if (!symbols.isEmpty()){
            System.out.println(ConsoleColors.ANSI_BLUE + "\nBloc \"" + getName()+" region"+ getRegion()+ "\" :" + ConsoleColors.ANSI_RESET);
        } else {
            System.out.println(ConsoleColors.ANSI_BLUE + "\nBloc \"" + getName() + " region"+ getRegion()+"\""+ ConsoleColors.ANSI_RESET + "\n\tCe bloc ne possède pas de symboles.");
        }

        if(this instanceof fonctionBloc) {
            fonctionBloc ici = (fonctionBloc) this;
            if (ici.getParametres().size() == 0) {
                System.out.println("\tCette fonction n'a aucun paramètre.");
            } else {
                System.out.println(ConsoleColors.ANSI_CYAN + "\tSes arguments sont :" + ConsoleColors.ANSI_RESET);
                for (recordField r : ici.getParametres()) {
                    System.out.println("\t" + r.toString() + "\n");
                }
            }
        }

        if(this instanceof forBloc) {
            forBloc ici = (forBloc) this;
            System.out.println("\tLe compteur de la boucle est " + ici.getCompteur() + ", son indice de début est " + ici.getCondDebut() + " et son indice de fin " + ici.getCondFin() + "\n");
        }

        if(this instanceof whileBloc) {
            whileBloc ici = (whileBloc) this;
            System.out.println("\tLa condition de la boucle est " + ici.getCond() + "\n");
        }

        if(symbols.values().size() != 0) {
            System.out.println(ConsoleColors.ANSI_CYAN + "\tListe des symboles du bloc :" + ConsoleColors.ANSI_RESET);
        }
        
        for (Symbol s : symbols.values()) {
            
            if (s instanceof fonctionSymbol){
                String fonction = "\t" + s.getName() + "(";
                ArrayList<recordField> typesEntrees = ((fonctionSymbol)s).getTypeEntrees();
                int index;
                if(typesEntrees.size() == 0){
                    fonction += ")";
                } else if (typesEntrees.size() == 1){
                    fonction += typesEntrees.get(0).toString() + ")";
                } else {
                    fonction += typesEntrees.get(0).toString() + ", ";
                    for(index = 1; index < typesEntrees.size() - 1; index++){
                        fonction += " " + typesEntrees.get(index).toString() + ", ";
                    }
                    fonction += " " + typesEntrees.get(index).toString() + ")";
                }
                if (((fonctionSymbol)s).getTypeRetour() != null){
                    fonction += " : " + ((fonctionSymbol)s).getTypeRetour().getName();
                }
                System.out.println(fonction);
            }
            else if (s instanceof varSymbol){
                if (((varSymbol)s).getType() != null){   //si la variable à un type
                    System.out.println("\t"+((varSymbol)s).toString());
                }
                else{
                    System.out.println("\t"+s.getName());    //si la variable n'a pas de type
                }
                System.out.println("\t\tOffset : "+((varSymbol)s).getOffset());
            }
            else if (s instanceof arraySymbol){
                System.out.println("\t"+((arraySymbol)s).toString());
            } else if (s instanceof recordSymbol){
                System.out.println("\t"+((recordSymbol)s).toString());
            } else {
                //Ce cas n'est jamais sesé se produire
                System.out.println("\t"+s.getName());
            }
            
        }

        for (BasicBloc b : blocs) {
            b.printBlocSymbols();
        }
    }
    public int getImbrication(){
        return this.imbrication;
    }
    public int getRegion(){
        return this.region;
    }
    public  void setRegion(int region){
        this.region = region;
    }
}
