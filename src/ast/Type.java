package ast;

public class Type implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast typeId;

    public Type(Ast typeId){
        this.typeId = typeId;
    }
    

}
