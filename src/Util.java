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
		double rightTermCost = rightTerm.getCost(cm); 
		
		return leftTermFixedCost + m * q + selectivities * rightTermCost;
	}


}
