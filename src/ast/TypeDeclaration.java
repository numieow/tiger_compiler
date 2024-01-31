package ast;

public class TypeDeclaration implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast id;
    public Ast type_id;
    
    public TypeDeclaration(Ast id, Ast type_id){
        this.id = id;
        this.type_id = type_id;
    }
}
