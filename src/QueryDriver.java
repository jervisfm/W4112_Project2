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
	
	
	
	private static void printList(ArrayList<double[]> query) {
		for(double[] array : query) {
			for(double d : array)  {
				System.out.printf("%f, ", d);
			}
			System.out.println("");
		}
	}
}