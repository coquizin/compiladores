package gpda.compiler.lexico;

public class Token {
	public static final int TK_IDENT = 0;
	public static final int TK_NUMBER = 1;
	public static final int TK_OPERATOR = 2;
	public static final int TK_PONCTUATION = 3;
	public static final int TK_ATR = 4;
	
	public static final String TK_TEXT[] = {
			"IDENTIFIER", "NUMBER", "OPERATOR", "PONCTUACTION", "ASSIGMENT"
	};
	
	private int 	type;
	private String 	text;
	private int 	line;
	private int 	column;
	
	
	public Token(int type, String text) {
		super();
		this.type = type;
		this.text = text;
	}
	
	public Token() {
		super();
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "Token [type=" + type + ", text=" + text + "]";
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
}