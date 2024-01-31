package ast;

public class ArrayType implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast type;

    public ArrayType(Ast type){
        this.type=type;
    }
}
