package ast;

import java.util.ArrayList;

public class LetExp implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public ArrayList<Ast> left;
    public Ast right;

    public LetExp(ArrayList<Ast> left){
        System.out.println("LetExp simple");
        this.left=left;
    }
    public LetExp(ArrayList<Ast> left,Ast right){
        this.left=left;
        this.right=right;


    }

}