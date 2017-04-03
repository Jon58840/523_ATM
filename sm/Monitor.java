package sm;
//SENG 523
//ATM

public class Monitor {

	/**
	 * The status of the monitor.
	 * 
	 * true=normal, false=faulty
	 */
	private boolean monitorStatus;

	/**
	 * The gui used to display monitor messages
	 * 
	 * TODO maybe add interface in between
	 */
	private final Gui gui;

	// Constructor
	public Monitor(Gui gui) {
		this.monitorStatus = true;
		this.gui = gui;
	}

	public boolean isMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(boolean monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	public void showMessage(String msg) {
		this.gui.setMonitorMessage(msg);
	}
}