package datastructures;

public class IsiVariable extends IsiSymbol{
	public static final int NUMBER = 0;
	public static final int TEXT   = 1;
	public static final int BOOLEAN = 2;
	
	private int type;
	private String value;
	private int countAtt;
	private int countUse;
	
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.countAtt = 0;
		this.countUse = 0;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		this.countUse++;
		return value;
	}

	public void setValue(String value) {
		this.countAtt++;
		this.value = value;
	}
	
	public int getCountAtt() {
		return countAtt;
	}
	
	public int getCountUse() {
		return countUse;
	}
	
	 @Override
	 public String toString() {
		 return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	 }
	 
	 @Override
	 public String generateJavaCode() {
		 String str;
		 if (type == NUMBER) {
			 str = "double ";
		 }
		 else if (type == BOOLEAN ){
			 str = "boolean ";
		 }
		 else {
			 str = "String ";
		 }
		 return str + " " + super.name + ";";
	 } 
}
