package gpda.compiler.lexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import gpda.compiler.exceptions.GpdaLexicalException;

public class GpdaScanner {
	
	private char[] 	content;
	private int 	estado;
	private int 	pos;
	private int 	line;
	private int 	column;
	Token 			token;
	
	public GpdaScanner(String filename) {
		try {
			line = 1;
			column = 0;
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
			content = txtConteudo.toCharArray();
			pos = 0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Token nextToken() {
		char currentChar;
		String term="";
		if (isEOF() ) {
			return null;
		}
		estado = 0;
		while (true) {
			currentChar = nextChar();
			column++;
			
			switch(estado) {
			case 0:
				if (isChar(currentChar)) {
					term  += currentChar;
					estado = 1;
				} else if (isDigit(currentChar)) {
					term  += currentChar;
					estado = 3;
				} else if (isSpace(currentChar)) {
					estado = 0;
				} else if (isOperator(currentChar)) {
					term += currentChar;
					token = new Token();
					token.setType(Token.TK_OPERATOR);
					token.setText(term);
					token.setLine(line);
					token.setColumn(column - term.length());
					return token;
				} else {
					throw new GpdaLexicalException("Unrecognized SYMBOL");
				}
				break;
			case 1: 
				if (isChar(currentChar) || isDigit(currentChar)) {
					estado = 1;
					term += currentChar;
				} else {
					estado = 2;
				}
				break;
			case 2:
				if (!isEOF(currentChar)) {
					back();
				}
				token = new Token();
				token.setType(Token.TK_IDENT);
				token.setText(term);
				token.setLine(line);
				token.setColumn(column - term.length());
				return token;
			case 3:
				if (isDigit(currentChar)) {
					term  += currentChar;
					estado = 3;
				} else if (!isChar(currentChar)) {
					estado = 4;
				} else {
					throw new GpdaLexicalException("Unrecognized Number");
				}
				break;
			case 4:
				token = new Token();
				token.setType(Token.TK_NUMBER);
				token.setText(term);
				token.setLine(line);
				token.setColumn(column - term.length());
				if (!isEOF(currentChar)) {
					back();						
				}
				return token;
			}
		}
	}
	
	private boolean isDigit(char c) {
		return c>= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c>= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' ||
			   c == '>' || c == '<' || c == '=';
	}
	
	private boolean isSpace(char c) {
		if (c == '\n' || c == '\r') {
			line++;
			column = 0;
		}
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	private char nextChar() {
		if (isEOF()) {
			return '\0';
		}
		
		return content[pos++];
	}
	
	private boolean isEOF() {
		return pos == content.length;
	}
	
	private boolean isEOF(char c) {
		return c == '\0';
	}
	
	private void back() {
		pos--;
	}
}