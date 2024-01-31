package ast;

public class BaseRecType implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast recType;

    public BaseRecType(Ast recType){
        this.recType = recType;
    }
    

}