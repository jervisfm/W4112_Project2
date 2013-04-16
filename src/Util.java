import java.util.ArrayList;
import java.util.BitSet;
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
	public static ArrayList 
				getAllSubsets(LogicalAndTerm terms) {
		int setSize = terms.size();  
		long maxLimit = 1  result = new ArrayList(); 
		for (long i = 0; i  removeEmptySubset(
											ArrayList subsets) {
		ArrayList result = new ArrayList();
		for(LogicalAndTerm subset : subsets) {
			if (!subset.isEmpty())
				result.add(subset);
		}
		return result; 
	}
	
	public static LinkedHashSet convertToSet(LogicalAndTerm terms) {
		
		LinkedHashSet result = new LinkedHashSet();
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
	public static ArrayList getBasicTerms(
			ArrayList queries) {
		int count = 1;
		ArrayList result = new ArrayList();
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
	
	public static void numberSubsets(ArrayList subsets) {
		int count = 0;
		for(LogicalAndTerm subset: subsets) {
			subset.setSubsetNo(count);
			++count;
		}
	}
	
	
	public static double computeNoBranchCost(ArrayList terms,
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

	/**
	 * Compute the cost for the combined branching And plan. 
	 * @param p
	 * @return
	 */
	public static double planCost(BranchingAndPlan p) {
		// TODO(jervis): Implement this. 
		return 0;
	}
	
	
}
