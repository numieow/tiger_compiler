package ast;

public class FieldList2 implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast left;
    public Ast middle;
    public Ast right;

    public FieldList2(Ast left,Ast middle,Ast right){
        this.left=left;
        this.middle=middle;
        this.right=right;

    }

}