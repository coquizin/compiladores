grammar IsiLang;

@header{
	import datastructures.IsiSymbol;
	import datastructures.IsiVariable;
	import datastructures.IsiSymbolTable;
	import exceptions.IsiSemanticException;
	import ast.IsiProgram;
	import ast.AbstractCommand;
	import ast.CommandRead;  
	import ast.CommandWrite; 
	import ast.CommandAttrib; 
	import ast.CommandIf; 
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbol symbol;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision; 
	private ArrayList<AbstractCommand> ifList;  
	private ArrayList<AbstractCommand> elseList; 
	
	public void verificaID(String id){
		if(!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol " + id + " not declared");
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
		  }
		;
		
decl	: (declaravar)+
		;

declaravar	: tipo ID {
					 _varName = _input.LT(-1).getText();
					 _varValue = null;
					 symbol = new IsiVariable(_varName, _tipo, _varValue);
					 if(!symbolTable.exists(_varName)){
					 	symbolTable.add(symbol);
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
					 }
					 else{
					 	throw new IsiSemanticException("Symbol " + _varName + " already declared");
					 }
				    }
			  )* SC
			;

tipo	: 'numero' { _tipo = IsiVariable.NUMBER; }
		| 'texto'  { _tipo = IsiVariable.TEXT; }
		;
		
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
			stack.push(curThread);
		  }
          (cmd)+
		;
		
cmd		: cmdRead    
		| cmdWrite    
		| cmdAttrib  
		| cmdIf     
		;
		
cmdRead	: 'leia' AP 
					 ID { verificaID(_input.LT(-1).getText());
					 	  _readID = _input.LT(-1).getText();
					 	}
					 FP
					 SC
			  {
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
						SC
						{
							CommandWrite cmd = new CommandWrite(_writeID);
							stack.peek().add(cmd);
						}
			;
			
cmdAttrib	: ID { verificaID(_input.LT(-1).getText()); 
				   _exprID = _input.LT(-1).getText();
				 }
			  ATTR { _exprContent = ""; }
			  expr 
			  SC { CommandAttrib cmd = new CommandAttrib(_exprID, _exprContent);
			  	   stack.peek().add(cmd);
			     } 
			;

cmdIf	: 'se' AP 
				   ID { _exprDecision = _input.LT(-1).getText(); }
				   OPREL { _exprDecision += _input.LT(-1).getText(); }
				   (ID | NUMBER) { _exprDecision += _input.LT(-1).getText(); }
				   FP 
				   ACH 
				   { curThread = new ArrayList<AbstractCommand>();
				   	 stack.push(curThread);
				   }
				   (cmd)+ 
				   FCH {
				   	  ifList = stack.pop();
				   }
				('senao' 
				   ACH {
				   	 curThread = new ArrayList<AbstractCommand>();
				   	 stack.push(curThread);
				   }
				   (cmd+) 
				   FCH {
				   	 elseList = stack.pop();
				   	 CommandIf cmd = new CommandIf(_exprDecision, ifList, elseList);
				   	 stack.peek().add(cmd);
				   }
				)?
			;

expr	: termo ( 
          OP    { _exprContent += _input.LT(-1).getText(); }
          termo 
          )*
		;
	
termo	: ID { verificaID(_input.LT(-1).getText()); 
			   _exprContent += _input.LT(-1).getText();
			 } 
		| NUMBER {
			   _exprContent += _input.LT(-1).getText();
		}
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
OP	: '+' | '-' | '*' | '/'
	;
	
// Atribuicao
ATTR	: '='
		;

// Virugla
VIR	: ','
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

// Identificadores		
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
// Numeros
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

// Espaço
WS	: (' ' | '\t' | '\n' | '\r') -> skip;