/*******************************/
/* CheckVisitor.java */

/* Name:    Andreas Charalampous
 * String.M :    1115201500195
 * e-mail:  sdi1500195@di.uoa.gr
 */
/********************************/
import syntaxtree.*;
import visitor.GJDepthFirst;

public class CheckVisitor extends GJDepthFirst<String, String>{
    
	private SymbolTable symbol_table;
	private String cur_class;
	private String cur_method;

    /* Constructor */
    public CheckVisitor(SymbolTable st){
		symbol_table = st;
		cur_class = null;
		cur_method = null;
    }


    //
    // User-generated visitor methods below
    //

    /**
     * f0 -> MainClass()
     * f1 -> ( TypeDeclaration() )*
     * f2 -> <EOF>
     */
    public String visit(Goal n, String argu) throws Exception {
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return null;
    }
  
     /**
      * f0 -> "class"
      * f1 -> Identifier()
      * f2 -> "{"
      * f3 -> "public"
      * f4 -> "static"
      * f5 -> "void"
      * f6 -> "main"
      * f7 -> "("
      * f8 -> "String"
      * f9 -> "["
      * f10 -> "]"
      * f11 -> Identifier()
      * f12 -> ")"
      * f13 -> "{"
      * f14 -> ( VarDeclaration() )*
      * f15 -> ( Statement() )*
      * f16 -> "}"
      * f17 -> "}"
      */
     public String visit(MainClass n, String argu) throws Exception {
        String _ret=null;
		n.f1.accept(this, argu); // check if class has same name with file
		
		symbol_table.enter_scope(); // initialize scope hashmap
		n.f14.accept(this, argu);
		n.f15.accept(this, argu);
		symbol_table.exit_scope();
        return _ret;
    }
  
    /**
     * f0 -> ClassDeclaration()
     *       | ClassExtendsDeclaration()
     */
    public String visit(TypeDeclaration n, String argu) throws Exception {
        return n.f0.accept(this, argu);
    }
  
    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( VarDeclaration() )*
     * f4 -> ( MethodDeclaration() )*
     * f5 -> "}"
     */
    public String visit(ClassDeclaration n, String argu) throws Exception {
        cur_class = n.f1.accept(this, argu); // get class name
		n.f4.accept(this, argu);
		cur_class = null;
        return null;
    }
  
     /**
      * f0 -> "class"
      * f1 -> Identifier()
      * f2 -> "extends"
      * f3 -> Identifier()
      * f4 -> "{"
      * f5 -> ( VarDeclaration() )*
      * f6 -> ( MethodDeclaration() )*
      * f7 -> "}"
      */
    public String visit(ClassExtendsDeclaration n, String argu) throws Exception {
        cur_class =  n.f1.accept(this, argu);
        
		//n.f3.accept(this, argu);
		
		n.f6.accept(this, argu);
		
		cur_class = null;
		return null;
    }
  
     /**
      * f0 -> Type()
      * f1 -> Identifier()
      * f2 -> ";"
      */
    public String visit(VarDeclaration n, String argu) throws Exception {
		String type = n.f0.accept(this, argu);
		String name = n.f1.accept(this, argu);

		int result = symbol_table.insert(type, name);
		if(result == -1) // redeclaration
			if(cur_class == null) // in main
				throw new Exception("Error during declaration of " + type + " " + name + " in Main method : " + name + " is already declared");
			else // in class method
				throw new Exception("Error during declaration of " + type + " " + name + " in method " + cur_method + " of class " + cur_class + " : " + name + " is already declared");
		else if(result == -2) // undefined type
			if(cur_class == null) // in main
				throw new Exception("Error during declaration of " + type + " " + name + " in Main method : Undefined reference to type " + type + "." );
			else // in class method
				throw new Exception("Error during declaration of " + type + " " + name + " in method " + cur_method + " of class " + cur_class + " : Undefined reference to type " + type + ".");


		return null;
	}
  
