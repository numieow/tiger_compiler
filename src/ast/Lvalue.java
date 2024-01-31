package ast;
//import java.util.Optional;
public class Lvalue implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast left;

    public Ast right = null;

    public Lvalue(Ast left,Ast right){
        this.left=left;
        this.right=right;

    }
    public Lvalue(Ast left){
        this.left=left;
        
    }

}