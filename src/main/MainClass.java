package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import exceptions.IsiSemanticException;
import parser.IsiLangLexer;
import parser.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			//Ler o arquivo "input.isi" que é a entrada para o Analisador Lexico
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			//Cria um fluxo de tokens para passaar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			//Cria o parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			System.out.println("Compilation Successful");
			
			parser.exibeComandos();
			
			parser.generateCode();
		}
		catch(IsiSemanticException ex) {
			System.err.println("Semantic error -" + ex.getMessage());
		}
		catch(Exception ex) {
			System.err.println("ERROR " + ex.getMessage());
		}
	}
}
