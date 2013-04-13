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
	public void testGetBasicTerms() {
		double [] selectionConditions = {0.700000, 0.800000, 
				  0.800000, 0.900000};
		ArrayList<double[]> queries = new ArrayList<double[]>();
		queries.add(selectionConditions); 
		LogicalAndTerm lat = Util.getBasicTerms(queries).get(0);
		assertTrue(lat.size() == 4); 
		int count = 0; 
		for (BasicTerm b : lat.getTerms()) {
			assertTrue(b.selectivity == selectionConditions[count]); 
			++count;
		}
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
	}
	
	@Test
	public void testRemoveEmptyTerm() throws Exception {
		LogicalAndTerm lat = getSampleTerms();
		int setSize = lat.size(); 
		ArrayList<LogicalAndTerm> subsets = Util.getAllSubsets(lat);
		
		int expectedSize = 1 << setSize; 
		int actualSize = subsets.size(); 
		assertTrue("Expected Size = " + expectedSize + " but got " + actualSize, 
				   actualSize == expectedSize);
		
		subsets = Util.removeEmptySubset(subsets); 
		expectedSize -= 1; 
		actualSize = subsets.size(); 
		assertTrue("Expected Size = " + expectedSize + " but got " + actualSize, 
				   actualSize == expectedSize);
	}

	@Test
	public void testgetSelectivity() {
		LogicalAndTerm lat = getSampleTerms(); 
		double expected = 0.7 * 0.8 * 0.8 * 0.9; 
		double actual = lat.getSelectivity(); 
		assertTrue("Expected Selectivty = " + expected + " but got " + actual, 
				   actual == expected);
	}
	
}
