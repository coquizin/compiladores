package gpda.compiler.main;

import gpda.compiler.lexico.GpdaScanner;
import gpda.compiler.lexico.Token;

public class MainClass {
	public static void main(String[] args) {
		GpdaScanner sc = new GpdaScanner("input.gpda");
		Token token = null;
		
		do {
			token = sc.nextToken();
			if (token != null) {
				System.out.println(token);
			}
		} while (token != null);
	}
}