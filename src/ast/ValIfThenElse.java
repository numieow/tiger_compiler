package ast;

public class ValIfThenElse implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast ifthenelse;

    public ValIfThenElse(Ast ifthenelse){
        this.ifthenelse = ifthenelse;
    }
    
}
