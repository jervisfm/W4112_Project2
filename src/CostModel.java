
/**
 * Represents the cost model to use in finding
 * optimal query plan.
 * @author Jervis
 *
 */
public class CostModel {
	/**
	 * Cost of accessing an array element rj[i]
	 */
	public final int r;
	/**
	 * Cost of performing an if test
	 */
	public final int t;
	/**
	 * Cost of performing a logical "and" test
	 */
	public final int l; 
	/**
	 * Cost of Branch mis-prediction
	 */
	public final int m; 
	/**
	 * Cost of writing an answer to the answer array
	 */
	public final int a;
	/**
	 * Cost of apply function f to its arguemnt
	 */
	public final int f; 
	
	public CostModel(int a, int f, int l, int m, int t, int r) {
		this.a=a;
		this.f=f;
		this.l=l;
		this.m=m;
		this.t=t;
		this.r=r;		
	}
}
