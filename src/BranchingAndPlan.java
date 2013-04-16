import java.util.ArrayList;

/**
 *
 * @author Jervis Muindi
 *
 */
public class BranchingAndPlan extends Plan {

	public BranchingAndPlan() {
		super();
	}

	public BranchingAndPlan(Plan left, Plan right, ArrayList<BasicTerm> terms) {
		this.left = left;
		this.right = right;
		this.terms = terms;
	}
}
