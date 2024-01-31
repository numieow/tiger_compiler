package graphViz;

import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Optional;

import ast.*;

public class GraphVizVisitor implements AstVisitor<String> {

    private int state;
    private String nodeBuffer;
    private String linkBuffer;


    public GraphVizVisitor() {
        this.state = 0;
        this.nodeBuffer = "digraph \"ast\"{\n\n\tnodesep=1;\n\tranksep=1;\n\n";
        this.linkBuffer = "\n";

    }
    public void dumpGraph(String filepath) throws IOException {

        FileOutputStream output = new FileOutputStream(filepath);

        String buffer = this.nodeBuffer + this.linkBuffer + "}";
        byte[] strToBytes = buffer.getBytes();

        output.write(strToBytes);

        output.close();

    }

    private String nextState() {
        int returnedState = this.state;
        this.state++;
        return "N" + returnedState;
    }

    private void addTransition(String from, String dest) {
        this.linkBuffer += String.format("\t%s -> %s; \n", from, dest);

    }

    private void addNode(String node, String label) {

        if (!label.contains("\"")) {
            label = "\"" + label + "\"";
        }
        if (label.contains("\\")) {
            label = label.replace("\\", "\\\\");
        }
        this.nodeBuffer += String.format("\t%s [label=%s, shape=\"box\"];\n", node, label);

    }

