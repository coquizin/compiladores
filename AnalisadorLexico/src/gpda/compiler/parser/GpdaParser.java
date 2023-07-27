package gpda.compiler.parser;

import gpda.compiler.exceptions.GpdaSyntaxException;
import gpda.compiler.lexico.GpdaScanner;
import gpda.compiler.lexico.Token;

public class GpdaParser {
	
	private GpdaScanner scanner;
	private Token token;
	
	public GpdaParser(GpdaScanner scanner) {
		this.scanner = scanner;
	}
	
	public void E() {
		T();
		El();
	}
	
	public void El() {
		token = scanner.nextToken();
		if (token != null) {
			OP();
			T();
			El();
		}
	}
	
	public void T() {
		token = scanner.nextToken();
		if (token.getType() != Token.TK_IDENT && token.getType() != Token.TK_NUMBER) {
			throw new GpdaSyntaxException("ID or NUMBER Expected! found"+Token.TK_TEXT[token.getType()]+" ("+token.getText()+")");
		}
			
	}
	
	public void OP() {
		if (token.getType() != Token.TK_OPERATOR) {
			throw new GpdaSyntaxException("Operator Expected, found "+Token.TK_TEXT[token.getType()]+" ("+token.getText()+")");
		}
	}
}