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
		// TODO Auto-generated method stub
		return id + " = _key." + (var.getType() == IsiVariable.NUMBER? "nextDouble();" : "nextLine();");
	}
	
	@Override
	public String toString() {
		return "CommandRead [id=" + id + "]";
	}
	
}
