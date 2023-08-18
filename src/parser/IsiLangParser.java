// Generated from IsiLang.g4 by ANTLR 4.13.0
package parser;

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
	import ast.CommandDoWhile; 
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.List;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, AP=11, FP=12, SC=13, OP=14, ATTR=15, VIR=16, ASPAS=17, ACH=18, 
		FCH=19, OPREL=20, ID=21, NUMBER=22, TEXT=23, WS=24;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaraVar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdRead = 6, RULE_cmdWrite = 7, RULE_cmdAttrib = 8, 
		RULE_cmdIf = 9, RULE_cmdWhile = 10, RULE_cmdDoWhile = 11, RULE_expr = 12, 
		RULE_termo = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaraVar", "tipo", "bloco", "cmd", "cmdRead", "cmdWrite", 
			"cmdAttrib", "cmdIf", "cmdWhile", "cmdDoWhile", "expr", "termo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'faca'", "'('", "')'", "';'", null, 
			"'='", "','", "'\"'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "AP", 
			"FP", "SC", "OP", "ATTR", "VIR", "ASPAS", "ACH", "FCH", "OPREL", "ID", 
			"NUMBER", "TEXT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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
		private String _exprDoWhile;
		private Stack<String> _exprDoWhileStack = new Stack<String>();
		private ArrayList<AbstractCommand> ifList;  //listaTrue 
		private ArrayList<AbstractCommand> elseList; //listaFalse
		private ArrayList<AbstractCommand> whileList; //listaWhile
		private ArrayList<AbstractCommand> doWhileList;
		
		public IsiSymbol getSymbolID(String id){
			return symbolTable.get(id);
		}
		
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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
			match(T__1);
			 program.setVarTable(symbolTable); 		  	
					    program.setCommands(stack.pop());
					    if(_unusedVariables.size() > 0){
					    	System.err.println("Unused variables: " + _unusedVariables);
					    }
					  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public List<DeclaraVarContext> declaraVar() {
			return getRuleContexts(DeclaraVarContext.class);
		}
		public DeclaraVarContext declaraVar(int i) {
			return getRuleContext(DeclaraVarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declaraVar();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraVarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaraVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaraVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaraVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaraVar(this);
		}
	}

	public final DeclaraVarContext declaraVar() throws RecognitionException {
		DeclaraVarContext _localctx = new DeclaraVarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaraVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			tipo();
			setState(40);
			match(ID);

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
								
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
				match(ID);

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
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = IsiVariable.NUMBER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = IsiVariable.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
						stack.push(curThread);
					  
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				cmd();
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2098912L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdReadContext cmdRead() {
			return getRuleContext(CmdReadContext.class,0);
		}
		public CmdWriteContext cmdWrite() {
			return getRuleContext(CmdWriteContext.class,0);
		}
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdDoWhileContext cmdDoWhile() {
			return getRuleContext(CmdDoWhileContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				cmdRead();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				cmdWrite();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				cmdAttrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				cmdIf();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				cmdWhile();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 6);
				{
				setState(69);
				cmdDoWhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdReadContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdRead(this);
		}
	}

	public final CmdReadContext cmdRead() throws RecognitionException {
		CmdReadContext _localctx = new CmdReadContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__4);
			setState(73);
			match(AP);
			setState(74);
			match(ID);
			 verificaID(_input.LT(-1).getText());
						   _readID = _input.LT(-1).getText();
						 
			setState(76);
			match(FP);
			setState(77);
			match(SC);

						   IsiVariable var = (IsiVariable)symbolTable.get(_readID);
						   CommandRead cmd = new CommandRead(_readID, var);
						   
						   IsiSymbol symbolRead = getSymbolID(_readID);
						   IsiVariable variableRead = (IsiVariable) symbolRead;
						   variableRead.setValue("x");			   
						   stack.peek().add(cmd);
						 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWriteContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdWriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdWrite(this);
		}
	}

	public final CmdWriteContext cmdWrite() throws RecognitionException {
		CmdWriteContext _localctx = new CmdWriteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdWrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__5);
			setState(81);
			match(AP);
			setState(82);
			match(ID);
			 verificaID(_input.LT(-1).getText()); 
									   	 _writeID = _input.LT(-1).getText();
									   
			setState(84);
			match(FP);
			setState(85);
			match(SC);

										CommandWrite cmd = new CommandWrite(_writeID);
										IsiSymbol symbolWrite = getSymbolID(_writeID);
										IsiVariable variableWrite = (IsiVariable) symbolWrite;
										String x = variableWrite.getValue();
										stack.peek().add(cmd);
									   
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(ID);
			 _varName = _input.LT(-1).getText();
							   verificaID(_varName); 
							   _unusedVariables.remove(_varName);
							   _exprID = _varName;
							 
			setState(90);
			match(ATTR);
			 _exprContent = ""; 
			setState(92);
			expr();
			setState(93);
			match(SC);
			 
						  	   verificaTipo(_varName, _tipo);
						       CommandAttrib cmd = new CommandAttrib(_exprID, _exprContent);
						       IsiSymbol symbolAtt = getSymbolID(_exprID);
						       IsiVariable variableAtt = (IsiVariable) symbolAtt;
						       variableAtt.setValue(_exprContent);
						  	   stack.peek().add(cmd);
						     
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdIf(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__6);
			setState(97);
			match(AP);
			{
			{
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprDecision = _input.LT(-1).getText(); 
							   						  IsiSymbol symbol = getSymbolID(_exprDecision);
							   						  IsiVariable variable = (IsiVariable) symbol;
							   						  String str = variable.getValue();
							   					    
			setState(100);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(102);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 String var = _input.LT(-1).getText();
							   						  _exprDecision += var;
							   						  if (symbolTable.exists(var)){
							   							IsiSymbol symbolIf = getSymbolID(var);
							   							IsiVariable variableIf = (IsiVariable) symbolIf;
							   							String x = variableIf.getValue();
							   						  } 
							  						
			}
			}
			setState(105);
			match(FP);
			setState(106);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>();
							   	     stack.push(curThread);
							       
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				cmd();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2098912L) != 0) );
			setState(113);
			match(FCH);
			 ifList = stack.pop();
							       
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(115);
				match(T__7);
				setState(116);
				match(ACH);

								   	 curThread = new ArrayList<AbstractCommand>();
								   	 stack.push(curThread);
								   
				{
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(118);
					cmd();
					}
					}
					setState(121); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2098912L) != 0) );
				}
				setState(123);
				match(FCH);

								   	 elseList = stack.pop();
								   	 CommandIf cmd = new CommandIf(_exprDecision, ifList, elseList);
								   	 stack.peek().add(cmd);
								   
				}
			}

			  if(elseList == null){
							   		CommandIf cmd = new CommandIf(_exprDecision, ifList, new ArrayList<AbstractCommand>());
							   		stack.peek().add(cmd);
							   }
							   elseList = null;
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__8);
			setState(131);
			match(AP);
			{
			{
			setState(132);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprWhile = _input.LT(-1).getText(); 
							 						 IsiSymbol symbol = getSymbolID(_exprWhile);
							 						 IsiVariable variable = (IsiVariable) symbol;
							 						 String str = variable.getValue();
							 					   
			setState(134);
			match(OPREL);
			 _exprWhile += _input.LT(-1).getText(); 
			setState(136);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 String var = _input.LT(-1).getText(); 
							 						_exprWhile += var;
							 						if(symbolTable.exists(var)){
							 						  IsiSymbol symbolWhile = getSymbolID(var);
							 						  IsiVariable variableWhile = (IsiVariable) symbolWhile;
							 						  String x = variableWhile.getValue();
							 						} 
							 				       
			}
			}
			setState(139);
			match(FP);
			setState(140);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>();
							       stack.push(curThread);				     
							     
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				cmd();
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2098912L) != 0) );
			setState(147);
			match(FCH);
			 whileList = stack.pop();
							       CommandWhile cmd = new CommandWhile(_exprWhile, whileList);
							       stack.peek().add(cmd);
							     
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdDoWhileContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public CmdDoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdDoWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdDoWhile(this);
		}
	}

	public final CmdDoWhileContext cmdDoWhile() throws RecognitionException {
		CmdDoWhileContext _localctx = new CmdDoWhileContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdDoWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__9);
			setState(151);
			match(ACH);
			 curThread = new ArrayList<AbstractCommand>();
							           stack.push(curThread);
									 
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				cmd();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2098912L) != 0) );
			setState(158);
			match(FCH);
			setState(159);
			match(T__8);
			setState(160);
			match(AP);
			{
			{
			setState(161);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprDoWhile = _input.LT(-1).getText(); 
							       						  if(symbolTable.exists(_exprDoWhile)){
							       							IsiSymbol symbolDoWhile = getSymbolID(_exprDoWhile);
							       							IsiVariable variableDoWhile = (IsiVariable) symbolDoWhile;
							       							String str = variableDoWhile.getValue(); 
							       						  }
							       						
			setState(163);
			match(OPREL);
			 _exprDoWhile += _input.LT(-1).getText(); 
			setState(165);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 String var = _input.LT(-1).getText();
							       						  _exprDoWhile += var;
							       						  if(symbolTable.exists(var)){
							       							IsiSymbol symbolDoWhile = getSymbolID(var);
							       							IsiVariable variableDoWhile = (IsiVariable) symbolDoWhile;
							       							String x = variableDoWhile.getValue();
							       						  } 
							       						 
			}
			}
			setState(168);
			match(FP);
			setState(169);
			match(SC);

							     	doWhileList = stack.pop();
							     	CommandDoWhile cmd = new CommandDoWhile(_exprDoWhile, doWhileList);
							     	stack.peek().add(cmd);
							     
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			termo();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(173);
				match(OP);
				 _exprContent += _input.LT(-1).getText(); 
				setState(175);
				termo();
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(ID);
				 
							   verificaID(_input.LT(-1).getText());
							   _tipo = ((IsiVariable) symbolTable.get(_input.LT(-1).getText())).getType();
							   String var = _input.LT(-1).getText();
							   _exprContent += var;
							   IsiSymbol symbol = getSymbolID(var);
							   IsiVariable variable = (IsiVariable) symbol;
							   String x = variable.getValue(); 
							 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(NUMBER);

						       _tipo = IsiVariable.NUMBER;
							   _exprContent += _input.LT(-1).getText();
						
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				match(TEXT);

							   _tipo = IsiVariable.TEXT;
							   _exprContent += _input.LT(-1).getText();
						
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018\u00be\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001$\b\u0001\u000b"+
		"\u0001\f\u0001%\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002.\b\u0002\n\u0002\f\u00021\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u00039\b\u0003\u0001\u0004\u0001\u0004\u0004\u0004=\b\u0004\u000b\u0004"+
		"\f\u0004>\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005G\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\tn\b\t\u000b\t\f\to\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\tx\b\t\u000b\t\f\ty\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u007f\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0004\n\u0090\b\n\u000b\n\f\n\u0091\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u009b\b\u000b\u000b\u000b"+
		"\f\u000b\u009c\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u00b1\b\f\n\f\f\f\u00b4\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0003\r\u00bc\b\r\u0001\r\u0000\u0000\u000e\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0001\u0001"+
		"\u0000\u0015\u0017\u00c0\u0000\u001c\u0001\u0000\u0000\u0000\u0002#\u0001"+
		"\u0000\u0000\u0000\u0004\'\u0001\u0000\u0000\u0000\u00068\u0001\u0000"+
		"\u0000\u0000\b:\u0001\u0000\u0000\u0000\nF\u0001\u0000\u0000\u0000\fH"+
		"\u0001\u0000\u0000\u0000\u000eP\u0001\u0000\u0000\u0000\u0010X\u0001\u0000"+
		"\u0000\u0000\u0012`\u0001\u0000\u0000\u0000\u0014\u0082\u0001\u0000\u0000"+
		"\u0000\u0016\u0096\u0001\u0000\u0000\u0000\u0018\u00ac\u0001\u0000\u0000"+
		"\u0000\u001a\u00bb\u0001\u0000\u0000\u0000\u001c\u001d\u0005\u0001\u0000"+
		"\u0000\u001d\u001e\u0003\u0002\u0001\u0000\u001e\u001f\u0003\b\u0004\u0000"+
		"\u001f \u0005\u0002\u0000\u0000 !\u0006\u0000\uffff\uffff\u0000!\u0001"+
		"\u0001\u0000\u0000\u0000\"$\u0003\u0004\u0002\u0000#\"\u0001\u0000\u0000"+
		"\u0000$%\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000"+
		"\u0000\u0000&\u0003\u0001\u0000\u0000\u0000\'(\u0003\u0006\u0003\u0000"+
		"()\u0005\u0015\u0000\u0000)/\u0006\u0002\uffff\uffff\u0000*+\u0005\u0010"+
		"\u0000\u0000+,\u0005\u0015\u0000\u0000,.\u0006\u0002\uffff\uffff\u0000"+
		"-*\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u000002\u0001\u0000\u0000\u00001/\u0001\u0000"+
		"\u0000\u000023\u0005\r\u0000\u00003\u0005\u0001\u0000\u0000\u000045\u0005"+
		"\u0003\u0000\u000059\u0006\u0003\uffff\uffff\u000067\u0005\u0004\u0000"+
		"\u000079\u0006\u0003\uffff\uffff\u000084\u0001\u0000\u0000\u000086\u0001"+
		"\u0000\u0000\u00009\u0007\u0001\u0000\u0000\u0000:<\u0006\u0004\uffff"+
		"\uffff\u0000;=\u0003\n\u0005\u0000<;\u0001\u0000\u0000\u0000=>\u0001\u0000"+
		"\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?\t\u0001"+
		"\u0000\u0000\u0000@G\u0003\f\u0006\u0000AG\u0003\u000e\u0007\u0000BG\u0003"+
		"\u0010\b\u0000CG\u0003\u0012\t\u0000DG\u0003\u0014\n\u0000EG\u0003\u0016"+
		"\u000b\u0000F@\u0001\u0000\u0000\u0000FA\u0001\u0000\u0000\u0000FB\u0001"+
		"\u0000\u0000\u0000FC\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000"+
		"FE\u0001\u0000\u0000\u0000G\u000b\u0001\u0000\u0000\u0000HI\u0005\u0005"+
		"\u0000\u0000IJ\u0005\u000b\u0000\u0000JK\u0005\u0015\u0000\u0000KL\u0006"+
		"\u0006\uffff\uffff\u0000LM\u0005\f\u0000\u0000MN\u0005\r\u0000\u0000N"+
		"O\u0006\u0006\uffff\uffff\u0000O\r\u0001\u0000\u0000\u0000PQ\u0005\u0006"+
		"\u0000\u0000QR\u0005\u000b\u0000\u0000RS\u0005\u0015\u0000\u0000ST\u0006"+
		"\u0007\uffff\uffff\u0000TU\u0005\f\u0000\u0000UV\u0005\r\u0000\u0000V"+
		"W\u0006\u0007\uffff\uffff\u0000W\u000f\u0001\u0000\u0000\u0000XY\u0005"+
		"\u0015\u0000\u0000YZ\u0006\b\uffff\uffff\u0000Z[\u0005\u000f\u0000\u0000"+
		"[\\\u0006\b\uffff\uffff\u0000\\]\u0003\u0018\f\u0000]^\u0005\r\u0000\u0000"+
		"^_\u0006\b\uffff\uffff\u0000_\u0011\u0001\u0000\u0000\u0000`a\u0005\u0007"+
		"\u0000\u0000ab\u0005\u000b\u0000\u0000bc\u0007\u0000\u0000\u0000cd\u0006"+
		"\t\uffff\uffff\u0000de\u0005\u0014\u0000\u0000ef\u0006\t\uffff\uffff\u0000"+
		"fg\u0007\u0000\u0000\u0000gh\u0006\t\uffff\uffff\u0000hi\u0001\u0000\u0000"+
		"\u0000ij\u0005\f\u0000\u0000jk\u0005\u0012\u0000\u0000km\u0006\t\uffff"+
		"\uffff\u0000ln\u0003\n\u0005\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000"+
		"\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0001"+
		"\u0000\u0000\u0000qr\u0005\u0013\u0000\u0000r~\u0006\t\uffff\uffff\u0000"+
		"st\u0005\b\u0000\u0000tu\u0005\u0012\u0000\u0000uw\u0006\t\uffff\uffff"+
		"\u0000vx\u0003\n\u0005\u0000wv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{|\u0005\u0013\u0000\u0000|}\u0006\t\uffff\uffff\u0000}\u007f"+
		"\u0001\u0000\u0000\u0000~s\u0001\u0000\u0000\u0000~\u007f\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0006\t\uffff"+
		"\uffff\u0000\u0081\u0013\u0001\u0000\u0000\u0000\u0082\u0083\u0005\t\u0000"+
		"\u0000\u0083\u0084\u0005\u000b\u0000\u0000\u0084\u0085\u0007\u0000\u0000"+
		"\u0000\u0085\u0086\u0006\n\uffff\uffff\u0000\u0086\u0087\u0005\u0014\u0000"+
		"\u0000\u0087\u0088\u0006\n\uffff\uffff\u0000\u0088\u0089\u0007\u0000\u0000"+
		"\u0000\u0089\u008a\u0006\n\uffff\uffff\u0000\u008a\u008b\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0005\f\u0000\u0000\u008c\u008d\u0005\u0012\u0000\u0000"+
		"\u008d\u008f\u0006\n\uffff\uffff\u0000\u008e\u0090\u0003\n\u0005\u0000"+
		"\u008f\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0005\u0013\u0000\u0000"+
		"\u0094\u0095\u0006\n\uffff\uffff\u0000\u0095\u0015\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0005\n\u0000\u0000\u0097\u0098\u0005\u0012\u0000\u0000\u0098"+
		"\u009a\u0006\u000b\uffff\uffff\u0000\u0099\u009b\u0003\n\u0005\u0000\u009a"+
		"\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0013\u0000\u0000\u009f"+
		"\u00a0\u0005\t\u0000\u0000\u00a0\u00a1\u0005\u000b\u0000\u0000\u00a1\u00a2"+
		"\u0007\u0000\u0000\u0000\u00a2\u00a3\u0006\u000b\uffff\uffff\u0000\u00a3"+
		"\u00a4\u0005\u0014\u0000\u0000\u00a4\u00a5\u0006\u000b\uffff\uffff\u0000"+
		"\u00a5\u00a6\u0007\u0000\u0000\u0000\u00a6\u00a7\u0006\u000b\uffff\uffff"+
		"\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\f\u0000\u0000"+
		"\u00a9\u00aa\u0005\r\u0000\u0000\u00aa\u00ab\u0006\u000b\uffff\uffff\u0000"+
		"\u00ab\u0017\u0001\u0000\u0000\u0000\u00ac\u00b2\u0003\u001a\r\u0000\u00ad"+
		"\u00ae\u0005\u000e\u0000\u0000\u00ae\u00af\u0006\f\uffff\uffff\u0000\u00af"+
		"\u00b1\u0003\u001a\r\u0000\u00b0\u00ad\u0001\u0000\u0000\u0000\u00b1\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b3\u0019\u0001\u0000\u0000\u0000\u00b4\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005\u0015\u0000\u0000\u00b6\u00bc"+
		"\u0006\r\uffff\uffff\u0000\u00b7\u00b8\u0005\u0016\u0000\u0000\u00b8\u00bc"+
		"\u0006\r\uffff\uffff\u0000\u00b9\u00ba\u0005\u0017\u0000\u0000\u00ba\u00bc"+
		"\u0006\r\uffff\uffff\u0000\u00bb\u00b5\u0001\u0000\u0000\u0000\u00bb\u00b7"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u001b"+
		"\u0001\u0000\u0000\u0000\f%/8>Foy~\u0091\u009c\u00b2\u00bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}