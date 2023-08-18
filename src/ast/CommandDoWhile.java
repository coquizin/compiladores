package ast;

import java.util.ArrayList;

public class CommandDoWhile extends AbstractCommand{
	private String condition;
	private ArrayList<AbstractCommand> doWhileList;
	
	public CommandDoWhile(String condition, ArrayList<AbstractCommand> doWhileList) {
		this.condition = condition;
		this.doWhileList = doWhileList;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("do{\n");
		doWhileList.forEach(command -> stringBuilder.append(command.generateJavaCode() + "\n"));
        stringBuilder.append("} while (" + condition + ");\n");

        return stringBuilder.toString();
	}
	
	@Override
	public String toString() {
		return "CommandDoWhile [condition = " + condition + "], comandos=" + doWhileList + "]";
	}
 }
