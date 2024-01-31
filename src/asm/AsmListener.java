package asm;

import parser.exprBaseListener;
import parser.exprParser;
import symtab.BasicBloc;
import symtab.fonctionBloc;
import symtab.tableDeSymboles;
import symtab.trueListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/*
 *
 * TODO : faire les if imbriqués genre la suite if à mettre au bon endroit
 *  TODO : faire les while imbriqués
 *  */

public class AsmListener extends exprBaseListener {

    private String head = "";
    private String content = "";
    private String foot = "";
    private ArrayList<String> buffers = new ArrayList<String>();
    private int imbrication = 1;
    private int region = 1;
    private int uniqueLabel = 0;
    ArrayList<Integer> takenRegisters = new ArrayList<Integer>();
    private ArrayList<Integer> parenthesisIndex = new ArrayList<>(); // le i-ème élément et l'index du registre pour la
    // i-ème parenthèse

    private tableDeSymboles table;

    private HashMap<String, Integer> variablesOffset = new HashMap<>();
    private int offset = 0;

    public AsmListener(trueListener symtab) {
        super();
        this.table = symtab.getTable();

    }

    ArrayList<AsmBloc> blocs = new ArrayList<>();

    public void produire(String expr, String buffer, String ctx) {

        boolean flag = false;
        for (AsmBloc bloc : blocs) {
            if (bloc.getTiger().contains(ctx)) {
                if (Objects.equals(buffer, "h"))
                    bloc.addAsmHead(expr);
                else if (Objects.equals(buffer, "f"))
                    bloc.addAsmTail(expr);
                else if (Objects.equals(buffer, "c")) {
                    bloc.addAsmBody(expr);
                }

                flag = true;
            }
        }
        if (!flag) {
            if (buffer == "h") {
                this.head += expr;
            } else if (buffer == "f") {
                this.buffers.add("\n" + expr);
            } else {
                this.content += expr;
            }
        }
    }

    public void ChainageR11(String expr, String buffer, String ctx) {


        for (AsmBloc bloc : blocs) {

            if (bloc.getTiger().contains(ctx)) {
                if (Objects.equals(buffer, "h"))
                    bloc.addAsmHead(expr);
                else if (Objects.equals(buffer, "f"))
                    bloc.addAsmTail(expr);
                else if (Objects.equals(buffer, "c")) {
                    bloc.addAsmBody(expr);
                }

            }
        }

    }

    @Override
    public void enterProgram(exprParser.ProgramContext ctx) {
        /* petit commentaire quand on rentre dans le code */
        this.content += ";Enter Program \n";
        //HEAP_BASE c'est pour gérer les strings
        produire("HEAP_BASE\t EQU 0x10000\nMOV R11, R13 ;Mise à jour du Base Pointer\nMOV R12, #HEAP_BASE ;On met l'adresse du tas dans R12\n", "c", ctx.getText());
    }

    public BasicBloc getCurrentBloc(int region) {
        // TODO : faire une vrai fonction qui cherche l'Offset du symbole et faire bien
        // le boulot

        BasicBloc empty = new fonctionBloc("empty", this.table.getBlocCourant());

        for (int i = 0; i < this.table.allBlocs.size(); i++) {
            if (this.table.allBlocs.get(i).getRegion() == region) {
                return this.table.allBlocs.get(i);
            }
        }
        return empty;

    }

    @Override
    public void exitProgram(parser.exprParser.ProgramContext ctx) {
        /* petit commentaire quand on a fini */
        getCurrentBloc(1);
        for (AsmBloc bloc : blocs) {

            produire("\n" + bloc.getAsm(), "f", ctx.getText());

        }
        produire("END\n", "c", ctx.getText());
        produire(";Exit Program \n", "c", ctx.getText());

        for (String buffer : this.buffers) {
            this.foot += buffer;
        }

        Tools.writeToFile("src/asm/out.arm", this.head + "\n" + this.content + "\n" + this.foot);
    }

