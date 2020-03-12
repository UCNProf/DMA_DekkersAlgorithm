

public class Mutex5 implements IMutex {

	private volatile String favouredThread = "Producer";
	
	private boolean producerThreadWantsToEnter = false; 
	private boolean consumerThreadWantsToEnter = false;
	
	@Override
	public void enterMutex() throws InterruptedException {
		
		System.out.println(Thread.currentThread().getName() + " entering");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			producerThreadWantsToEnter = true;
			while(consumerThreadWantsToEnter) {
				if(favouredThread == "Consumer") {
					producerThreadWantsToEnter = false;
					while(favouredThread == "Consumer") {  }
					producerThreadWantsToEnter = true;
				}
			}	
			break;
		case "Consumer":
			consumerThreadWantsToEnter = true;
			while(producerThreadWantsToEnter) {
				if(favouredThread == "Producer") {
					consumerThreadWantsToEnter = false;
					while(favouredThread == "Producer") {   }
					consumerThreadWantsToEnter = true;					
				}
			}
			break;
		}	
		System.out.println(Thread.currentThread().getName() + " entered" );
	}
	
	@Override
	public void exitMutex() {
		
		System.out.println(Thread.currentThread().getName() + " exiting");
		
		switch(Thread.currentThread().getName()) {
		case "Producer":
			favouredThread = "Consumer";			
			producerThreadWantsToEnter = false;
			break;
		case "Consumer":
			favouredThread = "Producer";			
			consumerThreadWantsToEnter = false;
			break;
		}	
		
		System.out.println(Thread.currentThread().getName() + " exited");
	}
	

}
