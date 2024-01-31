package symtab;

import com.sun.security.jgss.GSSUtil;
import parser.exprBaseListener;
import parser.exprParser;

import java.util.Objects;

public class trueListener extends exprBaseListener {

    private tableDeSymboles table;
    int region = 1;
    int imbrication =1;

    public trueListener() {
        this.table = new tableDeSymboles(); // on initialise la table des symboles
    }

    /**
     * Cette fonction parse un String d'entrée pour enlever tous les signes
     * superflus et ne garder que les entiers ou chaînes de caractères (pour faire
     * les contrôles)
     *
     * @param expr l'expression à traiter
     * @return un array de String, l'expression parsée ne contenant que des entiers
     * ou des strings
     */
    public String[] parseExpr(String expr) {

        String expr2 = expr.replace("+", " ").replace("-", " ").replace("*", " ").replace("/", " ").replace("&", " ").replace("|"," ").replace("=", " ").replace("<>", " ").replace("<", " ").replace(">", " ").replace("<=", " ").replace(">=", " ").replace("["," ").replace("]"," ");
        String[] res = expr2.split(" ");
        return res;
    }

    public boolean exprIsInt(String expr) {
        String[] res = parseExpr(expr);
        for (String s : res) {
            if (!s.matches("[0-9]+")) {
                Symbol present = this.table.getBlocCourant().searchSymbol(s);
                if (present == null) {
                    return false;
                } else if (present instanceof varSymbol) {
                    // TODO gérer les types alias
                    
                    if (!Objects.equals(((varSymbol) present).getType().getName(), "int")) {
                        return false;
                    }
                } else if (present instanceof arraySymbol) {
                    if (!Objects.equals(((arraySymbol) present).getTypeDesElements().getName(), "int")) {
                        return false;
                    }
                } else {
                        return false;
                }
            }
        }
        return true;
    }
        

    /**
     * Cette fonction enlève les parenthèses + crochets d'une expression et split la chaîne de caractères
     */
    public String[] removeBeckandCroc(String expr) {
        String expr2 = expr.replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ");
        String[] res = expr2.split(" ");
        return res;
    }

    public void printArray(String[] array) {
        for (String s : array) {
            System.out.print(s + " ");
        }
    }