    @Override
    public void enterVariable_declaration(exprParser.Variable_declarationContext ctx) {
        System.out.println("Variable declaration before : " + ctx.getText());
        String expr = ctx.getRuleContext().getText().split(":=")[1];

        if (expr.contains(" of ")) {
            /* Dans le cas d'un array */

            String typeEtTaille = expr.split("of")[0];
            System.out.print(typeEtTaille);
            String type = typeEtTaille.substring(0, typeEtTaille.indexOf("["));
            String taille = typeEtTaille.substring(typeEtTaille.indexOf("[") + 1, typeEtTaille.indexOf("]"));

            String valeurInitiale = expr.split("of")[1];

            /*
             * On suppose que la taille est bien direct un entier, pareil pour
             * l'initialisation
             */

            int trueTaille = Integer.parseInt(taille.replace(" ", ""));
            int trueValeurInitiale = Integer.parseInt(valeurInitiale.replace(" ", ""));

            /*
             * Vérif à faire sur le type ?
             * Type typeDArray = this.table.getType(type);
             */

            for (int i = 0; i < trueTaille; i++) {
                produire("MOV\tR0, #" + trueValeurInitiale + " ;Tentative d'ajout d'une déclaration de variable\n",
                        "c", ctx.getText());
                produire("STMFD\tR13!,{R0}\n", "c", ctx.getText());
                this.variablesOffset.put(ctx.getText().split(":")[0].substring(3),
                        this.table.searchSymbolOffset(ctx.getText().split(":")[0].substring(3), imbrication, region)
                                + this.offset);
                this.offset += 4;
                System.out.println("Variable declaration after : " + this.variablesOffset + " " + this.offset);
            }

        } else if (expr.contains("{")) {
            /* Dans le cas d'un record */

        } else {
            /* Entier ou string */
            /* On différencie ça en faisant un try/catch */
            try {
                int valeur = Integer.parseInt(expr);
                valeur = valeur + 1;
                /* Ne discrimine pas plus, juste pour pas avoir le "variable not used" */
                produire("MOV\tR0, #" + ctx.getText().split("=")[1]
                        + " ;Déclaration de la variable " + ctx.getRuleContext().getText().split(":=")[0].substring(3)
                        + " \n", "c", ctx.getText());
                produire("STMFD\tR13!,{R0} ;Ajout de la variable à la pile\n", "c", ctx.getText());

                this.offset -= 4;
                this.variablesOffset.put(ctx.getText().split(":")[0].substring(3),
                        this.table.searchSymbolOffset(ctx.getText().split(":")[0].substring(3), imbrication, region)
                                + this.offset);

                System.out.println("Variable declaration after : " + this.variablesOffset + " " + this.offset);
            } catch (NumberFormatException e) {
                /*Cas d'un string */
                produire("STMFD\tR13!, {R12} ;Sauvegarde de la base actuelle du tas\n", "c", ctx.getText());
                System.out.println("On passe par un string");
                String valeur = expr;
                for (int i = 0; i < valeur.length(); i++) {
                    if (valeur.charAt(i) != '\"') {
                        produire("MOV\tR0, #" + (int) valeur.charAt(i) + " ;On met la valeur du caractère " + valeur.charAt(i) + " dans R0\n", "c", ctx.getText());
                        produire("STR\tR0, [R12] ;On met la valeur dans le tas\n", "c", ctx.getText());
                        produire("ADD\tR12, R12, #-4 ;On incrémente la base du tas\n", "c", ctx.getText());
                    }

                }
                produire("MOV\tR0, #128 ;On met la valeur du caractère de fin de string dans R0\n", "c", ctx.getText());
                produire("STR\tR0, [R12] ;On met la valeur dans le tas\n", "c", ctx.getText());
                produire("ADD\tR12, R12, #-4 ;On incrémente la base du tas\n", "c", ctx.getText());

            } catch (Exception e) {
                System.out.println("Y'a un souci");
            }
        }

        // TODO : marche pour les int mais à modifier pour les tableaux etc

    }

    @Override
    public void enterPrint(parser.exprParser.PrintContext ctx) {
        produire("", "c", ctx.getText());
    }


    @Override
    public void enterIfThen(exprParser.IfThenContext ctx) {
        String cond = ctx.getText().split("then")[0].substring(2);
        String then = ctx.getText().split("then")[1];

        produire(";On calcule la condition du if\n", "c", ctx.getText());
        String Condcrv = calculRvalue(cond, "c", ctx.getText());
        produire("CMP\t" + Condcrv.substring(1, Condcrv.length() - 1) + ", #1\n", "c", ctx.getText());
        uniqueLabel++;
        produire("BEQ if" + uniqueLabel + "\n", "c", ctx.getText());
        produire("BNE suiteif" + uniqueLabel + "\n", "c", ctx.getText());
        produire("suiteif" + uniqueLabel + "\n", "c", ctx.getText());
        ArrayList<String> asm_code_if = new ArrayList<String>(3);
        asm_code_if.add("");
        asm_code_if.add("");
        asm_code_if.add("");
        /* produire("B for" + uniqueLabel + "\n", "c", ctx.getText()); */
        AsmBloc bloc = new AsmBloc(cond, asm_code_if, ctx.getText(), imbrication, "if");
        bloc.setRegion(region);
        bloc.addAsmHead("if" + uniqueLabel + "\n");
        bloc.addAsmTail("B suiteif" + uniqueLabel + "\n");
        blocs.add(bloc);
        for (int i = 0; i < blocs.size() - 1; i++) {
            if (blocs.get(i).getTiger().contains(ctx.getText())) {
                blocs.get(i).updateTiger(ctx.getText());
            }

        }

    }

