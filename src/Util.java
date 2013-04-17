import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Contains useful utility methods
 * @author Jervis
 *
 */
public class Util {

	/**
	 * Returns all 2^k subsets of the given terms, including the
	 * empty subset.
	 * @param terms
	 * @return
	 * @throws Exception if the # of terms > 63
	 */
	public static ArrayList<LogicalAndTerm>
				getAllSubsets(LogicalAndTerm terms) {
		int setSize = terms.size();
		long maxLimit = 1 << setSize;
		BitVector bv = new BitVector(setSize);
		ArrayList<LogicalAndTerm> result = new ArrayList<LogicalAndTerm>();
		for (long i = 0; i < maxLimit; ++i, bv.increment()) {
			BitSet bs = bv.getBitsSet();
			int bitVectorSize = bv.getSetSize();
			LogicalAndTerm subset = new LogicalAndTerm();
			for (int j = 0; j < bitVectorSize; ++j) {
				if(bs.get(j)) {
					subset.add(terms.get(j));
				}
			}
			result.add(subset);
		}
		return result;
	}

	/**
	 * Returns the given set but without any empty subsets.
	 * Passed in set is left untouched.
	 * @param subsets
	 * @return
	 */
	public static ArrayList<LogicalAndTerm> removeEmptySubset(
											ArrayList<LogicalAndTerm> subsets) {
		ArrayList<LogicalAndTerm> result = new ArrayList<LogicalAndTerm>();
		for(LogicalAndTerm subset : subsets) {
			if (!subset.isEmpty())
				result.add(subset);
		}
		return result;
	}

	public static LinkedHashSet<BasicTerm> convertToSet(LogicalAndTerm terms) {

		LinkedHashSet<BasicTerm> result = new LinkedHashSet<BasicTerm>();
		for (BasicTerm t : terms.getTerms()) {
			result.add(t);
		}
		return result;
	}

	/**
	 * Gets a list of of selection queries/conditions returned in a
	 * format of LogicalAndTerms.
	 * @param queries - list of queries to process
	 * @return
	 */
	public static ArrayList<LogicalAndTerm> getBasicTerms(
			ArrayList<double[]> queries) {
		int count = 1;
		ArrayList<LogicalAndTerm> result = new ArrayList<LogicalAndTerm>();
		for (double[] query : queries) {
			LogicalAndTerm lat = new LogicalAndTerm();
			for (double selectivity : query) {
				String functionName = String.format("t%d", count);
				String arg = String.format("o%d[i]", count);
				BasicTerm term = new BasicTerm(functionName, arg, selectivity);
				lat.add(term);
				++count;
			}
			if (lat.size() > 0)
				result.add(lat);
		}
		return result;
	}

	public static void numberSubsets(ArrayList<LogicalAndTerm> subsets) {
		int count = 0;
		for(LogicalAndTerm subset: subsets) {
			subset.setSubsetNo(count);
			++count;
		}
	}

	/**
	 * Gets the number of terms that are in common between
	 * the two sets. 
	 * @param s1 - set 1
	 * @param s2 - set 2
	 * @return
	 */
	public static int getCommonElementsSize(LinkedHashSet<BasicTerm> s1, 
											LinkedHashSet<BasicTerm> s2) {
		int count = 0; 
		for (BasicTerm t1 : s1) {			
			for (BasicTerm t2: s2) {
				if (t1.equals(t2)) {
					++count;
					break;
				}
			}
		}
		return count;
	}
	
	/**
	 * Returns true if the two sets are disjoint and have no in common
	 * elements
	 * @param s1 - set 1
	 * @param s2 - set 2
	 * @return
	 */
	public static boolean isDisjointSets(LinkedHashSet<BasicTerm> s1,
									 LinkedHashSet<BasicTerm> s2) {
		return getCommonElementsSize(s1, s2) == 0; 
	}
	

	public static double computeNoBranchCost(ArrayList<BasicTerm> terms,
											 CostModel cm) {
		if (cm == null || terms == null)
			throw new NullPointerException("terms or cost model is null");
		if (terms.size() < 1)
			throw new IllegalArgumentException("Need at least 1 term");

		int k = terms.size();
		int r = cm.r;
		int l = cm.l;
		int f = cm.f;
		int a = cm.a;

		return k*r + (k-1)*l + f*k + a;
	}

	public static void printSubsets(ArrayList<LogicalAndTerm> subsets) {
		int count = 1;		
		for (LogicalAndTerm subset : subsets) {
			System.out.println("Subset No " + count);
			System.out.println("-------------------");
			System.out.println(subset);
			System.out.println("====================");
			++count;
		}
	}
	
	public static String printSubset(LinkedHashSet<BasicTerm> set, boolean doPrint) {
		StringBuffer sb = new StringBuffer(); 
		for(BasicTerm t: set) {
			sb.append(t.function + ", ");
		}
		sb.append(" ; ");
		if (doPrint) {
			System.out.println(sb);
		} 
		return sb.toString();
	}
	
