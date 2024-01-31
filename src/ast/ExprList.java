package ast;
import java.util.Optional;
public class ExprList implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast left;
    public Optional<Ast> right;
    
    public ExprList(Ast left, Ast right){
        this.left = left;
        this.right = Optional.of(right);
    }
    public ExprList(Ast left){
        this.left = left;
        this.right = Optional.empty();
    }
    
}
