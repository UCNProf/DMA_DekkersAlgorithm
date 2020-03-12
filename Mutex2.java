

/* Second Version of Dekker’s Solution – To remove lockstep synchronization, 
 * it uses two flags to indicate its current status and updates them accordingly 
 * at the entry and exit section. 
 * 
 * */
public class Mutex2 implements IMutex {

	private static boolean producerThread = false; 
	private static boolean consumerThread = false;
	
	@Override
	public void enterMutex() {
		
		System.out.println(Thread.currentThread().getName() + " entering");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			while(consumerThread) {}	
			producerThread = true;
			break;
		case "Consumer":
			while(producerThread) {}
			consumerThread = true;
			break;
		}	
		System.out.println(Thread.currentThread().getName() + " entered" );
	}
	
	@Override
	public void exitMutex() {
		
		System.out.println(Thread.currentThread().getName() + " exiting");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			producerThread = false;
			break;
		case "Consumer":
			consumerThread = false;
			break;
		}	
		
		System.out.println(Thread.currentThread().getName() + " exited, consumerThread = "+ consumerThread +", producerThread = "+ producerThread);
	}
	
/* The problem arising in the above version is mutual exclusion itself. If 
 * threads are preempted (stopped) during flag updation ( i.e during 
 * current_thread = true ) then, both the threads enter their critical section 
 * once the preempted thread is restarted, also the same can be observed at 
 * the start itself, when both the flags are false. 
 * 
 * */
}