    @Override
    public void enterIfThenElse(exprParser.IfThenElseContext ctx){
        String cond = ctx.getText().split("then")[0].substring(2);
        String then = ctx.getText().split("then")[1].split("else")[0];
        String else_ = ctx.getText().split("else")[1];

        produire(";On calcule la condition du if\n", "c", ctx.getText());
        String Condcrv = calculRvalue(cond, "c", ctx.getText());
        produire("CMP\t" + Condcrv.substring(1, Condcrv.length() - 1) + ", #1\n", "c", ctx.getText());
        uniqueLabel++;
        produire("BEQ if" + uniqueLabel + "\n", "c", ctx.getText());
        produire("BNE else" + uniqueLabel + "\n", "c", ctx.getText());
        produire("endif" + uniqueLabel + "\n", "c", ctx.getText());
        ArrayList<String> asm_code_if = new ArrayList<String>(3);
        asm_code_if.add("");
        asm_code_if.add("");
        asm_code_if.add("");
        /* produire("B for" + uniqueLabel + "\n", "c", ctx.getText()); */
        AsmBloc bloc = new AsmBloc(cond, asm_code_if, then, imbrication, "if");
        bloc.setRegion(region);
        bloc.addAsmHead("if" + uniqueLabel + "\n");
        bloc.addAsmTail("B endif" + uniqueLabel + "\n");
        blocs.add(bloc);



        ArrayList<String> asm_code_else = new ArrayList<String>(3);
        asm_code_else.add("");
        asm_code_else.add("");
        asm_code_else.add("");
        /* produire("B for" + uniqueLabel + "\n", "c", ctx.getText()); */
        AsmBloc bloc_else = new AsmBloc(cond, asm_code_else, else_, imbrication, "else");
        bloc_else.setRegion(region);
        bloc_else.addAsmHead("else" + uniqueLabel + "\n");
        bloc_else.addAsmTail("B endif" + uniqueLabel + "\n");
        blocs.add(bloc_else);
        for (int i = 0; i < blocs.size() - 1; i++) {
            if (blocs.get(i).getTiger().contains(ctx.getText())) {
                blocs.get(i).updateTiger(ctx.getText());
            }

        }
    }

    @Override
    public void enterWhileExp(exprParser.WhileExpContext ctx) {
        imbrication++;
        region++;
        String cond = ctx.getText().split("do")[0].substring(5);
        produire("; \t calcule la condition du while\n", "c", ctx.getText());

        uniqueLabel++;
        produire("B while_start" + uniqueLabel + "\n", "c", ctx.getText());

        ArrayList<String> asm_code_while = new ArrayList<String>(3);
        asm_code_while.add("");
        asm_code_while.add("");
        asm_code_while.add("");
        /* produire("B for" + uniqueLabel + "\n", "c", ctx.getText()); */
        AsmBloc bloc = new AsmBloc(cond, asm_code_while, ctx.getText(), imbrication, "while");
        bloc.setRegion(region);

        bloc.addAsmHead("while_start" + uniqueLabel + "\n");
        produire("while_end" + uniqueLabel + "\n", "c", ctx.getText());
        bloc.addAsmTail("B while_start" + uniqueLabel + "\n");
        blocs.add(bloc);
        for (int i = 0; i < blocs.size() - 1; i++) {
            if (blocs.get(i).getTiger().contains(ctx.getText())) {
                blocs.get(i).updateTiger(ctx.getText());
            }
            System.out.println("bloc for : " + blocs.get(i).getTiger());
        }
        String Condcrv = calculRvalue(cond, "c", ctx.getText());
        produire("CMP\t" + Condcrv.substring(1, Condcrv.length() - 1) + ", #1\n", "c", ctx.getText());
        produire("BNE while_end" + uniqueLabel + "\n", "c", ctx.getText());
        this.uniqueLabel++;
    }

    @Override
    public void exitWhileExp(exprParser.WhileExpContext ctx) {
        imbrication--;

    }

