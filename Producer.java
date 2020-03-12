

// Producers run method controls a producer thread that stores 
// values from 1 to 4 in a shared location

public class Producer extends Thread {
	
	private IBuffer buffer; // reference to shared object
	private IMutex mutex;

	// Constructor
	public Producer(IBuffer buffer, IMutex mutex) {
		
		super("Producer"); // create thread named "Producer"

		this.buffer = buffer; // initialize shared location
		this.mutex = mutex;
	}

	@Override
	public void run() {

		for(int v = 1; v <= 4; v++) {
			
			try {
				
				this.mutex.enterMutex();
				
				this.buffer.set(v);
				
				this.mutex.exitMutex();
				
			} catch (InterruptedException ex) {
			
				ex.printStackTrace();
			}			
		}

		System.out.println(getName() + " done producing");
	}	
}
