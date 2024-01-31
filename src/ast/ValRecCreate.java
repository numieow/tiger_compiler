package ast;

public class ValRecCreate implements Ast{
    
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast rec;

    public ValRecCreate(Ast rec){
        this.rec = rec;
    }
}
