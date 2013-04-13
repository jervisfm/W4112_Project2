import java.util.BitSet;


public class BitVector {

	private long data;
	private static final int MAX_LIMIT = 63;
	private final int setSize; 
	/**
	 * Creates a bit vector that has that many bits available to it. 
	 * @param setSize - # of bits in vector. Maximum allowed value is 63 bits. 
	 * @throws Exception if setSize > 63 or < 1
	 */
	public BitVector(int setSize) throws Exception {
		if (setSize > MAX_LIMIT) {
			throw new Exception("Max set size supported 63. | " + setSize);
		}
		if (setSize < 1) {
			throw new Exception("Cannot not create empty bit set");
		}
		this.data = 0; 
		this.setSize = setSize;
	}
	
	public void increment() {
		++data; 
	}
	
	public void decrement() {
		--data; 
	}
	
	/**
	 * Gets the set size this vector was created with initially. 
	 * @return
	 */
	public int getSetSize() {
		return setSize; 
	}
	
	/**
	 * Determines if the bit at given position is set.
	 * Position schemed being used is zero-index. (i.e. very first bit
	 * on the right will have index 0). 
	 * @param bit
	 * @return
	 * @throws Exception
	 */
	public boolean isSet(int bit) throws Exception {
		if (bit > setSize - 1 || bit < 0) {
			throw new IllegalArgumentException ("Out of Range Bit: " + bit + 
								  				". SetSize=" +setSize);
		}		
		long mask = 1 << bit;
		long result = data & mask; 
		if (result > 0) 
			return true;
		else 
			return false;
	}
	
	
	/**
	 * Return a bitset representation of bits in this vector. 
	 * This bitset is always be of size 64 (due to its java implementation) so
	 * you need to be careful to ignore bits not relevant to you 
	 * (by using the actual setSize of the vector). 
	 * @return
	 */
	public BitSet getBitsSet() {		
		long mask = 1;
		int size = setSize; 
		BitSet bs = new BitSet(size); 
		for(int i = 0; i < size; ++i) {
			long result = data & mask; 
			if (result > 0) {
				bs.set(i);
			}
			mask <<= 1;
		}
		return bs; 
	}
}
