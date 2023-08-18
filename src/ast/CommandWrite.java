package ast;

public class CommandWrite extends AbstractCommand{
	
	private String id;
	
	public CommandWrite(String id) {
		this.id = id;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "System.out.println(" + id + ");";
	}
	
	@Override
	public String toString() {
		return "CommandWrite [id = " + id + "]";
	}
	
}
