import java.util.ArrayList;
import java.util.BitSet;

/**
 * Contains useful utility methods
 * @author Jervis
 *
 */
public class Util {

	public static void getAllSubsets(LogicalAndTerm terms) throws Exception {
		int setSize = terms.size();  
		long maxLimit = 1 << setSize;  
		BitVector bv = new BitVector(setSize);		
		ArrayList<LogicalAndTerm> result = new ArrayList<LogicalAndTerm>(); 
		for (long i = 0; i <= maxLimit; ++i) {
			bv.increment(); 
			BitSet bs = bv.getBitsSet(); 
			LogicalAndTerm subset = new LogicalAndTerm();
			for (int j = 0; j < bs.size(); ++j) {
				if(bs.get(j)) {
					subset.add(terms.get(j));
				}
			}
			result.add(subset);
		}
	}
}
