package ast;

import java.util.Optional;

public class CallExp implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast id;
    public Optional<Ast> expr_list;

    public CallExp(Ast id, Ast expr_list){
        this.id = id;
        this.expr_list = Optional.of(expr_list);
    }

    public CallExp(Ast id){
        this.id = id;
        this.expr_list = Optional.empty();
    }
}