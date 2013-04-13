import java.util.ArrayList;


/**
 * 
 * Represents a Collection of BasicTerms
 * @author Jervis
 */
public class LogicalAndTerm {

	private ArrayList<BasicTerm> data; 
	public LogicalAndTerm(BasicTerm term) {
		this.data  = new ArrayList<BasicTerm>();
		data.add(term);		
	}
	
	public void add(BasicTerm term) {
		data.add(term); 
	}
	
	public ArrayList<BasicTerm> getTerms() {
		return data; 
	}
}
