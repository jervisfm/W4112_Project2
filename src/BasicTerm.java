
/**
 * Represents a single function condition.
 * @author Jervis
 */
public class BasicTerm implements Cloneable {
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
	
	public boolean equals(BasicTerm other) {		
		return this.function.equals(other.function) && 
			   this.argument.equals(other.argument);				
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof BasicTerm) {
			BasicTerm other = (BasicTerm) o; 
			return  this.equals(other); 
			
		} else {
			return super.equals(o);	
		}
		
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new BasicTerm(this.function, this.argument, this.selectivity);
	}
	
}
