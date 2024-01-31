package ast;

public class Variable implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast expr;
    public Ast type_id;

    public Variable(Ast expr,Ast type_id){
        this.expr = expr;
        this.type_id = type_id;
    }
}
