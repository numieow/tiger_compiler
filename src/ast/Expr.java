package ast;

public class Expr implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast bool;

    public Expr(Ast bool){
        this.bool = bool;
    }
}