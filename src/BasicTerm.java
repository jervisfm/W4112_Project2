
/**
 * Represents a single function condition. 
 * @author Jervis
 */
public class BasicTerm  {
	public String function; 
	public String argument; 
	public double selectivity; 
	public BasicTerm(String function, String argument, double selectivity) {
		this.function = function;
		this.argument = argument;
		this.selectivity = selectivity; 
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(function); 
		sb.append("(" + argument + ")");
		sb.append("@" + selectivity); 
		return sb.toString();
	}
}
