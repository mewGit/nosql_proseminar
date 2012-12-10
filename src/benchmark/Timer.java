package benchmark;

public class Timer {
	
	private long diff = 0;
	private long starTime = 0;
	
	public void start() {
		starTime = System.nanoTime();
	}
	
	public long stop() {
		diff = System.nanoTime() - starTime;
		return diff;
	}
}
