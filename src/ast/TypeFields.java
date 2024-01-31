package ast;

import java.util.Optional;

public class TypeFields implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast fieldDec;
    public Optional<Ast> type_fields2;

    public TypeFields(Ast fieldDec,Ast type_fields2){
        this.fieldDec=fieldDec;
        this.type_fields2= Optional.of(type_fields2);
    }
    public TypeFields(Ast fieldDec){
        this.fieldDec=fieldDec;
        this.type_fields2 = Optional.empty();
    }
}