    @Override
    public String visit(And and) {

        String nodeIdentifier = this.nextState();

        String leftState = and.left.accept(this);
        String rightState = and.right.accept(this);

        this.addNode(nodeIdentifier, "&");
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(ArrayType array) {

        String nodeIdentifier = this.nextState();

        String typeState = array.type.accept(this);

        this.addNode(nodeIdentifier, "ArrayType");
        this.addTransition(nodeIdentifier, typeState);

        return nodeIdentifier;

    }

    @Override
    public String visit(ArrCreate array) {

        String nodeIdentifier = this.nextState();

        String typeState = array.type.accept(this);
        String exp1State = array.exp1.accept(this);

        this.addNode(nodeIdentifier, "ArrayCreate");
        this.addTransition(nodeIdentifier, typeState);
        this.addTransition(nodeIdentifier, exp1State);

        return nodeIdentifier;

    }

    @Override
    public String visit(Assignment assignment) {
        String nodeIdentifier = this.nextState();
        String leftState = assignment.left.accept(this);
        String rightState = assignment.right.accept(this);

        this.addNode(nodeIdentifier, ":=");
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Break b) {
        String nodeIdentifier = this.nextState();
        // String value=b.value.accept(this);
        this.addNode(nodeIdentifier, "Break");

        return nodeIdentifier;
    }

    @Override
    public String visit(CallExp callexp) {
        String nodeIdentifier = this.nextState();
        String id = callexp.id.accept(this);
        this.addNode(nodeIdentifier, "Call exp new");
        this.addTransition(nodeIdentifier, id);
        if (callexp.expr_list.isPresent()) {
            String exprlist = callexp.expr_list.get().accept(this);

            this.addTransition(nodeIdentifier, exprlist);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(Diff diff) {
        String nodeIdentifier = this.nextState();
        String leftState = diff.left.accept(this);
        String rightState = diff.right.accept(this);
        this.addNode(nodeIdentifier, "<>");
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Divide divide) {
        String nodeIdentifier = this.nextState();
        String leftState = divide.left.accept(this);
        String rightState = divide.right.accept(this);
        this.addNode(nodeIdentifier, "/");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Egal egal) {
        String nodeIdentifier = this.nextState();
        String leftState = egal.left.accept(this);

        String rightState = egal.right.accept(this);
        this.addNode(nodeIdentifier, "=");
        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Expr expr) {
        String nodeIdentifier = this.nextState();
        String bool = expr.bool.accept(this);
        this.addTransition(nodeIdentifier, bool);

        return nodeIdentifier;
    }

    @Override
    public String visit(ExprList exprlist) {
        String nodeIdentifier = this.nextState();
        String leftState = exprlist.left.accept(this);
        this.addNode(nodeIdentifier, "ExprList");
        this.addTransition(nodeIdentifier, leftState);
        if (exprlist.right.isPresent()) {

            String rightState = exprlist.right.get().accept(this);
            this.addTransition(nodeIdentifier, rightState);
        }
        return nodeIdentifier;

    }

    @Override
    public String visit(IfThen ifThen) {

        String nodeIdentifier = this.nextState();

        String conditionState = ifThen.condition.accept(this);
        String thenBlockState = ifThen.thenBlock.accept(this);

        this.addNode(nodeIdentifier, "IfThen");

        this.addTransition(nodeIdentifier, conditionState);
        this.addTransition(nodeIdentifier, thenBlockState);

        return nodeIdentifier;

    }

    @Override
    public String visit(IfThenElse ifThenElse) {

        String nodeIdentifier = this.nextState();

        String conditionState = ifThenElse.condition.accept(this);
        String thenBlockState = ifThenElse.thenBlock.accept(this);
        String elseBlockState = ifThenElse.elseBlock.accept(this);

        this.addNode(nodeIdentifier, "IfThenElse");

        this.addTransition(nodeIdentifier, conditionState);
        this.addTransition(nodeIdentifier, thenBlockState);
        this.addTransition(nodeIdentifier, elseBlockState);

        return nodeIdentifier;

    }

    @Override
    public String visit(IntNode intNode) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, String.valueOf(intNode.value));

        return nodeIdentifier;

    }

    @Override
    public String visit(Minus minus) {

        String nodeIdentifier = this.nextState();

        String leftState = minus.left.accept(this);
        String rightState = minus.right.accept(this);

        this.addNode(nodeIdentifier, "-");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Mult mult) {

        String nodeIdentifier = this.nextState();

        String leftState = mult.left.accept(this);
        String rightState = mult.right.accept(this);

        this.addNode(nodeIdentifier, "*");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Or or) {

        String nodeIdentifier = this.nextState();

        String leftState = or.left.accept(this);
        String rightState = or.right.accept(this);

        this.addNode(nodeIdentifier, "|");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Plus plus) {

        String nodeIdentifier = this.nextState();

        String leftState = plus.left.accept(this);
        String rightState = plus.right.accept(this);

        this.addNode(nodeIdentifier, "+");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Print print) {

        String nodeIdentifier = this.nextState();

        String valueState = print.expr.accept(this); // Gérer les cas où on a une STRING_CONSTANT

        this.addNode(nodeIdentifier, "print");
        this.addTransition(nodeIdentifier, valueState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Program program) {

        String nodeIdentifier = this.nextState();

        String exprState = program.expr.accept(this);

        this.addNode(nodeIdentifier, "Program");
        this.addTransition(nodeIdentifier, exprState);

        return nodeIdentifier;

    }

    @Override
    public String visit(SegExp segexp) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "segexp");
        if (segexp.expr_seq.isPresent()) {
            String exprSeq = segexp.expr_seq.get().accept(this);
            this.addTransition(nodeIdentifier, exprSeq);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(StrictMore strictMore) {

        String nodeIdentifier = this.nextState();
        String leftState = strictMore.left.accept(this);
        String rightState = strictMore.right.accept(this);
        this.addNode(nodeIdentifier, ">");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(StrictLess strictLess) {

        String nodeIdentifier = this.nextState();

        String leftState = strictLess.left.accept(this);
        String rightState = strictLess.right.accept(this);

        this.addNode(nodeIdentifier, "<");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(TypeFields typeField) {

        String nodeIdentifier = this.nextState();

        String fieldDecState = typeField.fieldDec.accept(this);
        String typeFieldIdState = typeField.type_fields2.get().accept(this);

        this.addNode(nodeIdentifier, "TypeFields");

        this.addTransition(nodeIdentifier, fieldDecState);
        this.addTransition(nodeIdentifier, typeFieldIdState);

        return nodeIdentifier;

    }

    @Override
    public String visit(TypeFields2 typeField) {

        String nodeIdentifier = this.nextState();

        String fieldDecState = typeField.fieldDec.accept(this);
        String typeFieldIdState = typeField.type_fields2.accept(this);

        this.addNode(nodeIdentifier, "typeFields2");

        this.addTransition(nodeIdentifier, fieldDecState);
        this.addTransition(nodeIdentifier, typeFieldIdState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Variable var) {

        String nodeIdentifier = this.nextState();

        String exprState = var.expr.accept(this);
        String typeIdState = var.type_id.accept(this);

        this.addNode(nodeIdentifier, "Variable");

        this.addTransition(nodeIdentifier, exprState);
        this.addTransition(nodeIdentifier, typeIdState);

        return nodeIdentifier;

    }

    @Override
    public String visit(VariableDeclaration varDec) {

        String nodeIdentifier = this.nextState();

        String idState = varDec.id.accept(this);
        String varState = varDec.variable.accept(this);

        this.addNode(nodeIdentifier, "var");

        this.addTransition(nodeIdentifier, idState);
        this.addTransition(nodeIdentifier, varState);

        return nodeIdentifier;

    }

    @Override
    public String visit(WhileExp whileExp) {

        String nodeIdentifier = this.nextState();

        String condState = whileExp.condition.accept(this);
        String blockState = whileExp.block.accept(this);

        this.addNode(nodeIdentifier, "while do");

        this.addTransition(nodeIdentifier, condState);
        this.addTransition(nodeIdentifier, blockState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Negation neg) {
        String nodeIdentifier = this.nextState();

        String negState = neg.expr.accept(this);

        this.addNode(nodeIdentifier, "-");

        this.addTransition(nodeIdentifier, negState);

        return nodeIdentifier;
    }

    @Override
    public String visit(More affect) {
        String nodeIdentifier = this.nextState();

        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, ">");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;
    }

    ;

    @Override
    public String visit(Less affect) {
        String nodeIdentifier = this.nextState();
        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "<");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);
        return nodeIdentifier;
    }

    @Override
    public String visit(Multi affect) {

        String nodeIdentifier = this.nextState();
        String leftState = affect.left.accept(this);
        String rightState = affect.right.accept(this);

        this.addNode(nodeIdentifier, "*");

        this.addTransition(nodeIdentifier, leftState);
        this.addTransition(nodeIdentifier, rightState);

        return nodeIdentifier;

    }

    @Override
    public String visit(Nil affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "nil");
        return nodeIdentifier;

    }

    @Override
    public String visit(StringNode affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, affect.value);
        return nodeIdentifier;
    }

    @Override
    public String visit(RecCreate affect) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "segexp");
        String type_id = affect.type_id.accept(this);
        this.addTransition(nodeIdentifier, type_id);
        if (affect.field_list.isPresent()) {
            String field_list = affect.field_list.get().accept(this);
            this.addTransition(nodeIdentifier, field_list);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(ForExp affect) {
        String nodeIdentifier = this.nextState();
        String id = affect.id.accept(this);
        String expr = affect.exprequal.accept(this);
        String to = affect.exprto.accept(this);
        String block = affect.exprdo.accept(this);

        this.addNode(nodeIdentifier, "for");
        this.addTransition(nodeIdentifier, id);
        this.addTransition(nodeIdentifier, expr);
        this.addTransition(nodeIdentifier, to);
        this.addTransition(nodeIdentifier, block);
        return nodeIdentifier;

    }

    @Override
    public String visit(LetExp affect) { // potentiellement des listes ici

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "let");

        for (Ast d : affect.left) {
            String dec = d.accept(this);
            this.addTransition(nodeIdentifier, dec);
        }

        String right = affect.right.accept(this);

        this.addTransition(nodeIdentifier, right);
        return nodeIdentifier;

    }

