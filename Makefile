main : 
	java -jar ./lib/antlr-4.9.2-complete.jar expr.g4 -listener -visitor -o ./src/parser
	javac  -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main.java -d ./bin
bad : 
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/bad.exp
declaration :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/declaration.exp
enchante :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/enchante.exp
erreur2 : 
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/erreur2.exp
erreur3 :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/erreur3.exp
erreurLexical :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/erreurLexical.exp
erreurSemantique :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/erreurSemantique.exp
for :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/for.exp
forfor :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/forfor.exp
function :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/function.exp
functionwoparam :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/functionwoparam.exp
hello :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/hello.exp
hello2 :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/hello2.exp
helloGoodComment:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/helloGoodComment.exp
helloBadComment :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/helloBadComment.exp
if :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/if.exp
ifthenthen:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/ifthenthen.exp
ifthenelse:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/ifthenelse.exp
opbinaire:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/opbinaire.exp
priorite:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/priorite.exp
priorite2:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/priorite2.exp
semGood:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/semGood.exp
simple:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/simple.exp
simple_array:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/simple_array.exp
somme:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/somme.exp
sujet:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/sujet.exp
triple:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/triple.exp

test:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/test.exp

test2:
    java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/test.exp
super_exemple:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/super_exemple.exp
soutenance:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/soutenance.exp
while:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/while.exp
whilewhile:
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/whilewhile.exp


run :
	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main $(target)

dot : 
	dot -Tsvg ./out/tree.dot -o ./out/tree.svg