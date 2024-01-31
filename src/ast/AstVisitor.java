package ast;
//import java.util.Optional;
public interface AstVisitor<T>{
    public T visit(IfThen affect);

    public T visit(IfThenElse affect);

    public T visit(SegExp affect);

    public T visit(Negation affect);
    
    public T visit(Program affect);

    public T visit(Expr affect);

    public T visit(CallExp affect);

    public T visit(And affect);

    public T visit(Or affect);

    public T visit(Plus affect);

    public T visit(Minus affect);

    public T visit(Mult affect);

    public T visit(Divide affect);

    public T visit(Egal affect);

    public T visit(Diff affect);

    public T visit(StrictMore affect);

    public T visit(StrictLess affect);

    public T visit(More affect);

    public T visit(Less affect);

    public T visit(Multi affect);

    public T visit(Nil affect);

    public T visit(StringNode affect);

    public T visit(IntNode affect);

    public T visit(Break affect);

    public T visit(RecCreate affect);

    public T visit(ArrCreate affect);

    public T visit(Assignment affect);

    public T visit(WhileExp affect);

     public T visit(ForExp affect);

     public T visit(LetExp affect);  // à revoir

    public T visit(ExprSeq affect);

    public T visit(ExprSeq2 affect);

    public T visit(FieldList affect);
    
    public T visit(FieldList2 affect);

    public T visit(Lvalue affect);

    public T visit(ArrayType affect);

    public T visit(RecType affect);

    public T visit(FieldDec affect);

    public T visit(TypeFields affect);

    public T visit(TypeFields2 affect);

    public T visit(VariableDeclaration affect);

    public T visit(Variable affect);

    public T visit(FunctionDeclaration affect);

    public T visit(Function affect);

    public T visit(Print affect);

    public T visit(VoidExpr affect);
    
    public T visit(ExprList affect);

    public T visit(String string);

    public T visit(LvalueBis affect);

    public T visit(Id  affect);

    //public T visit(Optional<Ast> affect);

    public T visit(TypeDecl affect);

    public T visit(TypeId affect);

    public T visit(TypeDeclaration affect);
    
    public T visit(TypeIdExpr affect);

    public T visit(FunExpr affect);

    public T visit(ArrType affect);

    public T visit(Type affect);

    public T visit(BaseArrayType affect);

    public T visit(BaseRecType affect);

    public T visit(ValLValue affect);

    public T visit(ValNil affect);

    public T visit(ValInt affect);

    public T visit(ValString affect);

    public T visit(ValSegExp affect);

    public T visit(ValNeg affect);

    public T visit(ValCallExp affect);
    
    public T visit(ValArrCreate affect);

    public T visit(ValRecCreate affect);

    public T visit(ValAssign affect);

    public T visit(ValIfThenElse affect);

    public T visit(ValIfThen affect);

    public T visit(ValWhileExp affect);

    public T visit(ValForExp affect);

    public T visit(ValBreak affect); //pas obligé ?

    public T visit(ValLetExp affect);

    public T visit(ValPrint affect);
}