package gpda.compiler.main;

import gpda.compiler.exceptions.GpdaLexicalException;
import gpda.compiler.exceptions.GpdaSyntaxException;
import gpda.compiler.lexico.GpdaScanner;
import gpda.compiler.lexico.Token;
import gpda.compiler.parser.GpdaParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			GpdaScanner sc = new GpdaScanner("input.gpda");
			GpdaParser 	pa = new GpdaParser(sc);
			
			pa.E();
			
			System.out.println("Success");
		} catch (GpdaLexicalException ex) {
			System.out.println("Lexical ERROR "+ex.getMessage());
		} catch (GpdaSyntaxException ex) {
			System.out.println("Syntax ERROR "+ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Genertic Error");
			System.out.println(ex.getClass().getName());
		}
	}
}