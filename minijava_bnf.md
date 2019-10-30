# BNF for minijava.jj

## NON-TERMINALS

<table>

<tbody>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod1">Goal</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[MainClass](#prod2) ( [TypeDeclaration](#prod3) )* <EOF></td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod2">MainClass</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](#prod4) "{" "public" "static" "void" "main" "(" "String" "[" "]" [Identifier](#prod4) ")" "{" ( [VarDeclaration](#prod5) )* ( [Statement](#prod6) )* "}" "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod3">TypeDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[ClassDeclaration](#prod7)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ClassExtendsDeclaration](#prod8)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod7">ClassDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](#prod4) "{" ( [VarDeclaration](#prod5) )* ( [MethodDeclaration](#prod9) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod8">ClassExtendsDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"class" [Identifier](#prod4) "extends" [Identifier](#prod4) "{" ( [VarDeclaration](#prod5) )* ( [MethodDeclaration](#prod9) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod5">VarDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Type](#prod10) [Identifier](#prod4) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod9">MethodDeclaration</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"public" [Type](#prod10) [Identifier](#prod4) "(" ( [FormalParameterList](#prod11) )? ")" "{" ( [VarDeclaration](#prod5) )* ( [Statement](#prod6) )* "return" [Expression](#prod12) ";" "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod11">FormalParameterList</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[FormalParameter](#prod13) ( [FormalParameterRest](#prod14) )*</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod13">FormalParameter</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Type](#prod10) [Identifier](#prod4)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod14">FormalParameterRest</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"," [FormalParameter](#prod13)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod10">Type</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[ArrayType](#prod15)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[BooleanType](#prod16)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[IntegerType](#prod17)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Identifier](#prod4)</td>

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

<td align="LEFT" valign="BASELINE">[Block](#prod18)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[AssignmentStatement](#prod19)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayAssignmentStatement](#prod20)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[IfStatement](#prod21)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[WhileStatement](#prod22)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PrintStatement](#prod23)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod18">Block</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"{" ( [Statement](#prod6) )* "}"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod19">AssignmentStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Identifier](#prod4) "=" [Expression](#prod12) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod20">ArrayAssignmentStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Identifier](#prod4) "[" [Expression](#prod12) "]" "=" [Expression](#prod12) ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod21">IfStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"if" "(" [Expression](#prod12) ")" [Statement](#prod6) "else" [Statement](#prod6)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod22">WhileStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"while" "(" [Expression](#prod12) ")" [Statement](#prod6)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod23">PrintStatement</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"System.out.println" "(" [Expression](#prod12) ")" ";"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod12">Expression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[AndExpression](#prod24)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[CompareExpression](#prod25)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PlusExpression](#prod26)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[MinusExpression](#prod27)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[TimesExpression](#prod28)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayLookup](#prod29)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayLength](#prod30)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[MessageSend](#prod31)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Clause](#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod24">AndExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Clause](#prod43) "&&" [Clause](#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod25">CompareExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "<" [PrimaryExpression](#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod26">PlusExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "+" [PrimaryExpression](#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod27">MinusExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "-" [PrimaryExpression](#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod28">TimesExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "*" [PrimaryExpression](#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod29">ArrayLookup</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "[" [PrimaryExpression](#prod32) "]"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod30">ArrayLength</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "." "length"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod31">MessageSend</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32) "." [Identifier](#prod4) "(" ( [ExpressionList](#prod33) )? ")"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod33">ExpressionList</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[Expression](#prod12) ( [ExpressionRest](#prod34) )*</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod34">ExpressionRest</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"," [Expression](#prod12)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod43">Clause</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[NotExpression](#prod41)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[PrimaryExpression](#prod32)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod32">PrimaryExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">[IntegerLiteral](#prod35)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[TrueLiteral](#prod36)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[FalseLiteral](#prod37)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[Identifier](#prod4)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ThisExpression](#prod38)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[ArrayAllocationExpression](#prod39)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[AllocationExpression](#prod40)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"></td>

<td align="CENTER" valign="BASELINE">|</td>

<td align="LEFT" valign="BASELINE">[BracketExpression](#prod42)</td>

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

<td align="LEFT" valign="BASELINE">"new" "int" "[" [Expression](#prod12) "]"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod40">AllocationExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"new" [Identifier](#prod4) "(" ")"</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod41">NotExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"!" [Clause](#prod43)</td>

</tr>

<tr>

<td align="RIGHT" valign="BASELINE"><a name="prod42">BracketExpression</a></td>

<td align="CENTER" valign="BASELINE">::=</td>

<td align="LEFT" valign="BASELINE">"(" [Expression](#prod12) ")"</td>

</tr>

</tbody>

</table>
