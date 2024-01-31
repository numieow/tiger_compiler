package ast;

public class ValNil implements Ast {
    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    String nil;

    public ValNil(String nil){
        this.nil=nil;
    }
}
