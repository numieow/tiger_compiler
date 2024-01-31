package ast;

public class WhileExp implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast condition;
    public Ast block;

    public WhileExp(Ast condition,Ast block){
        this.condition=condition;
        this.block=block;

    }

}