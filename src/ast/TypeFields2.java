package ast;

public class TypeFields2 implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast fieldDec;
    public Ast type_fields2;

    public TypeFields2(Ast fieldDec,Ast type_fields2){
        this.fieldDec = fieldDec;
        this.type_fields2 = type_fields2;
    }
    
}
