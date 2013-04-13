import java.util.ArrayList;


/**
 * This represents a plan where by the basic terms
 * /selection conditions are being bitwise "Anded" together.
 * We use the name "LogicalAnd" to remain consistent with the convention
 * used in 'Selection in Main Memory Conditions' Paper by Ken Ross.  
 * @author Jervis Muindi
 *
 */
public class LogicalAndPlan extends Plan {
	
	public LogicalAndPlan() {
		super(); 
	}

	public LogicalAndPlan(Plan left, Plan right, ArrayList<BasicTerm> terms) {
		this.left = left;
		this.right = right; 
		this.terms = terms;  
	}
	
	
}
