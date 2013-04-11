import java.util.Properties;

public class QueryDriver
{
	public static void main(String[] args)
	{
		String queryPath = args[0];
		String configPath = args[1];

		Properties prop = QueryParser.parseConfig(configPath);
	}
}