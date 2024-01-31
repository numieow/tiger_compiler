package ast;

//import java.util.Optional;

public class FunExpr implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast id;
    public Ast expr = null;

    
    public FunExpr(Ast id, Ast expr){
        this.id = id;
        this.expr = expr;
    }

    public FunExpr(Ast id){
        this.id=id;
    }
}