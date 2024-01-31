grammar expr;

@header {
package parser;
}

//Définition des non-terminaux

program:
	expr EOF
	;

expr:
	bool
;


segExp :
	'(' expr_seq? ')'
	;
negation :
	'-' expr
	;
callExp :
	id '(' expr_list? ')'
	;

bool :
	equal (('&'|'|') equal)*
    ;

equal :
	plus (('=' | '<>' | '<' | '>' | '<=' | '>=') plus)*
	;

plus : mult (('+'|'-')  mult)*
    ;

mult : valeur (('*'|'/') valeur)*
    ;
valeur :
	lvalue									#ValLValue
	| NIL									#ValNil
	| INTEGER_CONSTANT						#ValInt
	| STRING_CONSTANT						#ValString
	| segExp								#ValSegExp
	| negation								#ValNeg
	| callExp								#ValCallExp
    | arrCreate								#ValArrCreate
	| recCreate								#ValRecCreate
	| assignment							#ValAssign
	| ifThenElse							#ValIfThenElse
	| ifThen								#ValIfThen
	| whileExp								#ValWhileExp
	| forExp 								#ValForExp
	| 'break'								#ValBreak
	| letExp								#ValLetExp
	| print									#ValPrint
;

arrCreate :
	type_id '[' expr ']' ' of ' expr
	;

recCreate :
	type_id '{' field_list? '}'
	;

assignment :
	lvalue ':=' expr
	;

ifThen:
	'if' expr 'then' expr
	;

ifThenElse :
	'if' expr 'then' expr 'else' expr
	;

whileExp:
	'while' expr 'do' expr
	;

forExp :
	'for' id ':=' expr 'to' expr 'do' expr
	;

letExp :
	'let' declaration+ 'in' expr_seq? 'end'
	;


expr_seq:
	expr expr_seq2
	;

expr_seq2:
	';' expr expr_seq2    				#ExprSeq2
	| 									#VoidExprSeq2
	;

expr_list:
	expr expr_list2
	;

expr_list2:
	',' expr expr_list2	 #ExprList2
	|					#VoidExprList2
	;

field_list:
	id '=' expr field_list2
	;

field_list2:
	',' id '=' expr field_list2			#FieldList2
	|									#VoidFieldList2
	;

lvalue:
	id lvaluebis
	;


lvaluebis:
	'[' expr ']' lvaluebis				#ExprLValueBis
	| '.' id lvaluebis					#LValueBis
	| 									#VoidLVAlueBis
	;

id:
	IDENTIFIER
	;


declaration:
	type_declaration					#TypeDecl
	| variable_declaration				#VarDecl
	| function_declaration				#FunDecl
	;

type_id:
	IDENTIFIER
	;

type_declaration:
	'type' type_id '=' type
	;

type:
	type_id 							#TypeID
	| arrayType			#BaseArrayType
	| recType			#BaseRecType
	;

arrayType:
	'array of ' type_id
	;

recType:
	'{' type_fields '}'
	;

fieldDec:
	id ':' type_id
	;

type_fields:
	fieldDec type_fields2
	;

type_fields2:
	',' fieldDec type_fields2			#TypeFields2
	|									#VoidTypeFields
	;


variable_declaration:
	'var' id variable
	;

variable:
	':=' expr							#AssExpr
	| ':' type_id ':=' expr				#TypeIdExpr
	;

function_declaration:
	'function' id '(' type_fields? ')' function
	;

function :
	'=' expr							#FunExpr
	| ':' type_id '=' expr				#FunTypeID
	;

print :
	'print' '(' expr ')'
	;

//Définition des terminaux
INTEGER_CONSTANT :
	('1'..'9')('0'..'9')*
	| '0'
	;
STRING_CONSTANT :
	'"' ('a'..'z'|'A'..'Z'|'0'..'9'| ' ' | '\n' | '\t' | '\\' | '.' | '(' | ')' | ',' | '!' | '?')* '"'
	;
IDENTIFIER :
	('A' ..'Z' | 'a' ..'z')('A' ..'Z' | 'a' ..'z' | '0'..'9' | '_')*
	;
NIL:
	'nil'
	;
COMMENT: '/*' .*? '*/' -> skip;
WS: [ \t\n\\]-> skip;