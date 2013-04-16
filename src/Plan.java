import java.util.ArrayList;


public abstract class Plan {

	public Plan left;
	public Plan right;
	public ArrayList<BasicTerm> terms;

	public Plan() {
		this(null,null, new ArrayList<BasicTerm>());
	}

	public Plan(Plan left, Plan right) {
		this(left, right, new ArrayList<BasicTerm>());
	}

	public Plan(Plan left, Plan right, ArrayList<BasicTerm> terms) {
		this.left = left;
		this.right = right;
		this.terms = terms;
	}
}
