package ast;

public class Nil implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String value;

    public Nil() {
        this.value = "Nil";
    }
    
}
