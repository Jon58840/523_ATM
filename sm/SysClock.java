package sm;

public class SysClock extends Thread implements SysTimer {

	public final static int CLOCK_FREQ_MS = 100;

	private int timerVal = 0; // in ms
	
	private boolean timerOn = false;

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
		timerOn = true;
	}
	
	synchronized public boolean timerRunDown() {
		return timerVal == 0;
	}
	
	synchronized public void deactivateTimer() {
		this.timerOn = false;
	}
	
	synchronized public boolean isTimerOn() {
		return timerOn;
	}
	
	synchronized public void terminateClock() {
		this.running = false;
	}

}
