package ast;

public class StringNode implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String value;

    public StringNode(String value) {
        this.value = value;
    }
    
}
