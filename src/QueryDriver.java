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