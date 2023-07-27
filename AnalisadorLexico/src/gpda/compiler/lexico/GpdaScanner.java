package gpda.compiler.lexico;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GpdaScanner {
	
	private char[] content;
	private int estado;
	private int pos;
	Token token;
	
	public GpdaScanner(String filename) {
		try {
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
			term += currentChar;
			
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
					estado = 5;
				} else {
					throw new RuntimeException("Unrecognized SYMBOL");
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
				back();
				token = new Token();
				token.setType(Token.TK_IDENT);
				token.setText(term);
				return token;
			case 3:
				if (isDigit(currentChar)) {
					term  += currentChar;
					estado = 3;
				} else if (!isChar(currentChar)) {
					estado = 4;
				} else {
					throw new RuntimeException("Unrecognized Number");
				}
				break;
			case 4:
				token = new Token();
				token.setType(Token.TK_NUMBER);
				token.setText(term);
				back();
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
		return c == '>' || c == '<' || c == '=' || c == '!';
	}
	
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	}
	
	private char nextChar() {
		return content[pos++];
	}
	
	private boolean isEOF() {
		return pos == content.length;
	}
	
	private void back() {
		pos--;
	}
}