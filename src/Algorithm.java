import java.util.ArrayList;


public class Algorithm {

	
	public ArrayList<PlanRecord> plans;
	private LogicalAndTerm terms; 
	
	public Algorithm(LogicalAndTerm terms) {
		if (terms == null)
			throw new IllegalArgumentException("terms cannot be null");
		if (terms.isEmpty()) 
			throw new IllegalArgumentException("terms cannot be empty");
		
		this.terms = terms;
		this.plans = new ArrayList<PlanRecord>();
	}
	
	public void findOptimialPlan() {
		generateAllPlans(terms);
		
		
		
	}
	
	public ArrayList<PlanRecord> generateAllPlans(LogicalAndTerm terms) {
		plans = createPlanRecordsArray(terms);
		return plans; 
	}

	/**
	 * Creates an array of 2^k possible plan records from the given terms. 
	 * This corresponds to the 'Array A' in the 'Selection Conditions in Main
	 * Memory' paper by Ken Ross. 
	 * @param terms
	 */
	public static ArrayList<PlanRecord> createPlanRecordsArray(LogicalAndTerm
																terms) {
		ArrayList<LogicalAndTerm> subsets = Util.getAllSubsets(terms);
		subsets = Util.removeEmptySubset(subsets);
		Util.numberSubsets(subsets);
		ArrayList<PlanRecord> plans = new ArrayList<PlanRecord>(); 
		for (LogicalAndTerm subset : subsets) {
			long left, right; 
			left = right = 0; 
			int n = subset.size(); 
			double p = subset.getSelectivity(); 
			boolean b = false; 
			double c = subset.getCost(CostModel.getDefaultCostModel());
			if (subset.getNoBranchAlgCost() < c) {
				c = subset.getNoBranchAlgCost(); 
				b = true;
			}
			Plan plan = new LogicalAndPlan(null, null, subset.getTerms());
			PlanRecord record = new PlanRecord(n,p,b,c,plan,left,right); 
			plans.add(record);
		}
		return plans;
	}
}
