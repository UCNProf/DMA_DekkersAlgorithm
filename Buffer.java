

public class Buffer implements IBuffer {

	private int buffer = -1;

	@Override
	public void set(int value) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " writes: "+ value);
		Thread.sleep((int) (Math.random() * 3001));
		buffer = value;
	}

	@Override
	public int get() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " reads: "+ buffer);
		Thread.sleep((int) (Math.random() * 3001));
		return buffer;
	}

}
