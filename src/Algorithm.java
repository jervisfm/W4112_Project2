import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * @author Jervis
 *
 * Contains the Algorithm 4.11 to Optimize Cost of Branch
 * Mis-prediction From the Paper 'Selection conditions in main memory'
 * By Kenneth Ross.
 */
public class Algorithm {


	public ArrayList<PlanRecord> plans;
	public static final boolean DEBUG = false;
	private LogicalAndTerm terms;

	public Algorithm(LogicalAndTerm terms) {
		if (terms == null)
			throw new IllegalArgumentException("terms cannot be null");
		if (terms.isEmpty())
			throw new IllegalArgumentException("terms cannot be empty");

		this.terms = terms;
		this.plans = new ArrayList<PlanRecord>();
	}

	
	private void d(String msg) {
		if (DEBUG)
			System.out.print(msg);
	}
	
	private void dln(String msg) {
		if (DEBUG)
			System.out.println(msg);
	}
	
	public PlanRecord findOptimialPlan(CostModel cm) {

		if (cm == null)
			throw new IllegalArgumentException("Cost Model cannot be null");


		generateAllPlans(terms);

		for(PlanRecord p1 : plans) {
			LinkedHashSet<BasicTerm> set1 = Util.convertToSet(p1.subset);
			for(PlanRecord p2 : plans) {
				LinkedHashSet<BasicTerm> set2 = Util.convertToSet(p2.subset);
				
				// debug, delete me later
				d("s1 = ");
				d(Util.printSubset(set1, false)); 
				d("s2 = ");
				d(Util.printSubset(set2, false)); 
				d(" my common size  = " + Util.getCommonElementsSize(set1, set2));
				d(" ## ");
								 
				d(String.valueOf(p1.subset.getSubsetNo())); 
				d(" | ");
				d(String.valueOf(p2.subset.getSubsetNo()));				
								
				if (!Util.isDisjointSets(set1, set2)) {
					dln(". Debug: Skipping Common sets");					
					continue;
				}
				dln("");
				
				BranchingAndPlan p = makeBranchingAndPlan(p1, p2);

				/**
				 * TODO(jervis): double check again to make sure I do the
				 * checks correctly for both metrics. (esp. wrt to condition
				 * "leftmost &-term in set2 (aka 's' in paper)")
				 */
				Pair s1CMetric = p1.subset.getCMetric(cm);
				LogicalAndTerm lat = p2.getLeftMostLogicalAndTerm(plans);
				Pair s2CMetric = lat.getCMetric(cm);

				
				Pair s1DMetric = p1.subset.getDMetric(cm);
				Pair s2DMetric = p2.subset.getDMetric(cm);
				if (false && /* TODO: enable this check later */ 
					s2CMetric.x < s1CMetric.x && s2CMetric.y <= s1CMetric.y) {
					continue;
				} else if ( false && /* TODO: correct this condition */
							p1.p <= 0.5 &&
						   s2DMetric.y < s1DMetric.y &&
						   s2DMetric.x < s1DMetric.x) {
					continue;
				} else {
					double combinedCost = Util.planCost(p,cm);
					LogicalAndTerm union = Util.getUnionTerm(p1.subset,
															 p2.subset);
					int idx = getIndexOfSubset(plans, union);
					if (combinedCost < plans.get(idx).c) {
						PlanRecord ans = plans.get(idx);
						ans.c = combinedCost;
						ans.left = p1.subset.getSubsetNo();
						ans.right = p2.subset.getSubsetNo();
					}
				}
			}
		}
		int lastIdx = plans.size() - 1;
		return plans.get(lastIdx);
	}

	
	
	private BranchingAndPlan makeBranchingAndPlan(PlanRecord left,
												  PlanRecord right) {
		
		// TODO:(jervis): double-check that expression below is OK.
		BranchingAndPlan p = new BranchingAndPlan(left.plan, right.plan, null);
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
			left = right = -1;
			int n = subset.size();
			double p = subset.getSelectivity();			
			boolean doNoBranch = false;
			double c = subset.getCost(CostModel.getDefaultCostModel());
			if (subset.getNoBranchAlgCost() < c) {
				c = subset.getNoBranchAlgCost();
				doNoBranch = true;
			}
			Plan plan = new LogicalAndPlan(null, null, subset.getTerms());
			PlanRecord record = new PlanRecord(n,p,doNoBranch,c,plan,left,right, subset);
			plans.add(record);
		}
		return plans;
	}
	
	public static int getIndexOfSubset(ArrayList<PlanRecord> plans, 
									   LogicalAndTerm term) {
		
		int i = 0; 
		for (PlanRecord p : plans) {
			LogicalAndTerm curr = new LogicalAndTerm(p.plan.terms);
			if (term.size() == curr.size()) {
				boolean allMatch = true;
				
				for (BasicTerm t : term.getTerms()) {
					if (!Util.contains(curr.getTerms(), t)) {
							allMatch = false;
							break;
					}
				}
				if (allMatch) {
					return i;
				}
			}
			++i;
		}
		return 0; 
	}

}
