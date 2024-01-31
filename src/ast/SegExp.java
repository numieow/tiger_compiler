package ast;

import java.util.Optional;

public class SegExp implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Optional<Ast> expr_seq;

    public SegExp(Ast expr_seq){
        this.expr_seq = Optional.of(expr_seq);
    }

    public SegExp(){
        this.expr_seq = Optional.empty();
    }
}