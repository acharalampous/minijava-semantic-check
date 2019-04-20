all: compile

compile:
	java -jar jtb132di.jar minijava.jj
	java -jar javacc5.jar minijava-jtb.jj
	javac Main.java

clean:
	rm -rf *.class *~ syntaxtree visitor JavaCharStream.java ParseException.java minijava-jtb.jj MiniJavaParser.java MiniJavaParserConstants.java MiniJavaParseTokenManager.java Token.java TokenMgrError.java MiniJavaParserTokenManager.java