    @Override
    public void enterForExp(exprParser.ForExpContext ctx) {

        imbrication++;
        region++;
        String cond = ctx.getText().split("do")[0].substring(3);
        String init = cond.split("to")[0];

        //TODO : on peut pas mettre calculRvalue(init.split(":=")[1]) pour avoir des conditions plus sophistiquées ?
        String initValeur = init.split(":=")[1];
        uniqueLabel++;
        String initVar = init.split(":=")[0];

        // ! déclaration de la variable de début de la boucle


        try {

            /* Ne discrimine pas plus, juste pour pas avoir le "variable not used" */
            produire("MOV\tR0, #" + initValeur
                    + " ;Déclaration de la variable " + initVar
                    + " \n", "c", ctx.getText());
            produire("STMFD\tR13!,{R0} ;Ajout de la variable à la pile\n", "c", ctx.getText());

            this.offset -= 4;
            System.out.println("NOE -------------- " + initVar + " " + this.offset + " " + this.table.searchSymbolOffset(initVar, imbrication, region));
            this.variablesOffset.put(initVar,
                    this.table.searchSymbolOffset(initVar, imbrication, region) + this.offset);

            System.out.println("Variable declaration after : " + this.variablesOffset + " " + this.offset);
        } catch (NumberFormatException e) {
            /* C'est une string */
        } catch (Exception e) {
            System.out.println("Y'a un souci");
        }
        // ! fin de la déclaration de la variable de début de la boucle
        produire("B for" + uniqueLabel + "\n", "c", ctx.getText());
        boolean forend = false;
        for (int i = 0; i < this.blocs.size(); i++) {
            if (blocs.get(i).getTiger().contains(ctx.getText())) {
                produire("for_end" + uniqueLabel + "\n", "c", ctx.getText());
                // ! je remet la variable à sa valeur initiale
                forend = true;
                String lvalue = init.split(":=")[0];
                String rvalue = init.split(":=")[1];

                String result = calculRvalue(rvalue, "c", ctx.getText());

                if (this.variablesOffset.containsKey(lvalue)) {
                    produire("STR " + result.substring(1, result.length() - 1) + ", [R11, #"
                            + this.variablesOffset.get(lvalue)
                            + "] ;Sauvegarde du résultat\n", "c", ctx.getText());
                }
                // ! fin reset variable
                blocs.get(i).updateTiger(ctx.getText());
                reinitializeRegisters(takenRegisters);
                parenthesisIndex.clear();

            }
        }
        if (!forend) {
            produire("for_end" + uniqueLabel + "\n", "c", ctx.getText());
        }

        try {
            Integer.parseInt(initValeur);
            /*
             * int registreDebutBoucle = maxList(takenRegisters) + 1;
             * produire("MOV\tR" + (maxList(takenRegisters) + 1) + ", #" + initValeur +
             * " ;Déclaration de la variable de début de la boucle " + " \n", "c",
             * ctx.getText());
             * takenRegisters.add(maxList(takenRegisters) + 1);
             */

            cond = initVar + "=" + cond.split("to")[1];
            // * produire("STMFD\tR13!,{R0}\n", "c", ctx.getText());

            ArrayList<String> asm_code = new ArrayList<String>(3);
            asm_code.add("");
            asm_code.add("");
            asm_code.add("");
            /* produire("B for" + uniqueLabel + "\n", "c", ctx.getText()); */
            AsmBloc bloc = new AsmBloc(cond, asm_code, ctx.getText(), imbrication, "for");
            bloc.setRegion(region);
            bloc.addAsmHead("for" + uniqueLabel + "\n");
            blocs.add(bloc);
            String Condcrv = calculRvalue(cond, "h", ctx.getText().split("do")[0].substring(3));
            blocs.get(blocs.size() - 1).updateTiger(ctx.getText().split("do")[0].substring(3));

            System.out.println("ici\n" + bloc.getTiger());
            bloc.addAsmHead("CMP\t" + Condcrv.substring(1, Condcrv.length() - 1) + ", #1\n" + "BEQ for_end"
                    + uniqueLabel + "\n");

            // ! ici je vais faire i=i+1
            bloc.addTiger(initVar + ":=" + initVar + "+1");
            String result = calculRvalue(initVar + "+1", "f", initVar + ":=" + initVar + "+1");
            if (this.variablesOffset.containsKey(initVar)) {

                produire("STR " + result.substring(1, result.length() - 1) + ", [R11, #"
                        + this.variablesOffset.get(initVar)
                        + "] ;Sauvegarde du résultat\n", "f", initVar + ":=" + initVar + "+1");
            }
            reinitializeRegisters(takenRegisters);
            parenthesisIndex.clear();
            bloc.updateTiger(initVar + ":=" + initVar + "+1");

            // ! fin de i=i+1
            bloc.addAsmTail("B for" + uniqueLabel + "\n");

            String contenu = ctx.getText().substring(ctx.getText().indexOf("do") + 2);
            for (int i = 0; i < blocs.size() - 1; i++) {
                if (blocs.get(i).getTiger().contains(contenu)) {
                    blocs.get(i).updateTiger(contenu);
                }
                System.out.println("bloc for : " + blocs.get(i).getTiger());
            }

            System.out.println("bloc for : " + ctx.getText());

        } catch (NumberFormatException e) {
            // TODO handle exception en calculRValue
        } catch (Exception e) {
            System.out.println("Erreur en calculant les bornes d'une boucle for" + e);
        }

    }

    @Override
    public void exitForExp(exprParser.ForExpContext ctx) {
        imbrication--;
        uniqueLabel++;

    }

    @Override
    public void enterAssignment(exprParser.AssignmentContext ctx) {

        // for (int i = 0; i < blocs.size(); i++) {
        //
        // if (blocs.get(i).get(2).contains(ctx.getText())) {
        // blocs.get(i).set(1, blocs.get(i).get(1) + "\t;" +
        // ctx.getText().split(":=")[0] + " à faire" + "\n");
        // /*
        // * faut aller chercher la valeur des var mise en jeu
        // * et les modifier en fonction
        // *
        // */
        // }
        // }
        System.out.println("vars" + this.variablesOffset.toString());

        System.out.println("Assignment : " + ctx.getText());
        String lvalue = ctx.getText().split(":=")[0];
        String rvalue = ctx.getText().split(":=")[1];
        int imbrication_V = 0;
        int imbrication_B = 0;
        String result = calculRvalue(rvalue, "c", ctx.getText());
        if (this.table.BlocGlobal.getSymbol(lvalue) != null) {
            imbrication_V = 1;
        }

        for (BasicBloc bloc : this.table.allBlocs) {

            if (bloc.getSymbol(lvalue) != null) {

                if (bloc.getImbrication() > imbrication_V & bloc.getImbrication() <= this.imbrication) {
                    imbrication_V = bloc.getImbrication();
                }

            }
            
            if (bloc.getImbrication() <= this.imbrication & bloc.getImbrication() > imbrication_B) {

                for (AsmBloc bloc2 : blocs) {
                    
                    if (bloc.getRegion() == bloc2.getRegion() & bloc2.getCategory().equals("function")) {
                        imbrication_B = bloc.getImbrication();
                        break;
                    }
                }

            }
        }
        System.out.println("nathan" + imbrication_V + " " + imbrication_B + " " + lvalue);
        for (int i = imbrication_V; i < imbrication_B; i++) {
            System.out.println("nathan" + imbrication_V + " " + imbrication_B);
            ChainageR11("ldr R11,[r13,#" + (44 - this.offset) + "] ; on va ds le bloc au dessus\n", "c", ctx.getText());
        }

        if (this.variablesOffset.containsKey(lvalue)) {

            produire("STR " + result.substring(1, result.length() - 1) + ", [R11, #" + this.variablesOffset.get(lvalue)
                    + "] ;Sauvegarde du résultat\n", "c", ctx.getText());
        }
 if (imbrication_V < imbrication_B) {

                    ChainageR11("ADD R11,R13,#" + (-this.offset) + " ; on revient ds le bloc actuel\n", "c", ctx.getText());
                }

        reinitializeRegisters(takenRegisters);
        parenthesisIndex.clear();
    }

