package ast;

public class Break implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String value;

    public Break() {
        this.value = "Break";
    }
    
}

