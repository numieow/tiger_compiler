package ast;

public class ValPrint implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast val;

    public ValPrint(Ast val){
        this.val = val;
    }
    
}
