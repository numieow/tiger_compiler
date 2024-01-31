package ast;

public class ExprSeq2 implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast left;
    public Ast right;

    public ExprSeq2(Ast left,Ast right){
        this.left=left;
        this.right=right;

    }

}