// Generated from expr.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link exprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface exprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(exprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(exprParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#segExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegExp(exprParser.SegExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(exprParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#callExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(exprParser.CallExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(exprParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(exprParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#plus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(exprParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#mult}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(exprParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValLValue}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValLValue(exprParser.ValLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValNil}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValNil(exprParser.ValNilContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValInt(exprParser.ValIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValString(exprParser.ValStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValSegExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValSegExp(exprParser.ValSegExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValNeg}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValNeg(exprParser.ValNegContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValCallExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValCallExp(exprParser.ValCallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValArrCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValArrCreate(exprParser.ValArrCreateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValRecCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValRecCreate(exprParser.ValRecCreateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValAssign}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValAssign(exprParser.ValAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValIfThenElse}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValIfThenElse(exprParser.ValIfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValIfThen}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValIfThen(exprParser.ValIfThenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValWhileExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValWhileExp(exprParser.ValWhileExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValForExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValForExp(exprParser.ValForExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValBreak}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValBreak(exprParser.ValBreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValLetExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValLetExp(exprParser.ValLetExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValPrint}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValPrint(exprParser.ValPrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#arrCreate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrCreate(exprParser.ArrCreateContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#recCreate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecCreate(exprParser.RecCreateContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#ifThen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThen(exprParser.IfThenContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#ifThenElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(exprParser.IfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#whileExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExp(exprParser.WhileExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#forExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForExp(exprParser.ForExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#letExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExp(exprParser.LetExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#expr_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_seq(exprParser.Expr_seqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSeq2(exprParser.ExprSeq2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidExprSeq2(exprParser.VoidExprSeq2Context ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(exprParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList2(exprParser.ExprList2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidExprList2(exprParser.VoidExprList2Context ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#field_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_list(exprParser.Field_listContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldList2(exprParser.FieldList2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidFieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidFieldList2(exprParser.VoidFieldList2Context ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(exprParser.LvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLValueBis(exprParser.ExprLValueBisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValueBis(exprParser.LValueBisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidLVAlueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidLVAlueBis(exprParser.VoidLVAlueBisContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(exprParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(exprParser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(exprParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDecl(exprParser.FunDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#type_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_id(exprParser.Type_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#type_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_declaration(exprParser.Type_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeID}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeID(exprParser.TypeIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BaseArrayType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseArrayType(exprParser.BaseArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BaseRecType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseRecType(exprParser.BaseRecTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(exprParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#recType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecType(exprParser.RecTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#fieldDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDec(exprParser.FieldDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#type_fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_fields(exprParser.Type_fieldsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeFields2}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeFields2(exprParser.TypeFields2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidTypeFields}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidTypeFields(exprParser.VoidTypeFieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(exprParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssExpr(exprParser.AssExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeIdExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdExpr(exprParser.TypeIdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(exprParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExpr(exprParser.FunExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunTypeID}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunTypeID(exprParser.FunTypeIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(exprParser.PrintContext ctx);
}