     /**
      * f0 -> "public"
      * f1 -> Type()
      * f2 -> Identifier()
      * f3 -> "("
      * f4 -> ( FormalParameterList() )?
      * f5 -> ")"
      * f6 -> "{"
      * f7 -> ( VarDeclaration() )*
      * f8 -> ( Statement() )*
      * f9 -> "return"
      * f10 -> Expression()
      * f11 -> ";"
      * f12 -> "}"
      */
     public String visit(MethodDeclaration n, String argu) throws Exception {
        String _ret = null;
        String type = n.f1.accept(this, argu);
        cur_method = n.f2.accept(this, argu);
	
		symbol_table.insert_arguments(cur_class, cur_method); // store arguments in hashmap

		n.f7.accept(this, argu);
        n.f8.accept(this, argu);
		String return_type = n.f10.accept(this, argu);
		if(!return_type.equals(type))
			throw new Exception("Error in method " + cur_method + " of class" + cur_class + " : Invalid return type.");
        return _ret;
     }
  
     /**
      * f0 -> FormalParameter()
      * f1 -> FormalParameterTail()
      */
     public String visit(FormalParameterList n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> Type()
      * f1 -> Identifier()
      */
     public String visit(FormalParameter n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> ( FormalParameterTerm() )*
      */
     public String visit(FormalParameterTail n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> ","
      * f1 -> FormalParameter()
      */
     public String visit(FormalParameterTerm n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> ArrayType()
      *       | BooleanType()
      *       | IntegerType()
      *       | Identifier()
      */
     public String visit(Type n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> "int"
      * f1 -> "["
      * f2 -> "]"
      */
     public String visit(ArrayType n, String argu) throws Exception {
        return "int[]";
     }
  
     /**
      * f0 -> "boolean"
      */
     public String visit(BooleanType n, String argu) throws Exception {
        return "boolean";
     }
  
     /**
      * f0 -> "int"
      */
     public String visit(IntegerType n, String argu) throws Exception {
        return "int";
     }
  
     /**
      * f0 -> Block()
      *       | AssignmentStatement()
      *       | ArrayAssignmentStatement()
      *       | IfStatement()
      *       | WhileStatement()
      *       | PrintStatement()
      */
     public String visit(Statement n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> "{"
      * f1 -> ( Statement() )*
      * f2 -> "}"
      */
     public String visit(Block n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> Identifier()
      * f1 -> "="
      * f2 -> Expression()
      * f3 -> ";"
      */
     public String visit(AssignmentStatement n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> Identifier()
      * f1 -> "["
      * f2 -> Expression()
      * f3 -> "]"
      * f4 -> "="
      * f5 -> Expression()
      * f6 -> ";"
      */
     public String visit(ArrayAssignmentStatement n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> "if"
      * f1 -> "("
      * f2 -> Expression()
      * f3 -> ")"
      * f4 -> Statement()
      * f5 -> "else"
      * f6 -> Statement()
      */
     public String visit(IfStatement n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        n.f6.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> "while"
      * f1 -> "("
      * f2 -> Expression()
      * f3 -> ")"
      * f4 -> Statement()
      */
     public String visit(WhileStatement n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> "System.out.println"
      * f1 -> "("
      * f2 -> Expression()
      * f3 -> ")"
      * f4 -> ";"
      */
     public String visit(PrintStatement n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> AndExpression()
      *       | CompareExpression()
      *       | PlusExpression()
      *       | MinusExpression()
      *       | TimesExpression()
      *       | ArrayLookup()
      *       | ArrayLength()
      *       | MessageSend()
      *       | Clause()
      */
     public String visit(Expression n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> Clause()
      * f1 -> "&&"
      * f2 -> Clause()
      */
     public String visit(AndExpression n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "<"
      * f2 -> PrimaryExpression()
      */
     public String visit(CompareExpression n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "+"
      * f2 -> PrimaryExpression()
      */
     public String visit(PlusExpression n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "-"
      * f2 -> PrimaryExpression()
      */
     public String visit(MinusExpression n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "*"
      * f2 -> PrimaryExpression()
      */
     public String visit(TimesExpression n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "["
      * f2 -> PrimaryExpression()
      * f3 -> "]"
      */
     public String visit(ArrayLookup n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "."
      * f2 -> "length"
      */
     public String visit(ArrayLength n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> PrimaryExpression()
      * f1 -> "."
      * f2 -> Identifier()
      * f3 -> "("
      * f4 -> ( ExpressionList() )?
      * f5 -> ")"
      */
     public String visit(MessageSend n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        n.f2.accept(this, argu);
        n.f3.accept(this, argu);
        n.f4.accept(this, argu);
        n.f5.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> Expression()
      * f1 -> ExpressionTail()
      */
     public String visit(ExpressionList n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> ( ExpressionTerm() )*
      */
     public String visit(ExpressionTail n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> ","
      * f1 -> Expression()
      */
     public String visit(ExpressionTerm n, String argu) throws Exception {
        String _ret=null;
        n.f0.accept(this, argu);
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> NotExpression()
      *       | PrimaryExpression()
      */
     public String visit(Clause n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> IntegerLiteral()
      *       | TrueLiteral()
      *       | FalseLiteral()
      *       | Identifier()
      *       | ThisExpression()
      *       | ArrayAllocationExpression()
      *       | AllocationExpression()
      *       | BracketExpression()
      */
     public String visit(PrimaryExpression n, String argu) throws Exception {
        return n.f0.accept(this, argu);
     }
  
     /**
      * f0 -> <INTEGER_LITERAL>
      */
     public String visit(IntegerLiteral n, String argu) throws Exception {
        return n.f0.tokenImage; // must be turned into integer
     }
  
     /**
      * f0 -> "true"
      */
     public String visit(TrueLiteral n, String argu) throws Exception {
        return "true";
     }
  
     /**
      * f0 -> "false"
      */
     public String visit(FalseLiteral n, String argu) throws Exception {
        return "false";
     }
  
     /**
      * f0 -> <IDENTIFIER>
      */
     public String visit(Identifier n, String argu) throws Exception {
        return n.f0.tokenImage;
     }
  
     /**
      * f0 -> "this"
      */
     public String visit(ThisExpression n, String argu) throws Exception {
        return "this";
     }
  
     /**
      * f0 -> "new"
      * f1 -> "int"
      * f2 -> "["
      * f3 -> Expression()
      * f4 -> "]"
      */
     public String visit(ArrayAllocationExpression n, String argu) throws Exception {
        String type = n.f3.accept(this, argu);
		if(!type.equals("int"))
			if (cur_class == null) // in main
				throw new Exception( "Error during new array operation in Main method : Size of table must be an int value.");
			else // in class method
				throw new Exception("Error during new operation in method " + cur_method + " of class " + cur_class + " : Size of table must be an int value.");
		
        return null;
     }
  
     /**
      * f0 -> "new"
      * f1 -> Identifier()
      * f2 -> "("
      * f3 -> ")"
      */
     public String visit(AllocationExpression n, String argu) throws Exception {
        String type = n.f1.accept(this, argu);
		/* Check if valid type */
		if(!symbol_table.is_valid_type(type))
			if(cur_class == null) // in main
				throw new Exception("Error during new operation in Main method : Undefined reference to type " + type + ".");
			else // in class method
				throw new Exception("Error during new operation in method " + cur_method + " of class " + cur_class + " : Undefined reference to type " + type + ".");
		
        return null;
     }
  
     /**
      * f0 -> "!"
      * f1 -> Clause()
      */
     public String visit(NotExpression n, String argu) throws Exception {
		String _ret = null;
        n.f1.accept(this, argu);
        return _ret;
     }
  
     /**
      * f0 -> "("
      * f1 -> Expression()
      * f2 -> ")"
      */
	public String visit(BracketExpression n, String argu) throws Exception {
		String ret = n.f1.accept(this, argu);
        return ret;
	}

}