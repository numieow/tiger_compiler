// Generated from expr.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link exprParser}.
 */
public interface exprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(exprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(exprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(exprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(exprParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#segExp}.
	 * @param ctx the parse tree
	 */
	void enterSegExp(exprParser.SegExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#segExp}.
	 * @param ctx the parse tree
	 */
	void exitSegExp(exprParser.SegExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(exprParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(exprParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#callExp}.
	 * @param ctx the parse tree
	 */
	void enterCallExp(exprParser.CallExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#callExp}.
	 * @param ctx the parse tree
	 */
	void exitCallExp(exprParser.CallExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(exprParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(exprParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(exprParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(exprParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(exprParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(exprParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(exprParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(exprParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValLValue}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValLValue(exprParser.ValLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValLValue}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValLValue(exprParser.ValLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValNil}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValNil(exprParser.ValNilContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValNil}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValNil(exprParser.ValNilContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValInt(exprParser.ValIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValInt(exprParser.ValIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValString(exprParser.ValStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValString(exprParser.ValStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValSegExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValSegExp(exprParser.ValSegExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValSegExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValSegExp(exprParser.ValSegExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValNeg}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValNeg(exprParser.ValNegContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValNeg}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValNeg(exprParser.ValNegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValCallExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValCallExp(exprParser.ValCallExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValCallExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValCallExp(exprParser.ValCallExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValArrCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValArrCreate(exprParser.ValArrCreateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValArrCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValArrCreate(exprParser.ValArrCreateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValRecCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValRecCreate(exprParser.ValRecCreateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValRecCreate}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValRecCreate(exprParser.ValRecCreateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValAssign}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValAssign(exprParser.ValAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValAssign}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValAssign(exprParser.ValAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValIfThenElse}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValIfThenElse(exprParser.ValIfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValIfThenElse}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValIfThenElse(exprParser.ValIfThenElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValIfThen}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValIfThen(exprParser.ValIfThenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValIfThen}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValIfThen(exprParser.ValIfThenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValWhileExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValWhileExp(exprParser.ValWhileExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValWhileExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValWhileExp(exprParser.ValWhileExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValForExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValForExp(exprParser.ValForExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValForExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValForExp(exprParser.ValForExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValBreak}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValBreak(exprParser.ValBreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValBreak}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValBreak(exprParser.ValBreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValLetExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValLetExp(exprParser.ValLetExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValLetExp}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValLetExp(exprParser.ValLetExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValPrint}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void enterValPrint(exprParser.ValPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValPrint}
	 * labeled alternative in {@link exprParser#valeur}.
	 * @param ctx the parse tree
	 */
	void exitValPrint(exprParser.ValPrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#arrCreate}.
	 * @param ctx the parse tree
	 */
	void enterArrCreate(exprParser.ArrCreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#arrCreate}.
	 * @param ctx the parse tree
	 */
	void exitArrCreate(exprParser.ArrCreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#recCreate}.
	 * @param ctx the parse tree
	 */
	void enterRecCreate(exprParser.RecCreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#recCreate}.
	 * @param ctx the parse tree
	 */
	void exitRecCreate(exprParser.RecCreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#ifThen}.
	 * @param ctx the parse tree
	 */
	void enterIfThen(exprParser.IfThenContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#ifThen}.
	 * @param ctx the parse tree
	 */
	void exitIfThen(exprParser.IfThenContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElse(exprParser.IfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElse(exprParser.IfThenElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#whileExp}.
	 * @param ctx the parse tree
	 */
	void enterWhileExp(exprParser.WhileExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#whileExp}.
	 * @param ctx the parse tree
	 */
	void exitWhileExp(exprParser.WhileExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#forExp}.
	 * @param ctx the parse tree
	 */
	void enterForExp(exprParser.ForExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#forExp}.
	 * @param ctx the parse tree
	 */
	void exitForExp(exprParser.ForExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#letExp}.
	 * @param ctx the parse tree
	 */
	void enterLetExp(exprParser.LetExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#letExp}.
	 * @param ctx the parse tree
	 */
	void exitLetExp(exprParser.LetExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expr_seq}.
	 * @param ctx the parse tree
	 */
	void enterExpr_seq(exprParser.Expr_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expr_seq}.
	 * @param ctx the parse tree
	 */
	void exitExpr_seq(exprParser.Expr_seqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 */
	void enterExprSeq2(exprParser.ExprSeq2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 */
	void exitExprSeq2(exprParser.ExprSeq2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 */
	void enterVoidExprSeq2(exprParser.VoidExprSeq2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidExprSeq2}
	 * labeled alternative in {@link exprParser#expr_seq2}.
	 * @param ctx the parse tree
	 */
	void exitVoidExprSeq2(exprParser.VoidExprSeq2Context ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(exprParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(exprParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 */
	void enterExprList2(exprParser.ExprList2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 */
	void exitExprList2(exprParser.ExprList2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 */
	void enterVoidExprList2(exprParser.VoidExprList2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidExprList2}
	 * labeled alternative in {@link exprParser#expr_list2}.
	 * @param ctx the parse tree
	 */
	void exitVoidExprList2(exprParser.VoidExprList2Context ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#field_list}.
	 * @param ctx the parse tree
	 */
	void enterField_list(exprParser.Field_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#field_list}.
	 * @param ctx the parse tree
	 */
	void exitField_list(exprParser.Field_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 */
	void enterFieldList2(exprParser.FieldList2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 */
	void exitFieldList2(exprParser.FieldList2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidFieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 */
	void enterVoidFieldList2(exprParser.VoidFieldList2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidFieldList2}
	 * labeled alternative in {@link exprParser#field_list2}.
	 * @param ctx the parse tree
	 */
	void exitVoidFieldList2(exprParser.VoidFieldList2Context ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(exprParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(exprParser.LvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void enterExprLValueBis(exprParser.ExprLValueBisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void exitExprLValueBis(exprParser.ExprLValueBisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void enterLValueBis(exprParser.LValueBisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LValueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void exitLValueBis(exprParser.LValueBisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidLVAlueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void enterVoidLVAlueBis(exprParser.VoidLVAlueBisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidLVAlueBis}
	 * labeled alternative in {@link exprParser#lvaluebis}.
	 * @param ctx the parse tree
	 */
	void exitVoidLVAlueBis(exprParser.VoidLVAlueBisContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(exprParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(exprParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(exprParser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(exprParser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(exprParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(exprParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunDecl(exprParser.FunDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunDecl}
	 * labeled alternative in {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunDecl(exprParser.FunDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#type_id}.
	 * @param ctx the parse tree
	 */
	void enterType_id(exprParser.Type_idContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#type_id}.
	 * @param ctx the parse tree
	 */
	void exitType_id(exprParser.Type_idContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration(exprParser.Type_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration(exprParser.Type_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeID}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeID(exprParser.TypeIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeID}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeID(exprParser.TypeIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BaseArrayType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBaseArrayType(exprParser.BaseArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BaseArrayType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBaseArrayType(exprParser.BaseArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BaseRecType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBaseRecType(exprParser.BaseRecTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BaseRecType}
	 * labeled alternative in {@link exprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBaseRecType(exprParser.BaseRecTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(exprParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(exprParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#recType}.
	 * @param ctx the parse tree
	 */
	void enterRecType(exprParser.RecTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#recType}.
	 * @param ctx the parse tree
	 */
	void exitRecType(exprParser.RecTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#fieldDec}.
	 * @param ctx the parse tree
	 */
	void enterFieldDec(exprParser.FieldDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#fieldDec}.
	 * @param ctx the parse tree
	 */
	void exitFieldDec(exprParser.FieldDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#type_fields}.
	 * @param ctx the parse tree
	 */
	void enterType_fields(exprParser.Type_fieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#type_fields}.
	 * @param ctx the parse tree
	 */
	void exitType_fields(exprParser.Type_fieldsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeFields2}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 */
	void enterTypeFields2(exprParser.TypeFields2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeFields2}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 */
	void exitTypeFields2(exprParser.TypeFields2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidTypeFields}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 */
	void enterVoidTypeFields(exprParser.VoidTypeFieldsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidTypeFields}
	 * labeled alternative in {@link exprParser#type_fields2}.
	 * @param ctx the parse tree
	 */
	void exitVoidTypeFields(exprParser.VoidTypeFieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(exprParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(exprParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterAssExpr(exprParser.AssExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitAssExpr(exprParser.AssExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeIdExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdExpr(exprParser.TypeIdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeIdExpr}
	 * labeled alternative in {@link exprParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdExpr(exprParser.TypeIdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(exprParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(exprParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunExpr(exprParser.FunExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunExpr(exprParser.FunExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunTypeID}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunTypeID(exprParser.FunTypeIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunTypeID}
	 * labeled alternative in {@link exprParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunTypeID(exprParser.FunTypeIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(exprParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(exprParser.PrintContext ctx);
}