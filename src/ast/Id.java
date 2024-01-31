package ast;

public class Id implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public String id;
    
    public Id(String id){
        this.id = id;
    }
}
