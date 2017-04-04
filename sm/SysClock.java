package sm;

public class SysClock extends Thread implements SysTimer {

	public final static int CLOCK_FREQ_MS = 100;

	int timerVal = 0; // in ms

	private boolean running = true;

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(CLOCK_FREQ_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timerVal = Math.max(0, timerVal - CLOCK_FREQ_MS);
		}
	}

	synchronized public void setTimer(int seconds) {
		timerVal = seconds * 1000;
	}
	
	synchronized public boolean timerRunDown() {
		return timerVal == 0;
	}

}
