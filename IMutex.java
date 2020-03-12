

public interface IMutex {

	public void enterMutex() throws InterruptedException;
	public void exitMutex();
}