    /* */
    public boolean exprIsIntFull(String expr) {
        String[] res = removeBeckandCroc(expr);
        //On check dans l'ordre inverse, pour avoir les éléments les plus imbriqués en premier
        for (int i = res.length - 1; i >= 0; i--) {
            //On check si le dernier élément avant une parenthèse ou crochet est un symbole de fonction : si c'est le cas, on check si il y a les bons arguments / indice de bon type dans l'élément d'après
            if(!res[i].contains("(")) {
                String name = res[i];
                //On ne check que si c'est un nom de variable
                if(!name.matches("[0-9]+")) {
                    Symbol present = this.table.getBlocCourant().searchSymbol(name);
                    if(present == null) {
                        if (name.contains("<")){
                            String[] args = name.split("<");
                            if(args.length != 2) {
                                return false;
                            } else {
                                //On check si les arguments ont le bon type
                                if(!exprIsString(args[0]) || !exprIsString(args[1])) {
                                    return false;
                                }
                            }
                        } else if (name.contains(">")){
                            String[] args = name.split(">");
                            if(args.length != 2) {
                                return false;
                            } else {
                                if(!exprIsString(args[0]) || !exprIsString(args[1])) {
                                    return false;
                                }
                            }
                        } else if (name.contains("<=")){
                            String[] args = name.split("<=");
                            if(args.length != 2) {
                                return false;
                            } else {
                                if(!exprIsString(args[0]) || !exprIsString(args[1])) {
                                    return false;
                                }
                            }
                        } else if (name.contains(">=")){
                            String[] args = name.split(">=");
                            if(args.length != 2) {
                                return false;
                            } else {
                                if(!exprIsString(args[0]) || !exprIsString(args[1])) {
                                    return false;
                                }
                            }
                        } else if (name.contains("=")){
                            String[] args = name.split("=");
                            if(args.length != 2) {
                                return false;
                            } else {
                                if((!exprIsString(args[0]) || !exprIsString(args[1])) && (!exprIsInt(args[0]) || !exprIsInt(args[1]))) {
                                    Symbol present2 = this.table.getBlocCourant().searchSymbol(args[0]);
                                    Symbol present3 = this.table.getBlocCourant().searchSymbol(args[1]);
                                    if(present2 == null || present3 == null) {
                                        return false;
                                    } else if (present2 instanceof arraySymbol && present3 instanceof arraySymbol) {
                                        if(!((arraySymbol) present2).getTypeDesElements().getName().equals(((arraySymbol) present3).getTypeDesElements().getName())) {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        } else if (name.contains("<>")){
                            String[] args = name.split("<>");
                            if(args.length != 2) {
                                return false;
                            } else {
                                if((!exprIsString(args[0]) || !exprIsString(args[1])) && (!exprIsInt(args[0]) || !exprIsInt(args[1]))) {
                                    Symbol present2 = this.table.getBlocCourant().searchSymbol(args[0]);
                                    Symbol present3 = this.table.getBlocCourant().searchSymbol(args[1]);
                                    if(present2 == null || present3 == null) {
                                        return false;
                                    } else if (present2 instanceof arraySymbol && present3 instanceof arraySymbol) {
                                        if(!((arraySymbol) present2).getTypeDesElements().getName().equals(((arraySymbol) present3).getTypeDesElements().getName())) {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        } else {
                            return exprIsInt(name);

                        }
                    } else {

                        //On a un nom de variable avant une parenthèse, fonctionne pas
                        if(present instanceof varSymbol) {
                            return exprIsInt(name);
                        } 
                        
                        //On a un array avant une parenthèse, fonctionne que si l'indice est bien entier
                        else if (present instanceof arraySymbol) {
                            //TODO revoir comment les types des arrays sont gérés : faut checker qu'un élément de l'array est bien un int
                            if(i+1 >= res.length || ((arraySymbol) present).getType().getName().equals("int")) {
                                //Il manque l'argument de l'array
                                return false;
                            } else {
                                boolean isInt = exprIsInt(res[i+1]);
                                if(isInt) {
                                    //On ajoute des parenthèses pour signifier à exprIsInt que c'est ok
                                    res[i] += "(";
                                    res[i+1] += ")";
                                } else {
                                    //L'indice de l'array n'est pas un entier
                                    return false;
                                }
                                return ((arraySymbol) present).getTypeDesElements().getName().equals("int");
                            }
                        }

                        else if (present instanceof recordSymbol) {
                            if(i+1 >= res.length) {
                                //Il manque l'argument du record
                                return false;
                            } 
                            String fieldName = res[i+1];
                            //On recherche le nom du field dans le record
                            recordField field = ((recordSymbol) present).getField(fieldName);
                            if(field == null) {
                                //Le field n'existe pas
                                return false;
                            } else {
                                if(!field.getType().getName().equals("int")) {
                                    //Le field esst pas entier
                                    return false;
                                } else {
                                    //On ajoute des parenthèses pour signifier à exprIsInt que c'est ok
                                    res[i] += "(";
                                    res[i+1] += ")";
                                }
                            }
                        }

                        //On a une fonction, faut checker que tous les paramètres d'entrée sont là et qu'il sont du bon type
                        else if (present instanceof fonctionSymbol) {
                            if(i+1 >= res.length) {
                                //Il manque l'argument(s) de la fonction
                                return false;
                            } 
                            //On check si le type de retour de la fonction est le bon
                            if(!((fonctionSymbol) present).getTypeRetour().getName().equals("int")) {
                                return false;
                            }
                            String[] args = res[i+1].split(",");
                            if(args.length != ((fonctionSymbol) present).getTypeEntrees().size()) {
                                //Le nombre d'arguments est pas bon
                                return false;
                            } else {
                                //TODO On check si les arguments ont le bon type
                            } 
                        }

                        // On ne peut rien avoir d'autre mais on sait jamais hein
                        else {
                            exprIsInt(name);
                        }
                        }
                    } 
                }
        }
        return true;
    }

    public boolean exprIsString(String expr) {
        if (expr.matches("\".*\"")) {
            return true;
        }
        else {
            if (expr.contains("[") && expr.contains("]")){
                int i = expr.indexOf("[");
                int j = expr.indexOf("]");
                String name = expr.substring(0, i);
                String index = expr.substring(i+1, j);
                Symbol present = this.table.getBlocCourant().searchSymbol(name);
                if (present instanceof arraySymbol && exprIsInt(index)) {
                    
                    return ((arraySymbol) present).getTypeDesElements().getName().equals("string");

                }
                else {
                    return false;
                }
            } else {

                Symbol present = this.table.getBlocCourant().searchSymbol(expr);
                if (present instanceof varSymbol) {
                    return ((varSymbol) present).getType().getName().equals("string");
                } else {
                    return false;
                }
            }
        }

    }

    public boolean typeComp(String expr) {
        String[] res = parseExpr(expr);
        Symbol help = this.table.getBlocCourant().searchSymbol(res[0]);
        if (help == null) {
            return false;
        }
        String type = ((varSymbol) help).getType().getName();

        for (String s : res) {
            if (!s.matches("[0-9]+")) {

                Symbol present = this.table.getBlocCourant().searchSymbol(s);
                if (present == null) {

                    return false;
                } else if (present instanceof varSymbol) {


                    if (!Objects.equals(((varSymbol) present).getType().getName(), type)) {

                        return false;
                    } 
                }
            } else {
                if (!Objects.equals(type, "int")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isVar(String expr) {
        if (!expr.matches("[0-9]+")) {

            Symbol present = this.table.getBlocCourant().searchSymbol(expr);
            if (present instanceof varSymbol) {

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean gotType(String expr) {
        if (!expr.matches("[0-9]+")&&!expr.matches("\".*\"")) {
            Symbol present = this.table.getBlocCourant().searchSymbol(expr);
            if (present != null) {
                return true;
            } else {

                boolean gotIt = true;
                String[] res = parseExpr(expr);
                for (String s : res){
                    if (!s.matches("[0-9]+")&&!s.matches("\".*\"")) {
                        if (s.contains("[")){
                            s = s.substring(0, s.indexOf("["));
                        }
                        Symbol present2 = this.table.getBlocCourant().searchSymbol(s);
                        if (present2 == null) {
                            gotIt = false;
                        }
                    }
                }
                
                return gotIt;

            }
        } else {
            return true;
        }

    }



    @Override
    public void enterProgram(parser.exprParser.ProgramContext ctx) { // le mec de ANTLR utilse des returns [Scope scope]
        // dans l'expr.g4

    }

    @Override
    public void exitProgram(parser.exprParser.ProgramContext ctx) {
    }

    @Override
    public void enterExpr(parser.exprParser.ExprContext ctx) {
    }

    @Override
    public void exitExpr(parser.exprParser.ExprContext ctx) {
    }

    @Override
    public void enterSegExp(parser.exprParser.SegExpContext ctx) {
    }

    @Override
    public void exitSegExp(parser.exprParser.SegExpContext ctx) {
    }

    @Override
    public void enterVariable_declaration(parser.exprParser.Variable_declarationContext ctx) { // TODO gérer les arrays


        String name = ctx.getRuleContext().getText().split(":=")[0];
        name = name.split("var")[1];
        String expr = ctx.getRuleContext().getText().split(":=")[1];


        if (expr.contains(" of ")) {// on est dans le cas d'un array'
            String type = expr.split("of")[0];
            String valeur = expr.split("of")[1];
            String size = type.substring(type.indexOf("[") + 1, type.indexOf("]"));

            boolean isInt = exprIsInt(size);
            if (!isInt) {
                this.table.addErreur("Erreur : Taille de l'array " + name + " non définie");
                return;
            }
            type = type.substring(0, type.indexOf("["));

            Type type2 = this.table.getType(type); // pas d'inspi pour le nom
            //TODO Checker si le type en question est bien un arrayType
            if (type2 == null) {
                this.table.addErreur("Erreur : Type " + type + " non défini");
                return;
            } else {
                if(!(type2 instanceof arrayType)) {
                    this.table.addErreur("Erreur : Type " + type + " n'est pas un arrayType");
                    return;
                } else {
                    arraySymbol symbol = new arraySymbol(name, this.table.getBlocCourant(), size, type2, valeur, ((arrayType) type2).getType());
                    symbol.setOffset(this.table.getBlocCourant().getSymbols().size() + 1);
                    this.table.addSymbol(symbol);
                }
                
            }

        } 
        
        //Cs d'un record
        else if(expr.contains("{")) {
            String type = expr.substring(0, expr.indexOf("{"));
            String valeur = expr.substring(expr.indexOf("{") + 1, expr.indexOf("}"));
            System.out.println(valeur != null ? valeur : "null");
            Type type2 = this.table.getType(type);
            if (type2 == null) {
                this.table.addErreur("Erreur : Type " + type + " non défini");
                return;
            } else {
                if(!(type2 instanceof recordType)) {
                    this.table.addErreur("Erreur : Type " + type + " n'est pas un recordType");
                    return;
                } else {
                    recordSymbol symbol = new recordSymbol(name, this.table.getBlocCourant());
                    symbol.setType((recordType) type2);
                    String[] fields = valeur.split(",");
                    for (String s : fields) {
                        if(!s.contains("=")) {
                            this.table.addErreur("Erreur : Mauvaise définition du record " + name);
                            return;
                        } else {
                            symbol.addValue(s);
                        }
                    }
                    this.table.addSymbol(symbol);
                } 
            }
        }
        
        
        else {
            try {
                int value = Integer.parseInt(expr);
                Type type = this.table.getType("int");

                varSymbol symbol = new varSymbol(name, type, 4, expr);
                this.table.addSymbol(symbol);
            } catch (NumberFormatException e) {
                Type type = this.table.getType("string");
                varSymbol symbol = new varSymbol(name, type, 0, expr);
                symbol.setOffset(0); // * on met 0 pour le moment
                try {
                    this.table.addSymbol(symbol);
                } catch (IllegalArgumentException e1) {
                    this.table.addErreur("Erreur : " + name + " existe déjà");
                    return;
                }

            } catch (IllegalArgumentException e1) {
                this.table.addErreur("Erreur : " + name + " existe déjà");
                return;
            }
        }

    }

    @Override
    public void exitVariable_declaration(parser.exprParser.Variable_declarationContext ctx) {
    }

    @Override
    public void enterTypeIdExpr(parser.exprParser.TypeIdExprContext ctx) {
        BasicType type = new BasicType(ctx.type_id().getText());
        Symbol var = this.table.getBlocCourant().getLastSymbol(); // le dernier symbole est forcément la var
        ((varSymbol) var).setType(type);
    }

    @Override
    public void exitTypeIdExpr(parser.exprParser.TypeIdExprContext ctx) {
    }

    @Override
    public void enterArrCreate(parser.exprParser.ArrCreateContext ctx) {

    }

    @Override
    public void exitArrCreate(parser.exprParser.ArrCreateContext ctx) {
    }

    @Override
    public void enterFunction_declaration(parser.exprParser.Function_declarationContext ctx) { // TODO finir de définir
        imbrication++;
        region++;
        // la fonction
        // (paramètres etc)
        fonctionSymbol fun = new fonctionSymbol(ctx.id().getText(), 0);

        fonctionBloc bloc = new fonctionBloc(ctx.id().getText(), this.table.getBlocCourant());
        bloc.imbrication = imbrication;
        bloc.region = region;
        this.table.addAllBloc(bloc);
        String decl = ctx.getText();
        String entrees = decl.substring(decl.indexOf('(') + 1, decl.indexOf(')'));
        String[] params = entrees.split(",");
        String retour = "void";
        if (!decl.substring(decl.indexOf(')'), decl.indexOf('=')).equals(")")) {
            retour = decl.substring(decl.indexOf(')') + 2, decl.indexOf('='));
            if (this.table.getType(retour) == null) {
                this.table.addErreur("Erreur : Type " + retour + " non défini");
            }
        }

        // On récupère tous les paramètres de la fonction
        if (params.length == 1 && params[0].equals("")) {

        } else {
            for (String param : params) {
                String[] param2 = param.split(":");
                String name = param2[0];
                String type = param2[1];
                
                
                BasicType type2 = (BasicType) this.table.getType(type);
                recordField symbol = new recordField(name, type2);
                try{
                    bloc.setParam(symbol);
                    fun.setTypeEntrees(symbol);
                }
                catch (IllegalArgumentException e) {
                    this.table.addErreur("Erreur : Nom du paramètre " + name + " déjà définie dans la fonction " + fun.getName());
                    
                }
                
            }
        }
        fun.setTypeRetour((BasicType) this.table.getType(retour));
        try {
            this.table.addSymbol(fun);
            this.table.setBlocCourant(bloc);
        } catch (IllegalArgumentException e) {
            this.table.addErreur("Erreur : Nom " + fun.getName() + " déjà définie");
            this.table.setBlocCourant(bloc);
            return;
        }


    }

    @Override
    public void exitFunction_declaration(parser.exprParser.Function_declarationContext ctx) {
        this.table.setBlocCourant(this.table.getBlocCourant().getBlocEnglobant());
        imbrication--;
    }

    @Override
    public void enterFunTypeID(parser.exprParser.FunTypeIDContext ctx) {
        BasicType type = new BasicType(ctx.type_id().getText());
        Symbol fun = this.table.getBlocCourant().getBlocEnglobant().getLastSymbol();
        // on récupère le dernier symbole du bloc englobant (qui est forcément une
        // fonction)

        ((fonctionSymbol) fun).setTypeRetour(type);
    }

    @Override
    public void exitFunTypeID(parser.exprParser.FunTypeIDContext ctx) {
    }

    @Override
    public void enterType_declaration(parser.exprParser.Type_declarationContext ctx) { // TODO le scope des types va
        // jusqu'à la fin du let
        
        if (ctx.type().getText().contains("array")) {
            //
            BasicType type = new BasicType(ctx.type().getText().split(" ")[2]);
            arrayType arr = new arrayType(type, ctx.type_id().getText());
            this.table.addCreatedType(ctx.type_id().getText(), arr);

        } else if (ctx.type().getText().contains(":")) {    // On a un recordType
            String name = ctx.type_id().getText();  // On récupère le nom du recordType
            recordType rec = new recordType(name);  // On crée le recordType
            String fields = ctx.type().getText().substring(1, ctx.type().getText().length() - 1);   // On récupère les champs du recordType
            String[] fieldsList = fields.split(",");    // On récupère chaque champ 1 par 1 et on l'ajoute au recordType

            // On récupère chaque champ 1 par 1 et on l'ajoute au recordType
            try{
                for (int i = 0; i < fieldsList.length; i++) {
                    String id = fieldsList[i].split(":")[0];
                    String type_id = fieldsList[i].split(":")[1];
                    BasicType type = new BasicType(type_id);
                    rec.addField(id, type);
                }
                this.table.addCreatedType(name, rec);
            } catch (IllegalArgumentException e) {
                this.table.addErreur("Erreur : le type " + name + " possède 2 champs du même nom \n"+ ctx.type_id().getText() + " = " + ctx.type().getText());
            } catch(IndexOutOfBoundsException e) {
                this.table.addErreur("Erreur : le type " + name + " possède un champ mal défini \n"+ ctx.type_id().getText() + " = " + ctx.type().getText());
            }
            

        } else {
            BasicType type = new BasicType(ctx.type_id().getText());

            type.setAlias(ctx.type().getText());                        //TODO WARNING  j'ai échangé type() et type_id() pour faire une chaine d'alias
            BasicType.addType(ctx.type_id().getText());
            this.table.addCreatedType(ctx.type_id().getText(), type);
            type.printTypeAcceptes();                           
            

        }
    }

    @Override
    public void exitType_declaration(parser.exprParser.Type_declarationContext ctx) {
    }

    @Override
    public void enterForExp(parser.exprParser.ForExpContext ctx) {
        imbrication++;
        region++;

        String compteur = ctx.id().getText();
        String condDebut = ctx.expr(0).getText();
        String condFin = ctx.expr(1).getText();
        boolean isInt = exprIsInt(condDebut) && exprIsInt(condFin);

        if (!isInt) {
            this.table.addErreur("Erreur : les limites de la boucle for doivent être des entiers dans le bloc " + this.table.getBlocCourant().getName());
            forBloc bloc = new forBloc(this.table.getBlocCourant(), null, null, null);
            bloc.imbrication = imbrication;
            bloc.setRegion(region);

//            this.table.getBlocCourant().addBloc(bloc);
            this.table.setBlocCourant(bloc);
            return;
        } else {
            forBloc bloc = new forBloc(this.table.getBlocCourant(), compteur, condDebut, condFin);
            bloc.imbrication = imbrication;
            bloc.setRegion(region);
//            this.table.getBlocCourant().addBloc(bloc);
            this.table.setBlocCourant(bloc); this.table.addAllBloc(bloc);
        }


    }

    @Override
    public void exitForExp(parser.exprParser.ForExpContext ctx) {
        this.table.setBlocCourant(this.table.getBlocCourant().getBlocEnglobant());
        imbrication--;
    }

    @Override
    public void enterWhileExp(parser.exprParser.WhileExpContext ctx) {
        String cond = ctx.expr(0).getText();
        whileBloc bloc = new whileBloc(this.table.getBlocCourant(), cond);
        this.table.addAllBloc(bloc);
        this.table.setBlocCourant(bloc);
    }

    @Override
    public void exitWhileExp(parser.exprParser.WhileExpContext ctx) {
        this.table.setBlocCourant(this.table.getBlocCourant().getBlocEnglobant());
    }

    @Override
    public void enterExprLValueBis(parser.exprParser.ExprLValueBisContext ctx) {
        // TODO checker si chaque item de parse est bien connu dans le bloc courant
        // String expr = ctx.expr().getText();
        // String[] parse = parseExpr(expr);
    }

    public tableDeSymboles getTable() {
        return table;
    }

    @Override
    public void enterAssignment(exprParser.AssignmentContext ctx) {

        String left = ctx.lvalue().getText();
        String right = ctx.expr().getText();
        if (left.contains("[")) { //cas d'un assignement d'une valeur d'un array
            String id = left.split("\\[")[0];

            String index = left.split("\\[")[1].split("\\]")[0];
            BasicBloc bloc = this.table.getBlocCourant();
            if(!exprIsInt(index)) {
                this.table.addErreur("Erreur : L'expression " + index + " n'est pas un entier");
            } else 
            if (this.table.getBlocCourant().searchSymbol(id) == null) {
                this.table.addErreur("Erreur : Variable " + id + " non déclarée");
            } else 
            if (this.table.getBlocCourant().searchSymbol(index) == null && !exprIsInt(index)) {
                this.table.addErreur("Erreur : Variable " + index + " non déclarée");
            } else 
            if (this.table.getBlocCourant().searchSymbol(id) != null && exprIsInt(index)) {
                try {
                    ((arraySymbol) this.table.getBlocCourant().searchSymbol(id)).getType();
                } catch (Exception e) {
                    this.table.addErreur("Erreur : Variable " + id + " n'est pas un array");
                }
                if (!exprIsInt(index)) {
                    this.table.addErreur("Erreur : Variable " + index + " n'est pas un int");
                }
                if (right.contains("(")) {
                    String idR = right.split("\\(")[0];
                    if (this.table.getBlocCourant().searchSymbol(idR) == null) {
                        this.table.addErreur("Erreur : Fonction " + idR + " non déclarée");
                    } else {
                        if (!Objects.equals(((fonctionSymbol) this.table.getBlocCourant().searchSymbol(idR)).getTypeRetour().getName(), ((varSymbol) this.table.getBlocCourant().searchSymbol(id)).getType().getName())) {
                            this.table.addErreur("Erreur : le type de " + id + " (" + ((varSymbol) this.table.getBlocCourant().searchSymbol(id)).getType().getName() + ")" + " ne match pas avec le type de retour de la fonciton " + idR + " (" + ((fonctionSymbol) this.table.getBlocCourant().searchSymbol(idR)).getTypeRetour().getName() + ")");
                        }
                    }
                }
            }
        } else {
            BasicBloc bloc = this.table.getBlocCourant();
            boolean findId = false;
            while (bloc != null) {

                if (bloc.getSymbol(left) != null || exprIsInt(left)) {
                    findId = true;
                }
                bloc = bloc.getBlocEnglobant();
            }
            if (!findId) {
                this.table.addErreur("Erreur : variable " + left + " non déclarée");
            }
        }

    }

    @Override
    public void enterBool(parser.exprParser.BoolContext ctx) {
        String expression = ctx.getText();
        if (expression.contains("&")) {
            int index = expression.indexOf("&");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (&) : " + expression);
            }
        }
        if (expression.contains("|")) {
            int index = expression.indexOf("|");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (ou) : " + expression);
            }
        }

    }

    @Override
    public void enterEqual(parser.exprParser.EqualContext ctx) {
        String expression = ctx.getText();
        if (expression.contains("=")) {
            int index = expression.indexOf("=");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb)&&exprIsString(fin))) && gotType(deb) && gotType(fin) && (deb.charAt(deb.length()-1) !=('<')&&deb.charAt(deb.length()-1) !=('>')&&deb.charAt(deb.length()-1) !=(':'))) {
                Symbol present2 = this.table.getBlocCourant().searchSymbol(deb);
                Symbol present3 = this.table.getBlocCourant().searchSymbol(fin);
                if (present2!=null && present3!= null &&  present2 instanceof arraySymbol && present3 instanceof arraySymbol) {
                    if(!((arraySymbol) present2).getTypeDesElements().getName().equals(((arraySymbol) present3).getTypeDesElements().getName())) {
                        this.table.addErreur("Erreur : Typage d'opération (=) : " + expression);
                    }
                }
            } 
        }
        if (expression.contains("<>")) {
            int index = expression.indexOf("<>");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 2);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb)&&exprIsString(fin))) && gotType(deb) && gotType(fin)) {
                Symbol present2 = this.table.getBlocCourant().searchSymbol(deb);
                Symbol present3 = this.table.getBlocCourant().searchSymbol(fin);
                if (present2!=null && present3!= null &&  present2 instanceof arraySymbol && present3 instanceof arraySymbol) {
                    if(!((arraySymbol) present2).getTypeDesElements().getName().equals(((arraySymbol) present3).getTypeDesElements().getName())) {
                        this.table.addErreur("Erreur : Typage d'opération (<>) : " + expression);
                    }
                }
            }
        }
        if (expression.contains("<")) {
            int index = expression.indexOf("<");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb)&&exprIsString(fin))) && gotType(deb) && gotType(fin)&&fin.charAt(0) !=('>')) {
                this.table.addErreur("Erreur : Typage d'opération (<) : " + expression);
            }
        }
        if (expression.contains(">")) {
            int index = expression.indexOf(">");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb)&&exprIsString(fin))) && gotType(deb) && gotType(fin)&&deb.charAt(deb.length()-1) !=('<')) {
                this.table.addErreur("Erreur : Typage d'opération (>) : " + expression);
            }
        }
        if (expression.contains("<=")) {
            int index = expression.indexOf("<=");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 2);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb) && exprIsString(fin))) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (<=) : " + expression);
            }
        }
        if (expression.contains(">=")) {
            int index = expression.indexOf(">=");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 2);
            if (((!exprIsIntFull(deb) || !exprIsIntFull(fin))&&!(exprIsString(deb)&&exprIsString(fin))) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (>=) : " + expression);
            }
        }
    }

    @Override
    public void enterPlus(parser.exprParser.PlusContext ctx) {
        String expression = ctx.getText();
        if (expression.contains("+")) {
            int index = expression.indexOf("+");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (+) : " + expression);
            }
        }
        if (expression.contains("-")) {
            int index = expression.indexOf("-");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (-) : " + expression);
            }
        }
    }

    @Override
    public void enterMult(parser.exprParser.MultContext ctx) {
        String expression = ctx.getText();
        if (expression.contains("/")) {
            int index = expression.indexOf("/");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (/) : " + expression);
            }
            if (expression.length() > index && expression.charAt(index + 1) == '0') {
                if (!(expression.length() > (index + 2))) {
                    this.table.addErreur("Erreur : Division par 0 : " + expression);
                }
            }
        }
        if (expression.contains("*")) {
            int index = expression.indexOf("*");
            String deb = expression.substring(0, index);
            String fin = expression.substring(index + 1);
            if ((!exprIsIntFull(deb) || !exprIsIntFull(fin)) && gotType(deb) && gotType(fin)) {
                this.table.addErreur("Erreur : Typage d'opération (*) : " + expression);
            }
        }
    }


    public String getRawType(String aliasType) {
        
        if (aliasType.equals("int")) {
            return "int";
        }
        if (aliasType.equals("string")) {
            return "string";
        }
        // on cherche le type dans la table des symboles
    
        if(!aliasType.equals("int") && aliasType != "string") {
            return getRawType(((BasicType)this.table.getType(aliasType)).getAlias());
        }
        return null;
    }
    


    @Override
    public void enterCallExp(exprParser.CallExpContext ctx) { 

        if (!(this.table.getBlocCourant().searchSymbol(ctx.getText().split("\\(")[0]) instanceof fonctionSymbol)) { // si la fonction n'existe pas
            this.table.addErreur("Erreur : La fonction " + ctx.getText().split("\\(")[0] + " n'existe pas");          // on ajoute une erreur
        }
        String entrees = ctx.getText().substring(ctx.getText().indexOf("(") + 1, ctx.getText().indexOf(")"));       // on récupère les arguments de la fonction
        if (entrees.length() > 0) {                                                                                 // si il y a des arguments
            String[] entree = entrees.split(",");                                                                 // on les sépare
            if (entree.length != ((fonctionSymbol) this.table.getBlocCourant().searchSymbol(ctx.getText().split("\\(")[0])).getTypeEntrees().size()) { // si le nombre d'arguments est incorrect
                this.table.addErreur("Erreur : Le nombre d'arguments de la fonction " + ctx.getText().split("\\(")[0] + " est incorrect");            // on ajoute une erreur
            } else {
                for (int i = 0; i < entree.length; i++) {                                                       // sinon on vérifie le typage des arguments

                    if (!isVar(entree[i])) {                                                                // si l'argument n'est pas une variable
                        if (this.table.getBlocCourant().searchSymbol(entree[i]) == null && !exprIsInt(entree[i]) && !exprIsString(entree[i])) { // si l'argument n'est pas un entier ou une chaine de caractère
                            this.table.addErreur("Erreur : La variable " + entree[i] + " n'existe pas");        // on ajoute une erreur
                        }
                    else{
                        //si l'argument n'est pas du même type que le type précisé dans la déclaration de la fonction on ajoute une erreur
                        String[] arg = ((fonctionSymbol) this.table.getBlocCourant().searchSymbol(ctx.getText().split("\\(")[0])).getTypeEntrees().get(i).toString().split(":");
                        //On enlève tous les espaces pour pouvoir comparer les types
                        String argtype = arg[1].replaceAll("\\s+","");
                        
                        if (exprIsInt(entree[i]) && getRawType(argtype)!= "int") {
                            this.table.addErreur("Erreur : Le type de l'argument " + entree[i] + " est incorrect. Il devrait être de type " + argtype + ".");
                        }
                        if (exprIsString(entree[i]) && getRawType(argtype)!= "string") {
                            this.table.addErreur("Erreur : Le type de l'argument " + entree[i] + " est incorrect. Il devrait être de type " + argtype + ".");
                        }
                    }
//                    } else {
//                        this.table.addErreur("Erreur La variable " +"nathan" + " n'existe pas");
//                        if (this.table.getBlocCourant().searchSymbol(entree[i]).getClass() != ((fonctionSymbol) this.table.getBlocCourant().searchSymbol(ctx.getText().split("\\(")[0])).getTypeEntrees().get(i).getClass()) {
//                            this.table.addErreur("Erreur La variable " + entree[i] + " n'existe pas2");
//                        }
//                    } //TODO : pb avec la gramar dans declaration de fonction on doit séparer les différents types d'entrées avec une virgule
                    }
                }
            }

        }
    }
}
