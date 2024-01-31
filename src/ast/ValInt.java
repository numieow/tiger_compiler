package ast;

public class ValInt implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public int value;

    public ValInt(int value){
        this.value = value;
    }
}