    @Override
    public void exitAssignment(exprParser.AssignmentContext ctx) {


        for (AsmBloc bloc : blocs) {
            if (bloc.getTiger().contains(ctx.getText())) {
                bloc.updateTiger(ctx.getText());
            }
        }
    }

    /* @Override
    public void enterFieldDec(exprParser.FieldDecContext ctx) {
        // TODO Auto-generated method stub
        String id = ctx.id().getText();
        String type_id = ctx.type_id().getText();

        if (this.variablesOffset.containsKey(id)) {
            System.out.println("Variable déjà déclarée");
        } else {

            this.variablesOffset.put(id, this.offset);
            this.offset -= 4;

            
            ArrayList<String> asm_code = new ArrayList<String>(3);
            asm_code.add("STMFD\tR13!,{R0}\n");
            AsmBloc bloc = new AsmBloc("args" + this.uniqueLabel, asm_code, ctx.getText(), imbrication, "arguments");
            blocs.add(bloc);

        }

        System.out.println("id : " + id + " type_id : " + type_id);
        System.out.println("dictionnary : " + this.variablesOffset);
    }
    @Override
    public void exitFieldDec(exprParser.FieldDecContext ctx) {
        for (AsmBloc bloc : blocs) {
            if (bloc.getTiger().contains(ctx.getText())) {
                bloc.updateTiger(ctx.getText());
            }
        }
    } */

    @Override
    public void enterFunction_declaration(exprParser.Function_declarationContext ctx) {

        this.offset = 0;
        imbrication++;
        region++;
        // Capturer les informations de la fonction
        String funcName = ctx.getText();
        // funcName is between "function" and "("
        funcName = funcName.substring(funcName.indexOf("function") + 8, funcName.indexOf("("));
        String ctxWithoutFuncName = ctx.getText().substring(ctx.getText().indexOf("(") + 1);
        System.out.println("funcName : " + funcName);
        int funcOffset = this.offset;
        ArrayList<String> asm_code = new ArrayList<String>(3);
        // asm_code.add(funcName + "function ;On entre dans la fonction\n");
        asm_code.add(funcName + "function\n");
        asm_code.add("STMFD r13! , {r0-r10,r11,LR}\nMOV r11, r13\n");
        asm_code.add("");
        String args = ctx.getText();
        args = args.substring(args.indexOf("(") + 1, args.indexOf(")"));
        String[] argList = args.split(",");

        int numArgs = argList.length;

        

        for (int i = 0; i < numArgs; i++) {

            this.variablesOffset.put(argList[i].split(":")[0], i * 4);

            System.out.println("Variable declaration after ferfzef : " + this.variablesOffset + " " + this.offset);
        }

        String finDeFonc = "";
        for(int i=0;i<argList.length;i++){
           
        }
        AsmBloc bloc = new AsmBloc(funcName, asm_code, ctxWithoutFuncName, imbrication, "function");
        bloc.setRegion(region);
        

        bloc.addAsmTail("MOV r13, r11\nLDMFD r13!, {r0-r10,r11,PC}\n");
        blocs.add(bloc);

        // Ajouter la fonction à la table des symboles
        this.variablesOffset.put(funcName, funcOffset);
        uniqueLabel++;
    }

    @Override
    public void exitFunction_declaration(exprParser.Function_declarationContext ctx) {
        imbrication--;


    }

