
/**
 * Represents a pair of numeric values. 
 * @author Jervis
 *
 */
public class Pair {
	public double x;
	public double y; 
		
	public Pair() {
		this.x = this.y = 0; 
	}
	public Pair(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double[] toArray() {
		double[] ans = {x,y};
		return ans; 
		
	}
		
	public boolean equals(Pair other) {
		return this.x == other.x && this.y == other.y; 		
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(x + "," + y + "\n");
		return sb.toString(); 		
	}
}
