package ast;

public class ValBreak implements Ast{
    
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public String breakval;

    public ValBreak(String breakval){
        this.breakval = breakval;
    }


}