    @Override
    public String visit(ExprSeq affect) {

        String nodeIdentifier = this.nextState();
        String left = affect.left.accept(this);

        this.addNode(nodeIdentifier, "seqexp");
        this.addTransition(nodeIdentifier, left);
        if (affect.right != null) {
            String right = affect.right.accept(this);
            this.addTransition(nodeIdentifier, right);
        }

        return nodeIdentifier;

    }

    @Override
    public String visit(ExprSeq2 affect) {
        String nodeIdentifier = this.nextState();
        String left = affect.left.accept(this);
        String right = affect.right.accept(this);

        this.addNode(nodeIdentifier, "seqexp2");
        this.addTransition(nodeIdentifier, left);
        this.addTransition(nodeIdentifier, right);
        return nodeIdentifier;

    }

    @Override
    public String visit(FieldList affect) {
        String nodeIdentifier = this.nextState();
        String left = affect.left.accept(this);
        String middle = affect.middle.accept(this);
        this.addNode(nodeIdentifier, "fieldlist");
        this.addTransition(nodeIdentifier, left);
        this.addTransition(nodeIdentifier, middle);
        if (affect.right.isPresent()) {
            String right = affect.right.get().accept(this);
            this.addTransition(nodeIdentifier, right);
        }
        return nodeIdentifier;

    }

