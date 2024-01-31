package ast;

public class TypeIdExpr implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast typeId;
    public Ast expr;

    public TypeIdExpr(Ast typeId, Ast expr){
        this.typeId = typeId;
        this.expr = expr;
    }
}
