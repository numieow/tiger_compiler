package ast;

public class ValString implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String string;

    public ValString(String string){
        this.string = string;
    }
}
