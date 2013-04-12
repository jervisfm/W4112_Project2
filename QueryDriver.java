import java.util.ArrayList;
import java.util.Properties;

public class QueryDriver
{
	public static void main(String[] args)
	{
		String queryPath = args[0];
		String configPath = args[1];

		ArrayList<double[]> query = QueryParser.parseQuery(queryPath);
		Properties config = QueryParser.parseConfig(configPath);
	}
}