
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
	public int left;
	
	/**
	 * Index of right child subplan (in overall set) giving the best cost
	 */
	public int right; 
	
	public PlanRecord() {
		
	}
	
}
