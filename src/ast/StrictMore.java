package ast;

public class StrictMore implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;

    public StrictMore(Ast left, Ast right){
        this.left = left;
        this.right = right;
    }

}