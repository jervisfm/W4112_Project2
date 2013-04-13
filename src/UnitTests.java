import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;


public class UnitTests {

	
	private LogicalAndTerm getSampleTerms() {
		double [] selectionConditions = {0.700000, 0.800000, 
										  0.800000, 0.900000};
		ArrayList<double[]> queries = new ArrayList<double[]>();
		queries.add(selectionConditions); 
		return Util.getBasicTerms(queries).get(0); 						
	}
	
	@Test
	public void testGetAllSubSets() throws Exception {
		LogicalAndTerm lat = getSampleTerms();
		int setSize = lat.size(); 
		ArrayList<LogicalAndTerm> subsets = Util.getAllSubsets(lat);
		int count = 1; 
		for (LogicalAndTerm subset : subsets) {
			System.out.println("Subset No " + count);
			System.out.println("-------------------");
			System.out.println(subset);
			System.out.println("====================");
			++count;
		}
		
		int expectedSize = 1 << setSize; 
		int actualSize = subsets.size(); 
		assertTrue("Expected Size = " + expectedSize + " but got " + actualSize, 
				   actualSize == expectedSize);

		//assertTrue("no way", 1 == 2);
		
	}

}
