package ast;

import java.util.Optional;

public class VoidExpr implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Optional<Ast> type;

    public VoidExpr(){
        this.type = Optional.empty();
    }
}