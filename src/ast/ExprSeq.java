package ast;
//import java.util.Optional;

public class ExprSeq implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast left;
    public Ast right;

    public ExprSeq(Ast left,Ast right){
        this.left=left;
        this.right= right;
    }
    public ExprSeq(Ast left){
        this.left=left;
        
    }

}