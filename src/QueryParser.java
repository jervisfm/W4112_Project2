import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

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
			return null;
		}

		return prop;
	}

	public static ArrayList<double[]> parseQuery(String queryPath)
	{
		Scanner sc = null;

		try
		{
			sc = new Scanner(new File(queryPath));
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The query file cannot be found.");
			return null; 
		}

		ArrayList<double[]> query = new ArrayList<double[]>();

		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			String[] tokens = line.split(" ");
			double[] selectivities = new double[tokens.length];

			for(int i = 0; i < tokens.length; i++)
			{
				try
				{
					double x = Double.parseDouble(tokens[i]);

					if(x >= 0 && x <= 1)
					{
						selectivities[i] = x;
					}
				}
				catch(NumberFormatException nfe)
				{
					System.err.println("One of the numbers could not parsed to a double.");
				}
			}

			query.add(selectivities);
		}

		return query;
	}
}