package ast;

public class Negation implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;

    public Negation(Ast expr){
        this.expr = expr;
    }
}