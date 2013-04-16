import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.junit.Test;


public class UnitTests {

	private LogicalAndTerm getSampleTerms() {
		double [] selectionConditions = {0.700000, 0.800000,
										  0.800000, 0.900000};
		ArrayList<double[]> queries = new ArrayList<double[]>();
		queries.add(selectionConditions);
		return Util.getBasicTerms(queries).get(0);
	}
	
	private LogicalAndTerm getSampleTerms2() {
		double [] selectionConditions = {0.700000, 0.800000};
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

	@Test
	public void testgetCostLogicalAndTerm() {
		CostModel c = CostModel.getDefaultCostModel();
		LogicalAndTerm lat = getSampleTerms();
		double actual = lat.getCost(c);
		double s = lat.getSelectivity();
		double expected  = 4*c.r + 3*c.l + 4*c.f + c.t + s*c.m + s*c.a;
		assertTrue("Expected Cost = " + expected + " but got " + actual,
				   actual == expected);
	}
	@Test
	public void testComputeCostNoBranch() {
		CostModel c = CostModel.getDefaultCostModel();
		LogicalAndTerm lat = getSampleTerms();
		double actual = Util.computeNoBranchCost(lat.getTerms(), c);
		double s = lat.getSelectivity();
		double k = 4;
		double expected  = k*c.r + (k-1) * c.l + k*c.f + c.a;
		assertTrue("Expected Cost = " + expected + " but got " + actual,
				   actual == expected);
	}

	@Test
	public void testCreatePlanRecordsArray() {
		LogicalAndTerm lat = getSampleTerms();
		ArrayList<PlanRecord> plans = Algorithm.createPlanRecordsArray(lat);
		assertTrue(plans.size() == (long) (Math.pow(2, lat.size()) - 1));
		
	}

	@Test
	public void testGetFixedCostLogicalAndTerm() {
		LogicalAndTerm lat = getSampleTerms();
		int k = lat.size();
		CostModel cm = CostModel.getDefaultCostModel();
		double actual = lat.getFixedCost(cm);
		double expected  = k * cm.r + (k-1)*cm.l + k*cm.f + cm.t;
		assertTrue("Expected Fixed Cost = " + expected + " but got " + actual,
				   actual == expected);
	}

	@Test
	public void testGetCMetricLogicalAndTerm() {
		LogicalAndTerm lat = getSampleTerms();
		CostModel cm = CostModel.getDefaultCostModel();
		double p = lat.getSelectivity();
		double x = (p-1) / lat.getFixedCost(cm);
		double y = p ;
		Pair expected = new Pair(x,y);
		Pair actual = lat.getCMetric(cm);
		assertTrue("Expected CMetric Cost = " + expected + " but got " + actual,
				   actual.equals(expected));
	}

	@Test
	public void testGetDMetricLogicalAndTerm() {
		LogicalAndTerm lat = getSampleTerms();
		CostModel cm = CostModel.getDefaultCostModel();
		double p = lat.getSelectivity();
		double x = lat.getFixedCost(cm);
		double y = p ;
		Pair expected = new Pair(x,y);
		Pair actual = lat.getDMetric(cm);
		assertTrue("Expected DMetric Cost = " + expected + " but got " + actual,
				   actual.equals(expected));
	}
	
	@Test
	public void testAlgorithmOnSmallInputs() {
		LogicalAndTerm terms = getSampleTerms2();
		CostModel cm = CostModel.getDefaultCostModel(); 
		Algorithm alg = new Algorithm(terms); 
		//PlanRecord actual = alg.findOptimialPlan(cm);

		
		// Compute expected answer: 
		ArrayList<LogicalAndTerm> subsets = Util.getAllSubsets(terms);
		subsets = Util.removeEmptySubset(subsets); 
		Util.printSubsets(subsets);
		int count = 1; 
		
		for(LogicalAndTerm subset: subsets) {
			System.out.println("cost == " + subset.getCost(cm) + " | " + subset.getNoBranchAlgCost() );
		}
		
		Plan left = new LogicalAndPlan(null, null, subsets.get(0).getTerms());
		Plan right = new LogicalAndPlan(null, null, subsets.get(1).getTerms());
		BranchingAndPlan bap = new BranchingAndPlan(left, right, null); 
		double totalCost = Util.planCost(bap, cm);
		System.out.println ("Total Cost == " + totalCost);
		
		//PlanRecord expected = new PlanRecord(n, p, b, c, plan, left, right, subset); 
	}
	
	@Test
	public void testGetCommonsElementsSize() throws CloneNotSupportedException {
		LinkedHashSet<BasicTerm> s1 = new LinkedHashSet<BasicTerm>() ;
		LinkedHashSet<BasicTerm> s2 = new LinkedHashSet<BasicTerm>(); 
		
		BasicTerm b1 = new BasicTerm("t1", "blah", 0.6);
		BasicTerm b2 = new BasicTerm("t2", "blah2", 0.4);
		
		s1.add((BasicTerm) b1.clone()); 
		s2.add((BasicTerm) b1.clone());
		s2.add((BasicTerm) b2.clone());
		
		int actual = Util.getCommonElementsSize(s1, s2);
		int expected = 1;
		assertTrue("Expected Common Size = " + expected + " but got " + actual,
				   actual == expected);
	}
}
