package ast;
import java.util.Optional;
public class FieldList implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast left;
    public Ast middle;
    public Optional<Ast> right;

    public FieldList(Ast left,Ast middle,Ast right){
        this.left=left;
        this.middle=middle;
        this.right=Optional.of(right);

    }
    public FieldList(Ast left,Ast middle){
        this.left=left;
        this.middle=middle;
        this.right=Optional.empty();

    }

}