import java.util.ArrayList;
import java.util.Properties;

public class QueryDriver
{
	public static void main(String[] args)
	{
		String queryPath = args[0];
		String configPath = args[1];

		ArrayList<double[]> queries = QueryParser.parseQuery(queryPath);
		Properties config = QueryParser.parseConfig(configPath);
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

	private static void t0 () {
		long mask = 1;
		System.out.println(mask);
		for (int i = 0; i < 10; ++i) {
			mask <<= 1;
			System.out.println(mask);
		}
	}

	private static void printList(ArrayList<double[]> query) {
		for(double[] array : query) {
			for(double d : array)  {
				System.out.printf("%f, ", d);
			}
			System.out.println("");
		}
	}
}