	/**
	 * Unites the two given logical and terms into one term. 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static LogicalAndTerm getUnionTerm(LogicalAndTerm t1, 
											  LogicalAndTerm t2) {
		LogicalAndTerm result = new LogicalAndTerm(); 
		for(BasicTerm t : t1.getTerms()) {
			result.add(t);
		}
		for (BasicTerm t: t2.getTerms()) {
			result.add(t);
		}
		return result; 
	}
	
	public static boolean isLogicalAndTerm(PlanRecord p) {
		if (p == null)
			throw new IllegalArgumentException("plan cannot be null");
		
		return p.left <0 && p.right < 0; 
	}
	
	/**
	 * Tests if the plan record is a real Branching And Term. 
	 * i.e. it contains two children two it's is ANDing together.  
	 * @param p
	 * @return
	 */
	public static boolean isBranchAndTerm(PlanRecord p) {
		if (p == null)
			throw new IllegalArgumentException("plan cannot be null");
		
		return p.left >=0 && p.right >= 0; 
	}
	
	/**
	 * Check if this branch And Term is the lowest, 
	 * i.e. its children are NOT Other BranchAndTerms
	 * but rather just regular logical and term
	 * @param p
	 * @return
	 */
	public static boolean isLowestBranchAndTerm(PlanRecord p, ArrayList<PlanRecord> plans) {
		if (p == null)
			throw new IllegalArgumentException("plan cannot be null");
		
		if (p.left >= 0) {
			PlanRecord leftSubPlan = plans.get((int)p.left); 
			if (isLogicalAndTerm(leftSubPlan)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static double getPlanCost(PlanRecord p, 
									ArrayList<PlanRecord> plans, CostModel cm) {
		
		if ( p == null)
			return 0; 
		
		// Case 1: Logical And Plan only
		if  (isLogicalAndTerm(p)) {
			return p.subset.getCost(cm);
		}
		
		// Case 2: We have Branching And Plan made up 
		// from the logical and terms. 
		if (isLowestBranchAndTerm(p, plans)) {
			PlanRecord leftPlan = plans.get((int)p.left);			 
			double fixedCost = leftPlan.subset.getFixedCost(cm);
			double selectivity = leftPlan.p;
			double m = cm.m;
			double q = Math.min(selectivity, 1 - selectivity); 
			
			if (p.right < 0) { // error case : should never happen
				throw new Error("WARNING: Really wierd case in getPlanCost" +
							    "The RHS child is not defined for And-Plan");
			} else {
				PlanRecord rPlan = plans.get((int) p.right); 			
				return fixedCost + m*q + selectivity * getPlanCost(rPlan, plans, cm);
			}
			
		} else {
			PlanRecord lPlan = plans.get((int)p.left);
			PlanRecord rPlan = plans.get((int)p.right);
			
			// Sanity check
			if (p.left < 0 || p.right < 0) {
				System.out.println("ERROR: Unexpected Null child in And-Plan" +
								   "when computing PlanCost" );
			}
			
			return getPlanCost(lPlan, plans, cm) + getPlanCost(rPlan, plans, cm);
		}
		
		
		
	}
	
	public static double getAndPlanCost(PlanRecord p1, PlanRecord p2,
										ArrayList<PlanRecord> plans,CostModel cm) {
		
		if (p1 == null || p2 == null || plans == null || cm == null)
			throw new IllegalArgumentException("Cannot have null args");
	}
	
	/**
	 * Compute the cost for the combined branching And plan.
	 * @param p
	 * @return
	 */
	public static double planCost(BranchingAndPlan p, CostModel cm) {
		
		LogicalAndTerm leftTerm = new LogicalAndTerm(p.left.terms);
		LogicalAndTerm rightTerm = new LogicalAndTerm(p.right.terms); 
		
		double leftTermFixedCost =leftTerm.getFixedCost(cm);
		int m = cm.m;
		double selectivities = leftTerm.getSelectivity(); 
		double q = Math.min(selectivities , 1 - selectivities);
		double rightTermCost = Util.planCost(p.right, cm);  
		
		return leftTermFixedCost + m * q + selectivities * rightTermCost;
	}

	public static boolean contains(Collection<?> c, Object item) {		
		for (Object obj : c) {
			if (obj.equals(item))
				return true;
		}
		return false;
	}
	
	private static double planCost(Plan p, CostModel cm) {
		if (cm == null)
			throw new IllegalArgumentException("cost model cannot be null");
		
		if (p == null)
			return 0; 
		
		if (p.left == null && p.right == null) {
			if (p.terms != null) {
				LogicalAndTerm lat = new LogicalAndTerm(p.terms);				
				return lat.getCost(cm);
			} else {
				System.out.println("Warn: wierd case in PlanCost");
				return 0; 
			}
		}
			
		
		if (p.left instanceof LogicalAndPlan) {
			LogicalAndTerm left  = new LogicalAndTerm(p.left.terms); 
			double fixedCost = left.getFixedCost(cm); 
			double selectivity = left.getSelectivity();
			double q = Math.min(selectivity, 1 - selectivity);
			return fixedCost + cm.m * q + planCost(p.right, cm);
		} else {
			return planCost(p.left, cm) + planCost(p.right, cm);
		}
	}
}
