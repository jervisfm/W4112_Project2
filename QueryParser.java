import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QueryParser
{
	public static Properties parseConfig(String configPath)
	{
		Properties prop = new Properties();

		try
		{
			prop.load(new FileInputStream(configPath));
		}
		catch (IOException e)
		{
			System.err.println("The config file cannot be found.");
		}

		return prop;
	}

	public static void parseQuery(String queryPath)
	{
		
	}
}