grammar IsiLang;

@header{
	import datastructures.IsiSymbol;
	import datastructures.IsiVariable;
	import datastructures.IsiSymbolTable;
	import exceptions.IsiSemanticException;
	import ast.IsiProgram;
	import ast.AbstractCommand;
	import ast.CommandRead;  //import ast.CommandLeitura;
	import ast.CommandWrite; //import ast.CommandEscrita
	import ast.CommandAttrib; //import ast.CommandAtribuicao
	import ast.CommandIf; //import ast.CommandDecisao
	import ast.CommandWhile; 
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.List;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private List<String> _unusedVariables = new ArrayList<String>(); //Variaveis nao utilizadas
	private IsiSymbol symbol;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>(); //Pilha para todos os comandos
	
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;                                            //private String _exprCondition -- Expressao IF
	private Stack<String> _exprDecisionStack = new Stack<String>();          //Pilha para IF
	private String _exprWhile;                                               //Expressao While
	private Stack<String> _exprWhileStack = new Stack<String>();             //Pilha para While
	private ArrayList<AbstractCommand> ifList;  //listaTrue 
	private ArrayList<AbstractCommand> elseList; //listaFalse
	private ArrayList<AbstractCommand> whileList; //listaWhile
	
	public void verificaID(String id){
		if(!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol " + id + " not declared");
		}
	}
	
	public void verificaTipo(String id, int type){
		if(((IsiVariable) symbolTable.get(id)).getType() != type){
			throw new IsiSemanticException("Symbol " + id + " has wrong type");
		}
	}
	
	public void exibeComandos(){
		for(AbstractCommand c : program.getCommands()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
	}
}

prog	: 'programa' decl bloco 'fimprog;'
		  { program.setVarTable(symbolTable); 		  	
		    program.setCommands(stack.pop());
		    if(_unusedVariables.size() > 0){
		    	System.err.println("Unused variables: " + _unusedVariables);
		    }
		  }
		;
		
decl	: (declaraVar)+
		;

declaraVar	: tipo ID {
					 _varName = _input.LT(-1).getText();
					 _varValue = null;
					 symbol = new IsiVariable(_varName, _tipo, _varValue);
					 if(!symbolTable.exists(_varName)){
					 	symbolTable.add(symbol);
					 	_unusedVariables.add(_varName);
					 }
					 else{
					 	throw new IsiSemanticException("Symbol " + _varName + " already declared");
					 }
					}
			  (  VIR 
			     ID  {
					 _varName = _input.LT(-1).getText();
					 _varValue = null;
					 symbol = new IsiVariable(_varName, _tipo, _varValue);
					 if(!symbolTable.exists(_varName)){
					 	symbolTable.add(symbol);
					 	_unusedVariables.add(_varName);
					 }
					 else{
					 	throw new IsiSemanticException("Symbol " + _varName + " already declared");
					 }
				    }
			  )* SC
			;

tipo	: 'numero' { _tipo = IsiVariable.NUMBER; }
		| 'texto'  { _tipo = IsiVariable.TEXT; }
		| 'booleano' { _tipo = IsiVariable.BOOLEAN; }
		;
		
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
			stack.push(curThread);
		  }
          (cmd)+
		;
		
cmd		: cmdRead    //cmdleitura 
		| cmdWrite   //cmdescrita 
		| cmdAttrib  
		| cmdIf      //cmdselecao
		| cmdWhile
		;
		
cmdRead	: 'leia' AP 
		  ID { verificaID(_input.LT(-1).getText());
			   _readID = _input.LT(-1).getText();
			 }
		  FP
		  SC {
			   IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			   CommandRead cmd = new CommandRead(_readID, var);
			   stack.peek().add(cmd);
			 }
		 ;

cmdWrite	: 'escreva' AP 
						ID { verificaID(_input.LT(-1).getText()); 
						   	 _writeID = _input.LT(-1).getText();
						   } 
						FP 
						SC {
							CommandWrite cmd = new CommandWrite(_writeID);
							stack.peek().add(cmd);
						   }
			;
			
cmdAttrib	: ID { _varName = _input.LT(-1).getText();
				   verificaID(_varName); 
				   _unusedVariables.remove(_varName);
				   _exprID = _varName;
				 }
			  ATTR { _exprContent = ""; }
			  expr 
			  SC { 
			  	   verificaTipo(_varName, _tipo);
			       CommandAttrib cmd = new CommandAttrib(_exprID, _exprContent);
			  	   stack.peek().add(cmd);
			     } 
			;