    @Override
    public void enterCallExp(parser.exprParser.CallExpContext ctx) {
        // Get the function name
        String funcName = ctx.getText();
        // funcName is between "function" and "("
        funcName = funcName.substring(0, funcName.indexOf("("));
        System.out.println("funcName oui oui ici : " + funcName);
        // Get the number of arguments
        String args = ctx.getText();
        // numArgs is between "(" and ")"
        args = args.substring(args.indexOf("(") + 1, args.indexOf(")"));
        String[] argList = args.split(",");
        int numArgs = argList.length;

        // Retrieve the function's offset from the symbol table
        int funcOffset = this.variablesOffset.get(funcName);

        // Calculate the amount of stack space needed for the arguments
        int argStackSpace = numArgs * 4;

        // Push the arguments onto the stack
        for (int i = 0; i < numArgs; i++) {
            String argReg = "r" + i;
            String argExpr = argList[i];
            System.out.println("argReg : " + argReg);
            System.out.println("argExpr : " + argExpr);
            String reg = calculRvalue(argExpr, "c", ctx.getText());
            reg = reg.substring(1, reg.length() - 1);
            System.out.println("reg : " + reg);

        }

       
        // Call the function
        produire("BL " + funcName + "function ; Call function " + funcName + "\n", "c", ctx.getText());
        produire("end" + funcName + "function\n", "c", ctx.getText());


        // Clean up the stack
        // produire("ADD r13, r13, #" + argStackSpace + " ; Pop arguments off the
        // stack\n", "c", ctx.getText());

    }

    @Override
    public void exitCallExp(parser.exprParser.CallExpContext ctx) {
        for (AsmBloc bloc : blocs) {
            if (bloc.getAsmCode().get(0).equals(ctx.getText().substring(0, ctx.getText().indexOf("(")) + "function\n")) {
                bloc.addAsmTail("B end" + ctx.getText().substring(0, ctx.getText().indexOf("(")) + "function\n");
            }
        }
        for (AsmBloc bloc : blocs) {
            if (bloc.getTiger().contains(ctx.getText())) {
                bloc.updateTiger(ctx.getText());
            }
        }

    }

    public String calculRvalue(String rvalue, String buffer, String ctx) {

        // TODO :gérer les cas où il y a plus de 9 registres en sauvegardant les valeurs
        if (rvalue.contains("(")) {
            // Find the innermost set of parentheses
            int startIndex = rvalue.lastIndexOf("(");
            int endIndex = rvalue.indexOf(")", startIndex);

            // Evaluate the expression inside the parentheses
            String innerExpr = rvalue.substring(startIndex + 1, endIndex);
            String middle = calculRvalue(innerExpr, buffer, ctx);

            // Replace the inner expression with its result
            String newRvalue = rvalue.substring(0, startIndex) + middle +
                    rvalue.substring(endIndex + 1); // on remplace la parenthèse par le registre qui contient la valeur

            // Evaluate the rest of the expression recursively
            String strfinal = calculRvalue(newRvalue, buffer, ctx);

            return strfinal;
        } else if (rvalue.contains("+")) {
            String[] values = rvalue.split("\\+");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "+"), buffer, ctx);

            if (!left.isEmpty() && !right.isEmpty()) { // Vérification si les variables ne sont pas vides
                produire(
                        "ADD " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)
                                + ", " +
                                right.substring(1, right.length() - 1) + " ;Addition\n",
                        buffer, ctx);

