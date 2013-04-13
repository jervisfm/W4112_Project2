
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
	 * Creates a Plan Record. 
	 * @param n - Number of basic terms in matching subset
	 * @param p - Selectivies of all terms in the subset
	 * @param b - Indicates whether no branch optimization was used to 
	 *   		   compute best cost
	 * @param c - Best cost C for the subset
	 * @param left - Index # of left child subplan (in overall set) 
	 * 				  giving best cost
	 * @param right - Index # of right child subplan (overall set) giving 
	 * 				   best cost
	 */
	public PlanRecord(int n, double p, boolean b, double c,
					   long left, long right) {
		this.n = n; 
		this.p = p; 
		this.b = b; 
		this.c = c; 
		this.left = left;
		this.right = right; 
	}
	
}
