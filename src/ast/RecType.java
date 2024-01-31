package ast;

import java.util.ArrayList;

public class RecType implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }
    public ArrayList<Ast> recType;

    public RecType(){
        this.recType = new ArrayList<>();
    }

    public void addField(Ast field){
        this.recType.add(field);
    }
    
}
