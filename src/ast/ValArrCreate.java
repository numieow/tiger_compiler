package ast;

public class ValArrCreate implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }


    public Ast val;

    public ValArrCreate(Ast val){
        this.val = val;

    }
    
}
