package ast;

public class IntNode implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public int value;

    public IntNode(int value) {
        this.value = value;
    }
    
}
