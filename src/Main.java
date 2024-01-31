
//import java.util.Arrays;
//import javax.swing.JFrame;
//import javax.swing.JPanel;

import java.io.IOException;

//import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ast.Ast;
import ast.AstCreator;
import graphViz.GraphVizVisitor;
import parser.*;
import parser.exprParser.ProgramContext;
import symtab.trueListener;
import asm.Tools;
import asm.AsmListener;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Error : Expected 1 argument filepath, found 0");
            return;
        }

        String testFile = args[0];

        try {

            // chargement du fichier et construction du parser
            CharStream input = CharStreams.fromFileName(testFile);
            exprLexer lexer = new exprLexer(input);
            CommonTokenStream stream = new CommonTokenStream(lexer);
            exprParser parser = new exprParser(stream);

            ProgramContext program = parser.program();

            // visualize programcontext tree in console
            System.out.println(program.toStringTree(parser));

            // // code d'affichage de l'arbre syntaxique
            // JFrame frame = new JFrame("Antlr AST");
            // JPanel panel = new JPanel();
            // TreeViewer viewer = new TreeViewer(Arrays.asList(
            // parser.getRuleNames()),program);
            // viewer.setScale(1.5); // Scale a little
            // panel.add(viewer);
            // frame.add(panel);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.pack();
            // frame.setVisible(true);

            // Visiteur de création de l'AST + création de l'AST
            AstCreator creator = new AstCreator();
            //System.out.println("Program accept lancé");
            Ast ast = program.accept(creator);

            // Visiteur de représentation graphique + appel
            GraphVizVisitor graphViz = new GraphVizVisitor();
            ast.accept(graphViz);

            graphViz.dumpGraph("./out/tree.dot");

            // Listener de création de la table des symboles
            trueListener symtab = new trueListener();
            ParseTreeWalker.DEFAULT.walk(symtab, program);

            // Affichage de la table des symboles
            System.out.println("------------------------------------");
            symtab.getTable().printTable();
            System.out.println("\n------------------------------------");
            symtab.getTable().getErreursMessage(); 
            AsmListener asm = new AsmListener(symtab);
            ParseTreeWalker.DEFAULT.walk(asm, program);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

    }

}