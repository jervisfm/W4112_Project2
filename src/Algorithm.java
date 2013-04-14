import java.util.ArrayList;
import java.util.LinkedHashSet;


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
		
		for(PlanRecord p1 : plans) {
			LinkedHashSet<BasicTerm> set1 = Util.convertToSet(p1.subset);
			for(PlanRecord p2 : plans) {
				LinkedHashSet<BasicTerm> set2 = Util.convertToSet(p2.subset);
				set1.retainAll(set2); 
				if (set1.size() > 1) {
					System.out.println("Debug: Skipping Common set: "+ set1);
					continue; 
				}
				
				Plan p = makeBranchingAndPlanFromSubsets(p1.subset, p2.subset);
				
			}
		
		}
		
	}

	private Plan makeBranchingAndPlanFromSubsets(LogicalAndTerm leftSubset, 
												  LogicalAndTerm rightSubset) {
		Plan left = new LogicalAndPlan(null, null, leftSubset.getTerms());
		// TODO:(jervis): double-check that expression below is OK. 
		Plan right = new LogicalAndPlan(null,null, rightSubset.getTerms());
		Plan p = new BranchingAndPlan(left, right, null);
		return p; 
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
			PlanRecord record = new PlanRecord(n,p,b,c,plan,left,right, subset); 
			plans.add(record);
		}
		return plans;
	}
	
	public static void isDisjoint(PlanRecord p1, PlanRecord p2) {
		
	}
	
}
