import java.util.ArrayList;


/**
 *
 * Represents a Collection of BasicTerms
 * @author Jervis
 * @author Varun
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

	public LogicalAndTerm(ArrayList<BasicTerm> data) {
		if (data ==  null)
			throw new IllegalArgumentException("data cannot be null");
		this.data = data;
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


	public double getNoBranchAlgCost(CostModel cm) {
		if (cm == null)
			throw new NullPointerException("terms or cost model is null");
		if (data.size() < 1)
			throw new IllegalArgumentException("Need at least 1 term");

		int k = data.size();
		int r = cm.r;
		int l = cm.l;
		int f = cm.f;
		int a = cm.a;

		return k*r + (k-1)*l + f*k + a;
	}

	/**
	 * Get the full cost of this group of basic terms being
	 * logically - anded together.
	 * @param cost
	 * @return
	 */
	public double getCost(CostModel cost) {
		/**
		 * General Cost Formula is as follows:
		 *
		 * kr * (k-1)*l + (f1 + ... + fk) + t + mq + (p1*...*pk)*a
		 * Where:
		 * k = # of Basic terms
		 * l = cost of performing a 'logical' "and" (i.e. & op)
		 * f1 ... fk = Cost of doing function evals on all K functions.
		 * t = Cost of an if test evaluation
		 * m = Cost of branch misprediction
		 * p1 *...* pk = Compound / combined selectivity of all K basic terms
		 * r = Cost of accessing an array element
		 * q = p1*...*pk if p1*...*pk <= 0.5 ; else q = 1 - (p1*...*pk)
		 * a = cost of writing an answer to the answer array.
		 */
		double selectivities = getSelectivity();
		int m = cost.m;
		double q = selectivities <= 0.5 ? selectivities : 1 - selectivities;
		int a = cost.a;

		// Note: we assume each function has equal cost that's why
		// we multiply it (f) by # of terms (k)
		return getFixedCost(cost) + m*q + selectivities*a;
	}

	public int size() {
		return data.size();
	}

	/**
	 * Computes the fixed cost for this &-term.
	 * Fixed cost is the cost that does not vary with the selectivity
	 * of the terms.
	 * @return
	 */
	public double getFixedCost(CostModel cm) {
		/**
		 * fixed cost = k*r + (k-1)*l + f1 + ... + fk + t
		 * where :
		 *    k = # of basic terms
		 *    r = cost of accessing an array element
		 *    l = cost of performing a logical and op
		 *    f1 + ... fk = cost of applying function 1 through k to its arg
		 *    t = cost of performing the if test
		 */

		if (cm == null)
			throw new IllegalArgumentException("Cannot have null cost model");


		int k = data.size();
		int r = cm.r;
		int l = cm.l;
		int f = cm.f;
		int t = cm.t;

		return k*r + (k-1) * l + k*f + t;
	}

	/**
	 * Computes the C-Metric for this &-term.
	 * @param cm - CostModel to use
	 * @return
	 */
	public Pair getCMetric(CostModel cm) {
		if (cm == null)
			throw new IllegalArgumentException("CostModel cannot be null");
		/**
		 * C-metric is ( (p-1)/fcost(E) , p )
		 * where:
		 *		p = combined selectivity
		 *		fcost(E) = fixed cost of this &-term
		 */
		double fixedCost = getFixedCost(cm);
		double selectivites = getSelectivity();
		double x = (selectivites - 1) / fixedCost;
		double y = selectivites;
		return new Pair(x,y);
	}

	/**
	 * Computes the D-Metric for this &-term
	 * @param cm - CostModel to use
	 * @return
	 */
	public Pair getDMetric(CostModel cm) {
		if (cm == null)
			throw new IllegalArgumentException("CostModel cannot be null");
		/**
		 * D-metric is ( fcost(E) , p)
		 * where:
		 *		p = combined selectivity
		 *		fcost(E) = fixed cost of this &-term
		 */
		double fixedCost = getFixedCost(cm);
		double selectivities = getSelectivity();
		return new Pair(fixedCost, selectivities);
	}

	/**
	 * Computes the compound selectivity of all the basic terms
	 * that we have.
	 * @return
	 */
	public double getSelectivity() {
		if (isEmpty())
			return 0;
		double result = 1.0;
		for (int i = 0; i < data.size(); ++i) {
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

	public String selectivitiesToString() {
		StringBuffer sb = new StringBuffer();

		for(BasicTerm t : data) {
			sb.append(t.selectivity);
			sb.append(" ");
		}

		return sb.toString();
	}
}
