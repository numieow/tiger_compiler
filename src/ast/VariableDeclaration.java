package ast;

public class VariableDeclaration implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast id;
    public Ast variable;
    
    public VariableDeclaration(Ast id, Ast variable){
        this.id = id;
        this.variable = variable;
    }
}
