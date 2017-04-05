package sm;
//SENG 523

//ATM

public class CashDisburser {

	/**
	 * true = working, false = faulty.
	 */
	private boolean cashDisburserStatus;

	/**
	 * The amount of money ready to disburse
	 */
	private int currentDisburseAmount;

	/**
	 * Constructor of a CashDisburser device object.
	 */
	public CashDisburser() {
		cashDisburserStatus = true;
	}

	/**
	 * Returns true if this disburser is working properly, false otherwise.
	 * 
	 * @return true, if disburser is working, false if faulty.
	 */
	public boolean isWorking() {
		return cashDisburserStatus;
	}

	/**
	 * Sets the status of this device to working (true) or faulty (false).
	 * 
	 * @param isWorking
	 *            true, if working. false, if faulty
	 */
	public void setCashDisburserStatus(boolean isWorking) {
		cashDisburserStatus = isWorking;
	}

	/**
	 * Disburses the current amount in the disburser.
	 */
	public void disburse() {
		System.out.println("CashDisburser:\tDISBURSED " + currentDisburseAmount + " dollar.");
		currentDisburseAmount = 0;
	}

	/**
	 * Puts the given amount of money into the disburser
	 * 
	 * @param cashAmount
	 *            the amount that can be disbursed later
	 */
	public void putMoney(int cashAmount) {
		this.currentDisburseAmount = cashAmount;
	}
}