cmdIf	: 'se' AP (
                  ( 
				   (ID | NUMBER | TEXT | BOOLEAN) { _exprDecision = _input.LT(-1).getText(); }
				   OPREL { _exprDecision += _input.LT(-1).getText(); }
				   (ID | NUMBER | TEXT | BOOLEAN) { _exprDecision += _input.LT(-1).getText(); }
				  ) | BOOLEAN { _exprDecision = _input.LT(-1).getText(); }
				  ) 
				   FP { _exprDecisionStack.push(_exprDecision); }
				   ACH { curThread = new ArrayList<AbstractCommand>();
				   	     stack.push(curThread);
				       }
				   (cmd)+ 
				   FCH { ifList = stack.pop();
				       }
				('senao' 
				   ACH {
				   	 curThread = new ArrayList<AbstractCommand>();
				   	 stack.push(curThread);
				   }
				   (cmd+) 
				   FCH {
				   	 elseList = stack.pop();
				   	 CommandIf cmd = new CommandIf(_exprDecisionStack.pop(), ifList, elseList);
				   	 stack.peek().add(cmd);
				   }
				)?
				{  if(elseList == null){
				   		CommandIf cmd = new CommandIf(_exprDecisionStack.pop(), ifList, new ArrayList<AbstractCommand>());
				   		stack.peek().add(cmd);
				   }
				   elseList = null;
				}
			;

cmdWhile	: 'enquanto' AP
				(
				(
				 (ID | NUMBER | TEXT | BOOLEAN) { _exprWhile = _input.LT(-1).getText(); }
				 OPREL { _exprWhile += _input.LT(-1).getText(); }
				 (ID | NUMBER | TEXT | BOOLEAN) { _exprWhile += _input.LT(-1).getText(); }
				) | BOOLEAN { _exprWhile = _input.LT(-1).getText(); }
				)
				 FP { _exprWhileStack.push(_exprWhile); }
				 ACH { curThread = new ArrayList<AbstractCommand>();
				       stack.push(curThread);				     
				     }
				 (cmd)+
				 FCH { whileList = stack.pop();
				       CommandWhile cmd = new CommandWhile(_exprWhileStack.pop(), whileList);
				       stack.peek().add(cmd);
				     }
			;

expr	: termo ( OP { _exprContent += _input.LT(-1).getText(); } expr)? |
          OPMATH { _exprContent += _input.LT(-1).getText(); }
          termo ( OP { _exprContent += _input.LT(-1).getText(); } expr)? 
		;
	
termo	: ID { 
			   verificaID(_input.LT(-1).getText());
			   _tipo = ((IsiVariable) symbolTable.get(_input.LT(-1).getText())).getType();
			   _exprContent += _input.LT(-1).getText();
			 } 
		| NUMBER {
		       _tipo = IsiVariable.NUMBER;
			   _exprContent += _input.LT(-1).getText();
		}
		| TEXT {
			   _tipo = IsiVariable.TEXT;
			   _exprContent += _input.LT(-1).getText();
		}
		| BOOLEAN {
			   _tipo = IsiVariable.BOOLEAN;
			   _exprContent += _input.LT(-1).getText();
		}
		;

// Operacoes matematicas
OPMATH	: 'raiz' | 'log'
      	;

// Abrir parentese			
AP	: '('
	;

// Fechar parentese
FP	: ')'
	;

// Ponto-virgula
SC	: ';'
	;

// Operadores	
OP	: '+' | '-' | '*' | '/' | '**'
	;
	
// Atribuicao
ATTR	: '='
		;

// Virgula
VIR	: ','
	;
	
ASPAS	: '"'
		;

// Abrir chaves	
ACH	: '{'
	;

// Fechar chaves
FCH	: '}'
	;

// Operadores relacionais	
OPREL	: '>' | '<' | '>=' | '<=' | '==' | '!='
		;

// Valores booleanos
BOOLEAN	: 'true' | 'false'
		;

// Identificadores		
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
// Numeros
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

// String
TEXT	: ASPAS ( [a-z] | [A-Z] | [0-9] | ' ')+ ASPAS
		;

// Espaço
WS	: (' ' | '\t' | '\n' | '\r') -> skip;