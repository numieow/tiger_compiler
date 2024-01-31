package ast;

public class ValLValue implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast lvalue;

    public ValLValue(Ast lvalue){
        this.lvalue = lvalue;
    }
}
