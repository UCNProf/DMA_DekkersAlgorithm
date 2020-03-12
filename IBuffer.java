

//Buffer interface specifies methods to access buffer data.
public interface IBuffer {
			
	public void set(int value) throws InterruptedException; // place value into buffer
	public int get() throws InterruptedException;  // return value from buffer
}

