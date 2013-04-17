import java.util.ArrayList;


public class PlanRecord {
	/**
	 * Number of basic terms in the matching subset
	 */
	public int n;
	/**
	 * Selectivites of all terms in the subset
	 */
	public double p;
	/**
	 * Indicates whether No-branch optimization used to get best cost.
	 */
	public boolean b = false;
	/**
	 * Best Cost C for the subset
	 */
	public double c;

	/**
	 * Index of left child subplan (in overall set) giving the best cost
	 */
	public long left;

	/**
	 * Index of right child subplan (in overall set) giving the best cost
	 */
	public long right;

	/**
	 * The plan for this.
	 */
	public Plan plan;

	/**
	 * Associated subset
	 */
	public LogicalAndTerm subset;

	/**
	 * Creates a Plan Record.
	 * @param n - Number of basic terms in matching subset
	 * @param p - Selectivities of all terms in the subset
	 * @param b - Indicates whether no branch optimization was used to
	 *		   compute best cost
	 * @param c - Best cost C for the subset
	 * @param left - Index # of left child subplan (in overall set)
	 *				  giving best cost
	 * @param right - Index # of right child subplan (overall set) giving
	 *				   best cost
	 */
	public PlanRecord(int n, double p, boolean b, double c,
					   Plan plan, long left, long right, LogicalAndTerm subset) {
		if (n < 1)
			throw new IllegalArgumentException("Cannot have empty subsets - "+
												 n);
		this.n = n;
		this.p = p;
		this.b = b;
		this.c = c;
		this.left = left;
		this.right = right;
		this.plan = plan;
		this.subset = subset;
	}
	
	
	
	public String toString() {
		
		StringBuffer s= new StringBuffer(); 
		for (BasicTerm t : this.subset.getTerms()) {
			s.append(t.function + ",");
		}
		
		return String.format("No of terms: %d \n" +
							 "Combined Selectivities:%f\n" +
							 "Uses No Branch? %b \n" +
							 "Best Cost: %f \n" +
							 "Left Subplan Index: %d \n" +
							 "Right Subplan Index: %d\n" +
							 "Terms: %s\n" +
							 "------------------------\n", 
							 n, p, b, c, left, right, s.toString()); 		
	}
	
	public LogicalAndTerm getLeftMostLogicalAndTerm(ArrayList<PlanRecord> plans) {
		PlanRecord c = this;
		while (c.left >= 0) {
			c = plans.get((int)c.left);
		}
		return c.subset; 
	}
}
