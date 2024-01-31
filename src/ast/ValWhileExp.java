package ast;

public class ValWhileExp implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast whileexp;

    public ValWhileExp(Ast whileexp){
        this.whileexp = whileexp;
    }
}
