package ast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandAttrib extends AbstractCommand{
	private String id;
	private String expr;
	private final String regexIdentifier = "[a-z]([a-z]|[A-Z]|[0-9])*";
    private final String regexNumber = "[0-9]+(\\.[0-9]+)?";
    private final String idOrNumber = regexIdentifier + "|" + regexNumber;
	
	public CommandAttrib(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	
	private String substituiPotencia(String exp) {
        final String regex = "(?<base>"+ idOrNumber +")\\*\\*(?<potencia>"+ idOrNumber +")";
        final String subst = "Math.pow(${base},${potencia})";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(exp);
        final String modifiedExpression = matcher.replaceAll(subst);
        return modifiedExpression;
    }

    private String substituiRaiz(String exp) {
        final String regex = "raiz(?<radicando>[0-9]+(\\.[0-9]+)*)";
        final String subst = "Math.sqrt(${radicando})";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(exp);
        final String modifiedExpression = matcher.replaceAll(subst);
        return modifiedExpression;
    }

    private String substituiLog(String exp) {
        final String regex = "log(?<logaritmando>[0-9]+(\\.[0-9]+)*)";
        final String subst = "Math.log(${logaritmando})";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(exp);
        final String modifiedExpression = matcher.replaceAll(subst);
        return modifiedExpression;
    }
	
	@Override
	public String generateJavaCode() {
		expr = substituiPotencia(expr);
		expr = substituiRaiz(expr);
		expr = substituiLog(expr);
		return id + " =" + expr + ";";
	}
	
	@Override
	public String toString() {
		return "CommandAttrib [id=" + id + ", expr=" + expr + "]";
	}
	
}
