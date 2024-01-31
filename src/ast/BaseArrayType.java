package ast;

public class BaseArrayType implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast arrayType;

    public BaseArrayType(Ast arrayType){
        this.arrayType = arrayType;
    }
    

}