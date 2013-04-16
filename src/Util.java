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
