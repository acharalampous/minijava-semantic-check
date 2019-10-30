# BNF for minijava.jj

## NON-TERMINALS

<table>

<tbody>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod1">Goal</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[MainClass](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod2) ( [TypeDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod3) )* <EOF></td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod2">MainClass</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "{" "public" "static" "void" "main" "(" "String" "[" "]" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) ")" "{" ( [VarDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod5) )* ( [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6) )* "}" "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod3">TypeDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[ClassDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod7)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ClassExtendsDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod8)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod7">ClassDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "{" ( [VarDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod5) )* ( [MethodDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod9) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod8">ClassExtendsDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "extends" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "{" ( [VarDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod5) )* ( [MethodDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod9) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod5">VarDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Type](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod10) [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod9">MethodDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"public" [Type](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod10) [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "(" ( [FormalParameterList](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod11) )? ")" "{" ( [VarDeclaration](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod5) )* ( [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6) )* "return" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ";" "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod11">FormalParameterList</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[FormalParameter](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod13) ( [FormalParameterRest](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod14) )*</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod13">FormalParameter</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Type](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod10) [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod14">FormalParameterRest</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"," [FormalParameter](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod13)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod10">Type</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[ArrayType](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod15)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[BooleanType](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod16)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[IntegerType](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod17)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod15">ArrayType</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"int" "[" "]"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod16">BooleanType</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"boolean"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod17">IntegerType</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"int"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod6">Statement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Block](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod18)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[AssignmentStatement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod19)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayAssignmentStatement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod20)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[IfStatement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod21)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[WhileStatement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod22)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PrintStatement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod23)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod18">Block</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"{" ( [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod19">AssignmentStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "=" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod20">ArrayAssignmentStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "[" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) "]" "=" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod21">IfStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"if" "(" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ")" [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6) "else" [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod22">WhileStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"while" "(" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ")" [Statement](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod6)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod23">PrintStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"System.out.println" "(" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ")" ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod12">Expression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[AndExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod24)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[CompareExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod25)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PlusExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod26)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[MinusExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod27)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[TimesExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod28)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayLookup](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod29)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayLength](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod30)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[MessageSend](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod31)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Clause](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod24">AndExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Clause](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod43) "&&" [Clause](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod25">CompareExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "<" [PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod26">PlusExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "+" [PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod27">MinusExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "-" [PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod28">TimesExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "*" [PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod29">ArrayLookup</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "[" [PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "]"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod30">ArrayLength</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "." "length"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod31">MessageSend</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32) "." [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "(" ( [ExpressionList](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod33) )? ")"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod33">ExpressionList</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ( [ExpressionRest](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod34) )*</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod34">ExpressionRest</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"," [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod43">Clause</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[NotExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod41)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod32">PrimaryExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[IntegerLiteral](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod35)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[TrueLiteral](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod36)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[FalseLiteral](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod37)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ThisExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod38)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayAllocationExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod39)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[AllocationExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod40)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[BracketExpression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod42)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod35">IntegerLiteral</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE"><INTEGER_LITERAL></td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod36">TrueLiteral</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"true"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod37">FalseLiteral</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"false"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod4">Identifier</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE"><IDENTIFIER></td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod38">ThisExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"this"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod39">ArrayAllocationExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"new" "int" "[" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) "]"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod40">AllocationExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"new" [Identifier](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod4) "(" ")"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod41">NotExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"!" [Clause](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod42">BracketExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"(" [Expression](http://cgi.di.uoa.gr/~thp06/project_files/minijava-new/minijava.html#prod12) ")"</td>

</tr>

</tbody>

</table>
