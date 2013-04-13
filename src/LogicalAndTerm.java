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
	public LogicalAndTerm() {
		this.data  = new ArrayList<BasicTerm>();		
	}
	
	public void add(BasicTerm term) {
		data.add(term); 
	}
	
	public ArrayList<BasicTerm> getTerms() {
		return data; 
	}
	
	public BasicTerm get(int i ) {
		return data.get(i);
	}
	
	public int size() {
		return data.size(); 
	}
	
	public boolean isEmpty() {
		return size() == 0; 
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (BasicTerm t : data) {
			sb.append(t.toString());
			sb.append(" & "); 
			sb.append("\n");
		}
		return sb.toString(); 
	}
}
