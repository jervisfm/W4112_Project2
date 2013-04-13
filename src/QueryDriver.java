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
		
		
		printList(queries);
		System.out.println(config);
		t0(); 
	}

	private static void t0 () {
		long mask = 1; 
		System.out.println(mask); 
		for (int i = 0; i < 10; ++i) {
			mask <<= 1; 
			System.out.println(mask);
		}
	}
	
	private static ArrayList<LogicalAndTerm> 
					getBasicTerms(ArrayList<double[]> queries){		
		int count = 1; 
		ArrayList<LogicalAndTerm> result = new ArrayList<LogicalAndTerm>(); 
		for(double[] query : queries) {
			LogicalAndTerm lat = new LogicalAndTerm();
			for(double selectivity: query) {
				String functionName = String.format("t%d", count);
				String arg = String.format("o%d[i]", count);
				BasicTerm term = new BasicTerm(functionName, arg, selectivity);
				lat.add(term);
			}
			if (lat.size() > 0)
				result.add(lat); 						
		}
		return result; 
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