package ast;

public class ArrCreate implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast type;
    public Ast exp1;
    public Ast exp2;


    public ArrCreate(Ast type, Ast exp1, Ast exp2){
        this.type = type;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }


}