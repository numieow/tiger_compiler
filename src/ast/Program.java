package ast;

public class Program implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;

    public Program(Ast expr){
        this.expr = expr;
    }
}