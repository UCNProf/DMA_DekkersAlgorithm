

/* First Version of Dekker’s Solution – The idea is to use common or shared 
 * thread number between processes and stop the other process from entering 
 * its critical section if the shared thread indicates the former one already 
 * running.
 * 
 * */

public class Mutex1 implements IMutex {

	private volatile String threadName = "Producer";
	
	@Override
	public void enterMutex() {
		
		System.out.println(Thread.currentThread().getName() + " entering");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			while(threadName == "Consumer") {}			
			break;
		case "Consumer":
			while(threadName == "Producer") {}
			break;
		}	
		System.out.println(Thread.currentThread().getName() + " entered" );
	}
	
	@Override
	public void exitMutex() {
		
		System.out.println(Thread.currentThread().getName() + " exiting");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			threadName = "Consumer";
			break;
		case "Consumer":
			threadName = "Producer";
			break;
		}	
		
		System.out.println(Thread.currentThread().getName() + " exited, setting threadName = "+ threadName);
	}
	
/* The problem arising in the above implementation is lockstep synchronization, 
 * i.e each thread depends on the other for its execution. If one of the 
 * processes completes, then the second process runs, gives access to the 
 * completed one and waits for its turn, however, the former process is already 
 * completed and would never run to return the access back to the latter one. 
 * Hence, the second process waits infinitely then. 
 * 
 * */
}
