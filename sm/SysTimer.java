package sm;

public interface SysTimer {
	
	public void setTimer(int seconds);
	
	public boolean timerRunDown();
	
	public void deactivateTimer();
	
	boolean isTimerOn();

}
