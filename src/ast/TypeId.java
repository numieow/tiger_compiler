package ast;

public class TypeId implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String id;
    public TypeId(String id){
        this.id = id;
    }
}
