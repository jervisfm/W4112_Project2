import java.util.ArrayList;
import java.util.Properties;

/**
 * Contain the Main Java Program.  
 * @author Jervis
 * @author Varun
 */
public class QueryDriver
{		
	public static void main(String[] args)
	{
		if (args.length != 2) {
			System.out.println("Error: Need Exactly 2 Parameters");
			System.out.println("Program Usage: java QueryDriver [query] [config]"); 
			System.exit(-1);
		}
		
		String queryPath = args[0];
		String configPath = args[1];

		ArrayList<double[]> queries = QueryParser.parseQuery(queryPath);
		Properties config = QueryParser.parseConfig(configPath);
		if (queries == null || config == null) { // files not found
			System.out.println("Please ensure given config and query " +
							    "files exist and try again");
			System.exit(-1);
		}
		CostModel cm = new CostModel(config);

		ArrayList<LogicalAndTerm> queryTerms = Util.getBasicTerms(queries);

		for(LogicalAndTerm query : queryTerms)
		{
			Algorithm queryInstance = new Algorithm(query);
			PlanRecord solution = queryInstance.findOptimalPlan(cm);
			ArrayList<PlanRecord> plans = queryInstance.plans;
			String solutionCode = Util.getSolutionCode(solution, plans);
			double solutionCost = solution.c;

			System.out.println("==================================================================");
			System.out.println(query.selectivitiesToString());
			System.out.println("------------------------------------------------------------------");
			System.out.println(solutionCode);
			System.out.println("------------------------------------------------------------------");
			System.out.println("cost: " + solutionCost);
		}
	}		
}