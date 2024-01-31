package ast;
import java.util.Optional;


public class LvalueBis implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;
    public Optional<Ast> lvalue_bis;

    public LvalueBis(Ast expr, Ast lvalue_bis){
        this.expr = expr;
        this.lvalue_bis =Optional.of(lvalue_bis);
    }
    public LvalueBis(Ast expr){
        this.expr = expr;
        this.lvalue_bis =Optional.empty();
    }
}
