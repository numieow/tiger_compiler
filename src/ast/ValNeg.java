package ast;

public class ValNeg implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast neg;

    public ValNeg(Ast neg){
        this.neg = neg;
    }
}
