package ast;

public class Print implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;

    public Print(Ast expr){
        this.expr=expr;
    }
    
}
