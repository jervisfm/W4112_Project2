import java.util.BitSet;


public class BitVector {

	private long data;
	private BitSet bs; 
	private static final int LIMIT = 63;
	public BitVector(int setSize) throws Exception {
		if (setSize > LIMIT) {
			throw new Exception("Max set size supported 63. | " + setSize);
		}
		this.data = 1; 
	}
	
	public void increment() {
		++data; 
	}
	
	public void decrement() {
		--data; 
	}
	
	public BitSet getBitsSet() {
		BitSet bs = new BitSet(LIMIT); 
		long mask = 1; 
		for(int i = 0; i < LIMIT; ++i) {
			long result = data & mask; 
			if (result > 0) {
				bs.set(i);
			}
			mask <<= 1;
		}
		return bs; 
	}
}
