package ast;

public class ValCallExp implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast call;


    public ValCallExp(Ast call){
        this.call = call;

    }
}
