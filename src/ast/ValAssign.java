package ast;

public class ValAssign implements Ast{
    
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }


    public Ast val;

    public ValAssign(Ast val){

        this.val = val;
    }
    
}
