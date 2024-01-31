package ast;

public class ValLetExp implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast val;

    public ValLetExp(Ast val){
        this.val = val;
    }
    
}