    @Override
    public String visit(FieldList2 affect) {
        String nodeIdentifier = this.nextState();
        String left = affect.left.accept(this);
        String middle = affect.middle.accept(this);
        String right = affect.right.accept(this);

        this.addNode(nodeIdentifier, "fieldlist");
        this.addTransition(nodeIdentifier, left);
        this.addTransition(nodeIdentifier, middle);
        this.addTransition(nodeIdentifier, right);
        return nodeIdentifier;
    }

    @Override
    public String visit(Lvalue affect) {
        String nodeIdentifier = this.nextState();
        String left = affect.left.accept(this);

        this.addNode(nodeIdentifier,"lvalue");
        this.addTransition(nodeIdentifier, left);

        if (affect.right != null) {

            String right = affect.right.accept(this);
            this.addTransition(nodeIdentifier, right);
        }

        return nodeIdentifier;

    }

    @Override
    public String visit(LvalueBis affect) {
        String nodeIdentifier = this.nextState();
        String expr = affect.expr.accept(this);

        this.addNode(nodeIdentifier, "lvalue");
        this.addTransition(nodeIdentifier, expr);

        if (affect.lvalue_bis.isPresent()) {
            String lvalue_bis = affect.lvalue_bis.get().accept(this);
            this.addTransition(nodeIdentifier, lvalue_bis);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(RecType affect) {
        String nodeIdentifier = this.nextState();
        for (Ast rt : affect.recType) {
            String recType = rt.accept(this);
            this.addTransition(nodeIdentifier, recType);
            this.addNode(nodeIdentifier, "recType");
        }
        return nodeIdentifier;
    }

    @Override
    public String visit(FieldDec affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.id.accept(this);
        String typeIdState = affect.type_id.accept(this);

        this.addNode(nodeIdentifier, "Field declaration");

        this.addTransition(nodeIdentifier, idState);
        this.addTransition(nodeIdentifier, typeIdState);

        return nodeIdentifier;
    }

    @Override
    public String visit(FunctionDeclaration affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.id.accept(this);
        String functionState = affect.function.accept(this);

        this.addNode(nodeIdentifier, "function"); // Function Declaration

        for (Ast fd : affect.fieldDec) {
            String fieldDecState = fd.accept(this);
            this.addTransition(nodeIdentifier, fieldDecState);
        }

        this.addTransition(nodeIdentifier, idState);

        this.addTransition(nodeIdentifier, functionState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Function affect) {

        String nodeIdentifier = this.nextState();

        String exprState = affect.expr.accept(this);
        String typeIdState = affect.type_id.accept(this);

        this.addNode(nodeIdentifier, "Function");

        this.addTransition(nodeIdentifier, exprState);
        this.addTransition(nodeIdentifier, typeIdState);

        return nodeIdentifier;
    }

    @Override
    public String visit(VoidExpr affect) {

        return null;
    }

    @Override
    public String visit(String string) {

        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, string);

        return nodeIdentifier;
    }

    @Override
    public String visit(Id affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.id;

        this.addNode(nodeIdentifier, idState);

        return nodeIdentifier;
    }

    // @Override
    // public String visit(Optional<Ast> affect) {

    // return null;
    // }

    @Override
    public String visit(TypeDecl affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.type_id.accept(this);
        String typeState = affect.type.accept(this);

        this.addNode(nodeIdentifier, "BasicType declaration");

        this.addTransition(nodeIdentifier, idState);
        this.addTransition(nodeIdentifier, typeState);

        return nodeIdentifier;
    }

    @Override
    public String visit(TypeId affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.id;

        this.addNode(nodeIdentifier, idState);

        return nodeIdentifier;
    }

    @Override
    public String visit(TypeDeclaration affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.id.accept(this);
        String typeState = affect.type_id.accept(this);

        this.addNode(nodeIdentifier, "Id");
        this.addTransition(nodeIdentifier, idState);
        this.addTransition(nodeIdentifier, typeState);

        return nodeIdentifier;
    }

    @Override
    public String visit(Type affect) {

        String nodeIdentifier = this.nextState();

        String idState = affect.typeId.accept(this);

        this.addNode(nodeIdentifier, "BasicType");

        this.addTransition(nodeIdentifier, idState);

        return nodeIdentifier;
    }

    @Override
    public String visit(ArrType affect) {
        String nodeIdentifier = this.nextState();

        String type = affect.type.accept(this);

        this.addNode(nodeIdentifier, "Array type");

        this.addTransition(nodeIdentifier, type);

        return nodeIdentifier;
    }

    @Override
    public String visit(FunExpr affect) {
        String nodeIdentifier = this.nextState();

        String id = affect.id.accept(this);

        this.addNode(nodeIdentifier, "="); // Function expression

        this.addTransition(nodeIdentifier, id);

        if (affect.expr != null) {
            String expr = affect.expr.accept(this);
            this.addTransition(nodeIdentifier, expr);
        }

        return nodeIdentifier;
    }

    @Override
    public String visit(TypeIdExpr affect) {
        String nodeIdentifier = this.nextState();

        String typeId = affect.typeId.accept(this);
        String expr = affect.expr.accept(this);

        this.addNode(nodeIdentifier, ":=");

        this.addTransition(nodeIdentifier, typeId);
        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(BaseRecType affect) {
        String nodeIdentifier = this.nextState();

        String recType = affect.recType.accept(this);

        this.addNode(nodeIdentifier, "Baserectype");

        this.addTransition(nodeIdentifier, recType);

        return nodeIdentifier;
    }

    @Override
    public String visit(BaseArrayType affect) {
        String nodeIdentifier = this.nextState();

        String arrType = affect.arrayType.accept(this);

        this.addNode(nodeIdentifier, "Basearraytype");

        this.addTransition(nodeIdentifier, arrType);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValLValue affect) {

        String nodeIdentifier = this.nextState();

        String id = affect.lvalue.accept(this);

        this.addNode(nodeIdentifier, "Val lvalue");

        this.addTransition(nodeIdentifier, id);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValNil affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Val nil");

        return nodeIdentifier;
    }

    @Override
    public String visit(ValInt affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Val int");

        return nodeIdentifier;
    }

    @Override
    public String visit(ValString affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "Val string");

        return nodeIdentifier;
    }

    @Override
    public String visit(ValSegExp affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.segexp.accept(this);

        this.addNode(nodeIdentifier, "Val seg exp");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValNeg affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.neg.accept(this);

        this.addNode(nodeIdentifier, "Val neg");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValCallExp affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.call.accept(this);

        this.addNode(nodeIdentifier, "Val call exp");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValArrCreate affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.val.accept(this);

        this.addNode(nodeIdentifier, "Val arr create");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValRecCreate affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.rec.accept(this);

        this.addNode(nodeIdentifier, "Val rec create");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValAssign affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.val.accept(this);

        this.addNode(nodeIdentifier, "Val assign");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValIfThenElse affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.ifthenelse.accept(this);

        this.addNode(nodeIdentifier, "Val if then else");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValIfThen affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.ifthen.accept(this);

        this.addNode(nodeIdentifier, "Val if then");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValWhileExp affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.whileexp.accept(this);

        this.addNode(nodeIdentifier, "Val while");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValForExp affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.forexp.accept(this);

        this.addNode(nodeIdentifier, "Val for");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValBreak affect) {
        String nodeIdentifier = this.nextState();

        this.addNode(nodeIdentifier, "ValBreak");

        return nodeIdentifier;
    }

    @Override
    public String visit(ValLetExp affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.val.accept(this);

        this.addNode(nodeIdentifier, "Val let");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

    @Override
    public String visit(ValPrint affect) {
        String nodeIdentifier = this.nextState();

        String expr = affect.val.accept(this);

        this.addNode(nodeIdentifier, "Val print");

        this.addTransition(nodeIdentifier, expr);

        return nodeIdentifier;
    }

}

//TO DO : renommer les noeuds pour qu'ils soient plus parlants