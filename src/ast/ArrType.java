package ast;

public class ArrType implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast type;

    public ArrType(Ast type){
        this.type=type;
    }
}
