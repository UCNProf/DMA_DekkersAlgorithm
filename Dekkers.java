

public class Dekkers {
	public static void main(String[] args) {
		
		IBuffer buffer = new Buffer();
		IMutex mutex = new Mutex5();
		
		Producer prod = new Producer(buffer, mutex);
		Consumer cons = new Consumer(buffer, mutex);
		
		prod.start();
		cons.start();
	}
}
