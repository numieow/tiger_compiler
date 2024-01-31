package ast;

public class ValForExp implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast forexp;

    public ValForExp(Ast forexp){
        this.forexp = forexp;
    }
}
