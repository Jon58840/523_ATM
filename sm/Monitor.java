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

	/**
	 * Constructor of a Monitor SM instance.
	 * 
	 * @param gui
	 *            The gui object of this monitor.
	 */
	public Monitor(Gui gui) {
		this.monitorStatus = true;
		this.gui = gui;
	}

	/**
	 * Returns true if the monitor reports that it is working properly.
	 * 
	 * @return true if the monitor is okay, false if it is working faulty.
	 */
	public boolean isWorking() {
		return monitorStatus;
	}

	/**
	 * Set the monitor to working (true) or faulty (false).
	 * 
	 * Used for simulating the system.
	 * 
	 * @param monitorStatus
	 *            The new monitor status
	 */
	public void setMonitorStatus(boolean monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

	/**
	 * Shows the given message on the monitor. Every item in the given array
	 * describes one line.
	 * 
	 * @param msgs
	 */
	public void showMessages(String... msgs) {
		this.gui.setMonitorMessage(msgs);
	}
}