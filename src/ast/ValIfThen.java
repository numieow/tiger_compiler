package ast;

public class ValIfThen implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    
    public Ast ifthen;

    public ValIfThen(Ast ifthen){
        this.ifthen = ifthen;
    }
}
