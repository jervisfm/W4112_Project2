import java.util.ArrayList;
import java.util.BitSet;

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
	 * Creates an array of 2^k possible plan records from the given terms. 
	 * This corresponds to the 'Array A' in the 'Selection Conditions in Main
	 * Memory' paper by Ken Ross. 
	 * @param terms
	 */
	public static void createPlanRecordsArray(LogicalAndTerm terms) {
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
			
			PlanRecord record = new PlanRecord(n,p,b,c,left,right); 
		}
		
		
	}
}
