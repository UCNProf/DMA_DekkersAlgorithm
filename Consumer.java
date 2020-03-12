

public class Consumer extends Thread {

	private IBuffer buffer;
	private IMutex mutex;
	
	public Consumer(IBuffer buffer, IMutex mutex) {
		
		super("Consumer");
		
		this.buffer = buffer;
		this.mutex = mutex;
	}

	@Override
	public void run() {

		int sum = 0;
		
		for(int v = 1; v <= 4; v++) {
			
			try {
				
				this.mutex.enterMutex();
								
				sum += buffer.get();
				
				this.mutex.exitMutex();
				
			} catch (InterruptedException ex) {
				
				ex.printStackTrace();				
			}
		}
		
		System.out.println(getName() + " read values totaling: "+ sum);
	}	
}
