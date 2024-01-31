package ast;

public class ForExp implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast id;
    public Ast exprequal;
    public Ast exprto;
    public Ast exprdo;

    public ForExp(Ast id,Ast exprequal,Ast exprto,Ast exprdo){
        this.id=id;
        this.exprequal=exprequal;
        this.exprto=exprto;
        this.exprdo=exprdo;
    }
 
}