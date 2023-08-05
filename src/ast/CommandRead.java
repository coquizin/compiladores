package ast;

import datastructures.IsiVariable;

public class CommandRead extends AbstractCommand {
	
	private String id;
	private IsiVariable var;
	
	public CommandRead(String id, IsiVariable var) {
		this.id = id;
		this.var = var;
	}
	
	@Override
	public String generateJavaCode() {
		String scannerMethod;
		if(var.getType() == IsiVariable.NUMBER) {
			scannerMethod = "nextDouble()";
		}
		else if(var.getType() == IsiVariable.BOOLEAN) {
			scannerMethod = "nextBoolean()";
		}
		else {
			scannerMethod = "nextLine()";
		}
		return id + " = _key." + scannerMethod + ";"; 
	}
	
	@Override
	public String toString() {
		return "CommandRead [id=" + id + "]";
	}
	
}