                removeTR(maxTR());
            }

            return left;

        } else if (rvalue.contains("-")) {
            String[] values = rvalue.split("-");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "-"), buffer, ctx);

            produire("SUB " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1) + ", "
                    + right.substring(1, right.length() - 1) + " ;Soustraction\n", buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("*")) {
            String[] values = rvalue.split("\\*");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "*"), buffer, ctx);
            addTR(maxTR() + 1);
            String result = "`R" + maxTR() + "`";
            produire("MOV " + result.substring(1, result.length() - 1) + ", #0;Initialisation du compteur\n"
                    + "BL multiply_loop" + this.uniqueLabel + " ;On entre dans la boucle multiplicative\n"
                    + "suite" + this.uniqueLabel + " ;On passe à la suite\n"
                    + "MOV " + left.substring(1, left.length() - 1) + ", " + result.substring(1, result.length() - 1)
                    + " ;On récupère le résultat\n", buffer, ctx);

            produire("multiply_loop" + this.uniqueLabel + " ;Début de la boucle multiplicative\n"
                    + "ADD " + result.substring(1, result.length() - 1) + ", "
                    + result.substring(1, result.length() - 1) + ", " + left.substring(1, left.length() - 1)
                    + " ;i-ème addition de la multiplication\n"
                    + "SUBS " + right.substring(1, right.length() - 1) + ", " + right.substring(1, right.length() - 1)
                    + ", #1 ;Décrémentation du compteur\n"
                    + "BNE multiply_loop" + this.uniqueLabel + " ;Si le compteur n'est pas égal à 0, on recommence\n"
                    + "B suite" + this.uniqueLabel + " ;Sinon on passe à la suite du programme\n", "f", ctx);

            this.uniqueLabel += 1;
            removeTR(maxTR());
            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("/")) {
            String[] values = rvalue.split("/");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "/"), buffer, ctx);
            addTR(maxTR() + 1);
            String result = "`R" + maxTR() + "`";

            produire("MOV " + result.substring(1, result.length() - 1) + ", #0 ;Initialisation du compteur\n"
                    + "BL divide_loop" + this.uniqueLabel + " ;On entre dans la boucle de division\n"
                    + "suite" + this.uniqueLabel + " ;On passe à la suite\n"
                    + "MOV " + left.substring(1, left.length() - 1) + ", " + result.substring(1, result.length() - 1)
                    + " ;On met le résultat de la division dans le bon registre\n", buffer, ctx);

            produire("divide_loop" + this.uniqueLabel + " ;Début de la boucle de division\n"
                    + "SUBS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)
                    + ", " + right.substring(1, right.length() - 1)
                    + " ;i-ème soustraction de la division\n"
                    + "ADD " + result.substring(1, result.length() - 1) + ", "
                    + result.substring(1, result.length() - 1)
                    + ", #1 ;Incrémentation du compteur (qui sera le résultat)\n"
                    + "BGT divide_loop" + this.uniqueLabel + " ;Si on est supérieur à 0, on continue\n"
                    + "ADDS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)
                    + ", #0 ;On teste si la division tombe bien ou non\n"
                    + "SUBNE " + result.substring(1, result.length() - 1) + ", "
                    + result.substring(1, result.length() - 1) + ", #1 ;Si non, on enlève 1 au résultat final\n"
                    + "B suite" + this.uniqueLabel + " ;On passe à la suite du programme\n", "f", ctx);

            this.uniqueLabel += 1;
            removeTR(maxTR());
            removeTR(maxTR());

            return left;
        } else if (rvalue.contains("=")) {
            String[] values = rvalue.split("=");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "="), buffer, ctx);

            produire("CMP " + left.substring(1, left.length() - 1) + ", " + right.substring(1, right.length() - 1)
                    + " ;On compare les deux opérandes (égalité)\n"
                    + "MOVEQ " + left.substring(1, left.length() - 1)
                    + ", #1 ;Si elles sont égales, on passe le registre résultat à 1\n"
                    + "MOVNE " + left.substring(1, left.length() - 1)
                    + ", #0 ;Sinon, on passe le registre résultat à 0\n", buffer, ctx);

            removeTR(maxTR());

            return left;
        } else if (rvalue.contains("<>")) {
            String[] values = rvalue.split("<>");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "<>"), buffer, ctx);
            produire("CMP " + left.substring(1, left.length() - 1) + ", " + right.substring(1, right.length() - 1)
                    + " ;On compare les deux opérandes (différence)\n"
                    + "MOVEQ " + left.substring(1, left.length() - 1)
                    + ", #0 ;Si elles sont égales, on passe le registre résultat à 0\n"
                    + "MOVNE " + left.substring(1, left.length() - 1)
                    + ", #1 ;Sinon, on passe le registre résultat à 1\n", buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains(">")) {
            String[] values = rvalue.split(">");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, ">"), buffer, ctx);
            produire("SUBS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)+", "
                            + right.substring(1, right.length() - 1) + " ;On soustrait les deux opérandes pour tester (>)\n"
                            + "MOVGT " + left.substring(1, left.length() - 1)
                            + ", #1 ;Si le résultat est > 0, on passe le registre résultat à 1\n"
                            + "MOVLE " + left.substring(1, left.length() - 1)
                            + ", #0 ;Sinon, on passe le registre résultat à 0\n",
                    buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("<")) {
            String[] values = rvalue.split("<");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "<"), buffer, ctx);
            produire("SUBS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1) + ","
                            + right.substring(1, right.length() - 1) + " ;On soustrait les deux opérandes pour tester (<)\n"
                            + "MOVLT " + left.substring(1, left.length() - 1)
                            + ", #1 ;Si le résultat est < 0, on passe le registre résultat à 1\n"
                            + "MOVGE " + left.substring(1, left.length() - 1)
                            + ", #0 ;Sinon, on passe le registre résultat à 0\n",
                    buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains(">=")) {
            String[] values = rvalue.split(">=");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, ">="), buffer, ctx);
            produire("SUBS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)
                            + right.substring(1, right.length() - 1) + " ;On soustrait les deux opérandes pour tester (>=)\n"
                            + "MOVGE " + left.substring(1, left.length() - 1)
                            + ", #1 ;Si le résultat est >= 0, on passe le registre résultat à 1\n"
                            + "MOVLT " + left.substring(1, left.length() - 1)
                            + ", #0 ;Sinon, on passe le registre résultat à 0\n",
                    buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("<=")) {
            String[] values = rvalue.split("<=");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "<="), buffer, ctx);
            produire("SUBS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1)
                            + right.substring(1, right.length() - 1) + " ;On soustrait les deux opérandes pour tester (<=)\n"
                            + "MOVLE " + left.substring(1, left.length() - 1)
                            + ", #1 ;Si le résultat est <= 0, on passe le registre résultat à 1\n"
                            + "MOVGT " + left.substring(1, left.length() - 1)
                            + ", #0 ;Sinon, on passe le registre résultat à 0\n",
                    buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("&")) {
            String[] values = rvalue.split("&");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "&"), buffer, ctx);
            produire("CMP " + left.substring(1, left.length() - 1) + ", " + right.substring(1, right.length() - 1)
                    + " ;On compare les deux opérandes (&, on part du principe que les deux opérandes sont bien égales à 0 ou 1)\n"
                    + "MOVEQ " + left.substring(1, left.length() - 1)
                    + ", #1 ;Si les deux opérandes sont égales, on passe le registre résultat à 1\n"
                    + "MOVNE " + left.substring(1, left.length() - 1)
                    + ", #0 ;Sinon on passe le registre résultat à 0\n", buffer, ctx);

            removeTR(maxTR());

            return left;

        } else if (rvalue.contains("|")) {
            String[] values = rvalue.split("\\|");
            String left = calculRvalue(values[0], buffer, ctx);
            String right = calculRvalue(tail(values, 1, "|"), buffer, ctx);
            produire("ADDS " + left.substring(1, left.length() - 1) + ", " + left.substring(1, left.length() - 1) + ", "
                    + right.substring(1, right.length() - 1)
                    + " ;On additionne les deux opérandes (|, on part du principe que les deux opérandes sont bien égales à 0 ou 1)\n"
                    + "MOVGT " + left.substring(1, left.length() - 1)
                    + ", #1 ;Si l'addition est > 0, on passe le registre résultat à 1\n"
                    + "MOVLE " + left.substring(1, left.length() - 1)
                    + ", #0 ;Sinon, on passe le registre résultat à 0\n", buffer, ctx);

            removeTR(maxTR());

            return left;

        } else {

            System.out.println("VALEUR DE RVALUE : " + rvalue + " " + this.takenRegisters);
            if (this.variablesOffset.containsKey(rvalue)) {
                addTR(maxTR() + 1);
                System.out.println("VALEUR DE RVALUE : " + rvalue + " " + this.takenRegisters + " " + this.variablesOffset.get(rvalue));
                int imbrication_V = 0;
                int imbrication_B = 0;

                if (this.table.BlocGlobal.getSymbol(rvalue) != null) {
                    imbrication_V = 1;
                }
                for (BasicBloc bloc : this.table.allBlocs) {
                    if (bloc.getSymbol(rvalue) != null) {

                        if (bloc.getImbrication() > imbrication_V & bloc.getImbrication() <= this.imbrication) {
                            imbrication_V = bloc.getImbrication();
                        }
                    }
                    if (bloc.getImbrication() <= this.imbrication & bloc.getImbrication() > imbrication_B) {
                        for (AsmBloc bloc2 : blocs) {
                            if (bloc.getRegion() == bloc2.getRegion() & bloc2.getCategory().equals("function")) {
                                imbrication_B = bloc.getImbrication();
                                break;
                            }
                        }

                    }
                }
                for (int i = imbrication_V; i < imbrication_B; i++) {
                    System.out.println("nathan" + imbrication_V + " " + imbrication_B);
                    ChainageR11("ldr R11,[r13,#" + (44 - this.offset) + "] ; on va ds le bloc au dessus\n", "c", ctx);
                }
                System.out.println("WHHHHHHHHHHHHHHHHHHHHHAAAAT " + rvalue + this.variablesOffset.get(rvalue));
                produire("LDR R" + (maxList(takenRegisters)) + ", [R11, #" + this.variablesOffset.get(rvalue)
                        + "] ;On charge la valeur de " + rvalue + " dans un registre\n", buffer, ctx);
                if (imbrication_V < imbrication_B) {

                    ChainageR11("ADD R11,R13,#" + (-this.offset) + " ; on revient ds le bloc actuel\n", "c", ctx);
                }
                return "`R" + maxTR() + "`";
            }
            // else if rvalue is format `R[0-10]+`
            else if (rvalue.matches("`R[0-9]+`")) {
                takenRegisters.add(maxList(takenRegisters) + 1);

                // String register = rvalue.substring(1, rvalue.length() - 1);
                /*
                 * produire("MOV R" + (maxList(takenRegisters)) + ", " + register
                 * + " ;On charge la valeur de d'un parenthèse dans un registre\n",buffer);
                 */ // Sert à rien ??
                return rvalue;

            } else {
                try {
                    int value = Integer.parseInt(rvalue);
                    addTR(maxTR() + 1);
                    String register = "`R" + maxTR() + "`";
                    produire("MOV R" + maxTR() + ", #" + value
                            + " ;On charge la valeur de l'entier dans un registre\n", buffer, ctx);
                    return register;
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : " + rvalue + " n'est pas un entier");
                }
            }
        }
        System.out.println("takenRegisters : " + takenRegisters);
        return "";
    }

    public ArrayList<Integer> reinitializeRegisters(ArrayList<Integer> takenRegisters) {
        takenRegisters.clear();
        takenRegisters.add(-1);
        return takenRegisters;
    }

    public int maxList(ArrayList<Integer> list) {
        int max = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    public String tail(String[] list, int i, String separator) {
        String res = "";
        if (i > list.length) {
            System.out.println("Erreur : i > list.length");
            return res;
        }

        for (int j = i; j < list.length - 1; j++) {
            res += list[j] + separator;
        }
        res += list[list.length - 1];
        return res;
    }

    public void addTR(int value) {
        this.takenRegisters.add(value);
    }

    public void removeTR(int value) {
        int index = this.takenRegisters.indexOf(value);
        this.takenRegisters.remove(value);
    }

    public void removeWITR(int index) {
        this.takenRegisters.remove(index);
    }

    public int maxTR() {
        return this.maxList(this.takenRegisters);
    }
}
