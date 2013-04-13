import java.util.ArrayList;


/**
 * 
 * Represents a Collection of BasicTerms
 * @author Jervis
 */
public class LogicalAndTerm {

	private ArrayList<BasicTerm> data; 
	/**
	 * We use to label in what array index this subset is being stored in. 
	 * It helps us to do easy look ups. 
	 */
	private int subset_no; 
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
	
	public void setSubsetNo(int n) {
		if (n < 0) {
			throw new IllegalArgumentException(
					"subset no can't be negative: " + n);
		}
		this.subset_no = n; 
	}
	
	public int getSubsetNo() {
		return this.subset_no; 
	}
	
	public int size() {
		return data.size(); 
	}
	
	/**
	 * Computes the compound selectivity of all the basic terms
	 * that we have. 
	 * @return
	 */
	public double getSelectivity() {
		if (isEmpty())
			return 0; 
		double start = data.get(0).selectivity;
		double result = start; 
		for (int i = 1; i < data.size(); ++i) {
			result *= data.get(i).selectivity;
		}
		return result; 
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
