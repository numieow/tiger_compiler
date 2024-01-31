package ast;

import java.util.ArrayList;
import parser.exprBaseVisitor;
import parser.exprParser;

public class AstCreator extends exprBaseVisitor<Ast> {

    @Override
    public Ast visitProgram(exprParser.ProgramContext ctx) {

        return new Program(ctx.getChild(0).accept(this));
    }

    @Override
    public Ast visitExpr(exprParser.ExprContext ctx) {

        return ctx.getChild(0).accept(this);
    }

    // Faut gérer le cas où il n'y a pas de expr_seq
    @Override
    public Ast visitSegExp(exprParser.SegExpContext ctx) {
        if (ctx.getChildCount() <= 2) {
            return new SegExp();
        } else {
            return ctx.getChild(1).accept(this);
        }
    }

    @Override
    public Ast visitNegation(exprParser.NegationContext ctx) {
        return ctx.getChild(1).accept(this);
    }

    @Override
    public Ast visitCallExp(exprParser.CallExpContext ctx) {
        Ast id = ctx.getChild(0).accept(this);
        if (ctx.getChildCount() <= 3) {

            return id;
        } else {

            Ast expr_list = ctx.getChild(2).accept(this);
            return new CallExp(id, expr_list);
        }

    }

    @Override
    public Ast visitBool(exprParser.BoolContext ctx) {
        Ast tempNode = ctx.getChild(0).accept(this);

        for (int i = 0; 2 * i < ctx.getChildCount() - 1; i++) {

            String operation = ctx.getChild(2 * i + 1).toString();
            Ast right = ctx.getChild(2 * (i + 1)).accept(this);

            switch (operation) {
                case "&":
                    tempNode = new And(tempNode, right);
                    break;
                case "|":
                    tempNode = new Or(tempNode, right);
                    break;
                default:
                    break;
            }
        }
        return tempNode;
    }

    @Override
    public Ast visitEqual(exprParser.EqualContext ctx) {
        Ast tempNode = ctx.getChild(0).accept(this);

        for (int i = 0; 2 * i < ctx.getChildCount() - 1; i++) {
            String operation = ctx.getChild(2 * i + 1).toString();
            Ast right = ctx.getChild(2 * (i + 1)).accept(this);

            switch (operation) {
                case "=":

                    tempNode = new Egal(tempNode, right);
                    break;
                case "<>":
                    tempNode = new Diff(tempNode, right);
                    break;
                case "<":
                    tempNode = new StrictLess(tempNode, right);
                    break;
                case ">":
                    tempNode = new StrictMore(tempNode, right);
                    break;
                case "<=":
                    tempNode = new Less(tempNode, right);
                    break;
                case ">=":
                    tempNode = new More(tempNode, right);
                    break;
                default:

                    break;
            }
        }
        return tempNode;
    }

    @Override
    public Ast visitPlus(exprParser.PlusContext ctx) {
        Ast tempNode = ctx.getChild(0).accept(this);

        for (int i = 0; 2 * i < ctx.getChildCount() - 1; i++) {
            String operation = ctx.getChild(2 * i + 1).toString();
            Ast right = ctx.getChild(2 * (i + 1)).accept(this);

            switch (operation) {
                case "+":
                    tempNode = new Plus(tempNode, right);
                    break;
                case "-":
                    tempNode = new Minus(tempNode, right);
                    break;
                default:
                    break;
            }
        }
        return tempNode;
    }

    @Override
    public Ast visitMult(exprParser.MultContext ctx) {
        Ast tempNode = ctx.getChild(0).accept(this);

        for (int i = 0; 2 * i < ctx.getChildCount() - 1; i++) {

            String operation = ctx.getChild(2 * i + 1).toString(); // Error here :(

            Ast right = ctx.getChild(2 * (i + 1)).accept(this);

            switch (operation) {
                case "*":
                    tempNode = new Multi(tempNode, right);
                    break;
                case "/":
                    tempNode = new Divide(tempNode, right);
                    break;
                default:
                    break;
            }
        }
        return tempNode;
    }

