package ast;

public class ValSegExp implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast segexp;

    public ValSegExp(Ast segexp){
        this.segexp = segexp;
      
    }
}