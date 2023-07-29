package ast;

public class CommandAttrib extends AbstractCommand{
	
	private String id;
	private String expr;
	
	public CommandAttrib(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return id + " =" + expr + ";";
	}
	
	@Override
	public String toString() {
		return "CommandAttrib [id=" + id + ", expr=" + expr + "]";
	}
	
}
