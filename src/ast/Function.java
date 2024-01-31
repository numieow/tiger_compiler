package ast;

public class Function implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast expr;
    public Ast type_id;

    public Function(Ast type_id,Ast expr){
        this.expr=expr;
        this.type_id=type_id;
    }
    
}
