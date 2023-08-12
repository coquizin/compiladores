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
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, AP=11, FP=12, SC=13, OP=14, ATTR=15, VIR=16, ASPAS=17, ACH=18, 
		FCH=19, OPREL=20, BOOLEAN=21, ID=22, NUMBER=23, TEXT=24, WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "AP", "FP", "SC", "OP", "ATTR", "VIR", "ASPAS", "ACH", "FCH", 
			"OPREL", "BOOLEAN", "ID", "NUMBER", "TEXT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'booleano'", 
			"'leia'", "'escreva'", "'se'", "'senao'", "'enquanto'", "'('", "')'", 
			"';'", null, "'='", "','", "'\"'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "AP", 
			"FP", "SC", "OP", "ATTR", "VIR", "ASPAS", "ACH", "FCH", "OPREL", "BOOLEAN", 
			"ID", "NUMBER", "TEXT", "WS"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u00c2\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0096\b\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u00a1\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0005\u0015\u00a5\b\u0015\n\u0015\f\u0015\u00a8\t\u0015\u0001\u0016\u0004"+
		"\u0016\u00ab\b\u0016\u000b\u0016\f\u0016\u00ac\u0001\u0016\u0001\u0016"+
		"\u0004\u0016\u00b1\b\u0016\u000b\u0016\f\u0016\u00b2\u0003\u0016\u00b5"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0004\u0017\u00b9\b\u0017\u000b\u0017"+
		"\f\u0017\u00ba\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0000\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0007\u0003\u0000"+
		"*+--//\u0002\u0000<<>>\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0004"+
		"\u0000  09AZaz\u0003\u0000\t\n\r\r  \u00cb\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00013\u0001\u0000\u0000\u0000\u0003<\u0001\u0000"+
		"\u0000\u0000\u0005E\u0001\u0000\u0000\u0000\u0007L\u0001\u0000\u0000\u0000"+
		"\tR\u0001\u0000\u0000\u0000\u000b[\u0001\u0000\u0000\u0000\r`\u0001\u0000"+
		"\u0000\u0000\u000fh\u0001\u0000\u0000\u0000\u0011k\u0001\u0000\u0000\u0000"+
		"\u0013q\u0001\u0000\u0000\u0000\u0015z\u0001\u0000\u0000\u0000\u0017|"+
		"\u0001\u0000\u0000\u0000\u0019~\u0001\u0000\u0000\u0000\u001b\u0080\u0001"+
		"\u0000\u0000\u0000\u001d\u0082\u0001\u0000\u0000\u0000\u001f\u0084\u0001"+
		"\u0000\u0000\u0000!\u0086\u0001\u0000\u0000\u0000#\u0088\u0001\u0000\u0000"+
		"\u0000%\u008a\u0001\u0000\u0000\u0000\'\u0095\u0001\u0000\u0000\u0000"+
		")\u00a0\u0001\u0000\u0000\u0000+\u00a2\u0001\u0000\u0000\u0000-\u00aa"+
		"\u0001\u0000\u0000\u0000/\u00b6\u0001\u0000\u0000\u00001\u00be\u0001\u0000"+
		"\u0000\u000034\u0005p\u0000\u000045\u0005r\u0000\u000056\u0005o\u0000"+
		"\u000067\u0005g\u0000\u000078\u0005r\u0000\u000089\u0005a\u0000\u0000"+
		"9:\u0005m\u0000\u0000:;\u0005a\u0000\u0000;\u0002\u0001\u0000\u0000\u0000"+
		"<=\u0005f\u0000\u0000=>\u0005i\u0000\u0000>?\u0005m\u0000\u0000?@\u0005"+
		"p\u0000\u0000@A\u0005r\u0000\u0000AB\u0005o\u0000\u0000BC\u0005g\u0000"+
		"\u0000CD\u0005;\u0000\u0000D\u0004\u0001\u0000\u0000\u0000EF\u0005n\u0000"+
		"\u0000FG\u0005u\u0000\u0000GH\u0005m\u0000\u0000HI\u0005e\u0000\u0000"+
		"IJ\u0005r\u0000\u0000JK\u0005o\u0000\u0000K\u0006\u0001\u0000\u0000\u0000"+
		"LM\u0005t\u0000\u0000MN\u0005e\u0000\u0000NO\u0005x\u0000\u0000OP\u0005"+
		"t\u0000\u0000PQ\u0005o\u0000\u0000Q\b\u0001\u0000\u0000\u0000RS\u0005"+
		"b\u0000\u0000ST\u0005o\u0000\u0000TU\u0005o\u0000\u0000UV\u0005l\u0000"+
		"\u0000VW\u0005e\u0000\u0000WX\u0005a\u0000\u0000XY\u0005n\u0000\u0000"+
		"YZ\u0005o\u0000\u0000Z\n\u0001\u0000\u0000\u0000[\\\u0005l\u0000\u0000"+
		"\\]\u0005e\u0000\u0000]^\u0005i\u0000\u0000^_\u0005a\u0000\u0000_\f\u0001"+
		"\u0000\u0000\u0000`a\u0005e\u0000\u0000ab\u0005s\u0000\u0000bc\u0005c"+
		"\u0000\u0000cd\u0005r\u0000\u0000de\u0005e\u0000\u0000ef\u0005v\u0000"+
		"\u0000fg\u0005a\u0000\u0000g\u000e\u0001\u0000\u0000\u0000hi\u0005s\u0000"+
		"\u0000ij\u0005e\u0000\u0000j\u0010\u0001\u0000\u0000\u0000kl\u0005s\u0000"+
		"\u0000lm\u0005e\u0000\u0000mn\u0005n\u0000\u0000no\u0005a\u0000\u0000"+
		"op\u0005o\u0000\u0000p\u0012\u0001\u0000\u0000\u0000qr\u0005e\u0000\u0000"+
		"rs\u0005n\u0000\u0000st\u0005q\u0000\u0000tu\u0005u\u0000\u0000uv\u0005"+
		"a\u0000\u0000vw\u0005n\u0000\u0000wx\u0005t\u0000\u0000xy\u0005o\u0000"+
		"\u0000y\u0014\u0001\u0000\u0000\u0000z{\u0005(\u0000\u0000{\u0016\u0001"+
		"\u0000\u0000\u0000|}\u0005)\u0000\u0000}\u0018\u0001\u0000\u0000\u0000"+
		"~\u007f\u0005;\u0000\u0000\u007f\u001a\u0001\u0000\u0000\u0000\u0080\u0081"+
		"\u0007\u0000\u0000\u0000\u0081\u001c\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0005=\u0000\u0000\u0083\u001e\u0001\u0000\u0000\u0000\u0084\u0085\u0005"+
		",\u0000\u0000\u0085 \u0001\u0000\u0000\u0000\u0086\u0087\u0005\"\u0000"+
		"\u0000\u0087\"\u0001\u0000\u0000\u0000\u0088\u0089\u0005{\u0000\u0000"+
		"\u0089$\u0001\u0000\u0000\u0000\u008a\u008b\u0005}\u0000\u0000\u008b&"+
		"\u0001\u0000\u0000\u0000\u008c\u0096\u0007\u0001\u0000\u0000\u008d\u008e"+
		"\u0005>\u0000\u0000\u008e\u0096\u0005=\u0000\u0000\u008f\u0090\u0005<"+
		"\u0000\u0000\u0090\u0096\u0005=\u0000\u0000\u0091\u0092\u0005=\u0000\u0000"+
		"\u0092\u0096\u0005=\u0000\u0000\u0093\u0094\u0005!\u0000\u0000\u0094\u0096"+
		"\u0005=\u0000\u0000\u0095\u008c\u0001\u0000\u0000\u0000\u0095\u008d\u0001"+
		"\u0000\u0000\u0000\u0095\u008f\u0001\u0000\u0000\u0000\u0095\u0091\u0001"+
		"\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096(\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005t\u0000\u0000\u0098\u0099\u0005r\u0000\u0000"+
		"\u0099\u009a\u0005u\u0000\u0000\u009a\u00a1\u0005e\u0000\u0000\u009b\u009c"+
		"\u0005f\u0000\u0000\u009c\u009d\u0005a\u0000\u0000\u009d\u009e\u0005l"+
		"\u0000\u0000\u009e\u009f\u0005s\u0000\u0000\u009f\u00a1\u0005e\u0000\u0000"+
		"\u00a0\u0097\u0001\u0000\u0000\u0000\u00a0\u009b\u0001\u0000\u0000\u0000"+
		"\u00a1*\u0001\u0000\u0000\u0000\u00a2\u00a6\u0007\u0002\u0000\u0000\u00a3"+
		"\u00a5\u0007\u0003\u0000\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a7,\u0001\u0000\u0000\u0000\u00a8\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a9\u00ab\u0007\u0004\u0000\u0000\u00aa\u00a9"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00aa"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00b4"+
		"\u0001\u0000\u0000\u0000\u00ae\u00b0\u0005.\u0000\u0000\u00af\u00b1\u0007"+
		"\u0004\u0000\u0000\u00b0\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00ae\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5.\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b8\u0003!\u0010\u0000\u00b7\u00b9\u0007\u0005\u0000"+
		"\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000"+
		"\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0003!\u0010\u0000"+
		"\u00bd0\u0001\u0000\u0000\u0000\u00be\u00bf\u0007\u0006\u0000\u0000\u00bf"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0006\u0018\u0000\u0000\u00c1"+
		"2\u0001\u0000\u0000\u0000\n\u0000\u0095\u00a0\u00a4\u00a6\u00ac\u00b2"+
		"\u00b4\u00b8\u00ba\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}