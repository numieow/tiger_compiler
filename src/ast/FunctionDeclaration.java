package ast;
import java.util.ArrayList;

public class FunctionDeclaration implements Ast{
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public Ast id;
    public ArrayList<Ast> fieldDec;
    public Ast function;

    public FunctionDeclaration(Ast id, ArrayList<Ast> fieldDec, Ast function){
        this.id = id;
        this.fieldDec = fieldDec;
        this.function = function;

    }

    public void addFieldDec(Ast field){
        this.fieldDec.add(field);
    }
    
}