    @Override
    public Ast visitValLValue(exprParser.ValLValueContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValNil(exprParser.ValNilContext ctx) {
        return new Nil();
    }

    @Override
    public Ast visitValInt(exprParser.ValIntContext ctx) {
        return new IntNode(Integer.parseInt(ctx.getChild(0).toString()));
    }

    @Override
    public Ast visitValString(exprParser.ValStringContext ctx) {
        return new StringNode(ctx.getChild(0).toString());
    }

    @Override
    public Ast visitValSegExp(exprParser.ValSegExpContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValNeg(exprParser.ValNegContext ctx) {
        return new Negation(ctx.getChild(0).accept(this));
    }

    @Override
    public Ast visitValCallExp(exprParser.ValCallExpContext ctx) {

        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValArrCreate(exprParser.ValArrCreateContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValRecCreate(exprParser.ValRecCreateContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValAssign(exprParser.ValAssignContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValIfThenElse(exprParser.ValIfThenElseContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValWhileExp(exprParser.ValWhileExpContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValForExp(exprParser.ValForExpContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValBreak(exprParser.ValBreakContext ctx) {
        return new Break();
    }

    @Override
    public Ast visitValLetExp(exprParser.ValLetExpContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitValPrint(exprParser.ValPrintContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitArrCreate(exprParser.ArrCreateContext ctx) {
        return new ArrCreate(ctx.getChild(0).accept(this), ctx.getChild(2).accept(this), ctx.getChild(5).accept(this));
    }

    @Override
    public Ast visitRecCreate(exprParser.RecCreateContext ctx) {
        Ast type_id = ctx.getChild(0).accept(this);
        if (ctx.getChildCount() > 3) {
            return new RecCreate(type_id, ctx.getChild(2).accept(this));
        } else {
            return new RecCreate(type_id);
        }
    }

    @Override
    public Ast visitAssignment(exprParser.AssignmentContext ctx) {
        Ast lvalue = ctx.getChild(0).accept(this);
        Ast exp = ctx.getChild(2).accept(this);

        return new Assignment(lvalue, exp);
    }

    @Override
    public Ast visitIfThen(exprParser.IfThenContext ctx) {
        Ast condition = ctx.getChild(1).accept(this);
        Ast thenBlock = ctx.getChild(3).accept(this);

        return new IfThen(condition, thenBlock);
    }

    @Override
    public Ast visitIfThenElse(exprParser.IfThenElseContext ctx) {
        Ast condition = ctx.getChild(1).accept(this);
        Ast thenBlock = ctx.getChild(3).accept(this);
        Ast elseBlock = ctx.getChild(5).accept(this);

        return new IfThenElse(condition, thenBlock, elseBlock);
    }

    @Override
    public Ast visitWhileExp(exprParser.WhileExpContext ctx) {
        Ast condition = ctx.getChild(1).accept(this);
        Ast block = ctx.getChild(3).accept(this);

        return new WhileExp(condition, block);
    }

    @Override
    public Ast visitForExp(exprParser.ForExpContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        Ast exp1 = ctx.getChild(3).accept(this);
        Ast exp2 = ctx.getChild(5).accept(this);
        Ast block = ctx.getChild(7).accept(this);

        return new ForExp(id, exp1, exp2, block);
    }

    @Override
    public Ast visitLetExp(exprParser.LetExpContext ctx) {

        ArrayList<Ast> decs = new ArrayList<Ast>();
        int i = 1;
        while (ctx.getChild(i).getChildCount() >= 1) {
            decs.add(ctx.getChild(i).accept(this));
            i++;
        }

        Ast exp = ctx.getChild(1 + i).accept(this);

        return new LetExp(decs, exp);
    }

    @Override
    public Ast visitExpr_seq(exprParser.Expr_seqContext ctx) {
        Ast exp = ctx.getChild(0).accept(this);
        if (ctx.getChild(1).getChildCount() > 1) { // segexp2 est tjr présent, on regarde son nombre de fils à lui
            Ast exp_seq = ctx.getChild(1).accept(this);
            return new ExprSeq(exp, exp_seq);
        } else {
            return exp;
        }
    }

    @Override
    public Ast visitExprSeq2(exprParser.ExprSeq2Context ctx) {
        Ast exp = ctx.getChild(1).accept(this);
        if (ctx.getChild(1).getChildCount() > 1) {
            Ast exp_seq = ctx.getChild(2).accept(this);
            return new ExprSeq(exp, exp_seq);
        } else {
            return exp;
        }
    }

    @Override
    public Ast visitVoidExprSeq2(exprParser.VoidExprSeq2Context ctx) {
        return null;
    }

    @Override
    public Ast visitExpr_list(exprParser.Expr_listContext ctx) {
        Ast exp = ctx.getChild(0).accept(this);
        if (ctx.getChild(1).getChildCount() > 1) {
            Ast exp_list = ctx.getChild(1).accept(this);
            return new ExprList(exp, exp_list);
        } else {
            return exp;
        }
    }

    @Override
    public Ast visitExprList2(exprParser.ExprList2Context ctx) {
        Ast exp = ctx.getChild(1).accept(this);
        if (ctx.getChild(2).getChildCount() > 1) {
            Ast exp_list = ctx.getChild(2).accept(this);
            return new ExprList(exp, exp_list);
        } else {
            return exp;
        }
    }

    @Override
    public Ast visitVoidExprList2(exprParser.VoidExprList2Context ctx) {
        return null;
    }

    @Override
    public Ast visitField_list(exprParser.Field_listContext ctx) {
        Ast id = ctx.getChild(0).accept(this);
        Ast expr = ctx.getChild(2).accept(this);
        if (ctx.getChildCount() > 3) {
            Ast field_list = ctx.getChild(3).accept(this);
            return new FieldList(id, expr, field_list);
        } else {
            return new FieldList(id, expr);
        }
    }

    @Override
    public Ast visitFieldList2(exprParser.FieldList2Context ctx) {
        Ast id = ctx.getChild(1).accept(this);
        Ast expr = ctx.getChild(3).accept(this);
        if (ctx.getChildCount() > 4) {
            Ast field_list = ctx.getChild(4).accept(this);
            return new FieldList(id, expr, field_list);
        } else {
            return new FieldList(id, expr);
        }
    }

    @Override
    public Ast visitVoidFieldList2(exprParser.VoidFieldList2Context ctx) {
        return new VoidExpr();
    }

    @Override
    public Ast visitLvalue(exprParser.LvalueContext ctx) {
        Ast id = ctx.getChild(0).accept(this);
        if (ctx.getChild(1).getChildCount() > 1) { // lvaluebis est tjr affiché, on regarde donc son nombre d'enfants à
                                                   // lui

            Ast exp = ctx.getChild(1).accept(this);
            return new Lvalue(id, exp);
        } else {

            return id;
        }
    }

    @Override
    public Ast visitExprLValueBis(exprParser.ExprLValueBisContext ctx) {
        Ast exp = ctx.getChild(1).accept(this);
        if (ctx.getChild(3).getChildCount() > 1) {
            Ast lvalue_bis = ctx.getChild(3).accept(this);
            return new LvalueBis(exp, lvalue_bis);
        } else {
            return exp;
        }
    }

    @Override
    public Ast visitLValueBis(exprParser.LValueBisContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        if (ctx.getChild(2).getChildCount() > 1) {
            Ast exp = ctx.getChild(2).accept(this);
            return new Lvalue(id, exp);
        } else {
            return id;
        }
    }

    @Override
    public Ast visitVoidLVAlueBis(exprParser.VoidLVAlueBisContext ctx) {
        return new VoidExpr();
    }

    @Override
    public Ast visitId(exprParser.IdContext ctx) {
        return new Id(ctx.getText());
    }

    @Override
    public Ast visitTypeDecl(exprParser.TypeDeclContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitVarDecl(exprParser.VarDeclContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitFunDecl(exprParser.FunDeclContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitType_id(exprParser.Type_idContext ctx) {

        return new TypeId(ctx.getText());
    }

    @Override
    public Ast visitType_declaration(exprParser.Type_declarationContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        Ast type = ctx.getChild(3).accept(this);
        return new TypeDeclaration(id, type);
    }

    @Override
    public Ast visitTypeID(exprParser.TypeIDContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitBaseArrayType(exprParser.BaseArrayTypeContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitBaseRecType(exprParser.BaseRecTypeContext ctx) {
        return ctx.getChild(0).accept(this);
    }

    @Override
    public Ast visitArrayType(exprParser.ArrayTypeContext ctx) {
        Ast type_id = ctx.getChild(1).accept(this);
        return type_id;
    }

    @Override
    public Ast visitRecType(exprParser.RecTypeContext ctx) {
        if (ctx.getChildCount() > 2) {

            RecType rec_list = new RecType();

            int i = 1;
            while (ctx.getChild(i).getChildCount() > 1) {
                rec_list.addField(ctx.getChild(i).accept(this));
                i++;
            }

            // for(int i = 1; i < ctx.getChildCount(); i++) {
            // rec_list.addField(ctx.getChild(i).accept(this));
            // }

            return rec_list;

        } else {
            return new RecType(); // peut etre probleme de null?
        }
    }

    @Override
    public Ast visitFieldDec(exprParser.FieldDecContext ctx) {
        Ast id = ctx.getChild(0).accept(this);
        Ast type = ctx.getChild(2).accept(this);
        return new FieldDec(id, type);
    }

    @Override
    public Ast visitType_fields(exprParser.Type_fieldsContext ctx) {
        Ast id = ctx.getChild(0).accept(this);
        if (ctx.getChildCount() > 2) {
            Ast type_fields = ctx.getChild(2).accept(this);
            return new TypeFields(id, type_fields);
        } else {
            return id;
        }
    }

    @Override
    public Ast visitTypeFields2(exprParser.TypeFields2Context ctx) {
        Ast fieldDec = ctx.getChild(1).accept(this);
        if (ctx.getChildCount() >= 3) {
            Ast type_fields = ctx.getChild(3).accept(this);
            return new TypeFields(fieldDec, type_fields);
        } else {
            return fieldDec;
        }
    }

    @Override
    public Ast visitVoidTypeFields(exprParser.VoidTypeFieldsContext ctx) {
        return new VoidExpr();
    }

    @Override
    public Ast visitVariable_declaration(exprParser.Variable_declarationContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        Ast variable = ctx.getChild(2).accept(this);
        return new VariableDeclaration(id, variable);
    }

    @Override
    public Ast visitAssExpr(exprParser.AssExprContext ctx) {
        return ctx.getChild(1).accept(this);
    }

    @Override
    public Ast visitTypeIdExpr(exprParser.TypeIdExprContext ctx) {
        Ast type_id = ctx.getChild(1).accept(this);
        Ast exp = ctx.getChild(3).accept(this);
        return new TypeIdExpr(type_id, exp);
    }

    @Override
    public Ast visitFunction_declaration(exprParser.Function_declarationContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        ArrayList<Ast> field_dec = new ArrayList<Ast>();

        int i = 3;
        while (ctx.getChild(i).getChildCount() >= 1) {
            field_dec.add(ctx.getChild(i).accept(this));
            i++;
        }
        // for(int i = 1; i < ctx.getChild(3).getChildCount(); i++) {
        // field_dec.add(ctx.getChild(3).getChild(i).accept(this));
        // }
        Ast function = ctx.getChild(1 + i).accept(this);
        return new FunctionDeclaration(id, field_dec, function);
    }

    @Override
    public Ast visitFunExpr(exprParser.FunExprContext ctx) {
        Ast exp = ctx.getChild(1).accept(this);
        return exp;
    }

    @Override
    public Ast visitFunTypeID(exprParser.FunTypeIDContext ctx) {
        Ast id = ctx.getChild(1).accept(this);
        Ast exp = ctx.getChild(3).accept(this);
        return new FunExpr(id, exp);
    }

    @Override
    public Ast visitPrint(exprParser.PrintContext ctx) {
        Ast exp = ctx.getChild(2).accept(this);
        return new Print(exp);
    }
}
