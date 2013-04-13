import java.util.ArrayList;


public abstract class Plan {

	public Plan left; 
	public Plan right; 	
	public ArrayList<BasicTerm> terms; 

	public Plan() {
		this(null,null);
	}
	
	public Plan(Plan left, Plan right) {		
		this.left = left;
		this.right = right; 
		this.terms = new ArrayList<BasicTerm>(); 
	}
}
