package sm;
//SENG 523

//ATM

public class CashDisburser {

	private boolean cashDisburserStatus;
	
	/**
	 * The amount of money ready to disburse
	 */
	private int currentDisburseAmount;

	// Constructor
	public CashDisburser() {
		cashDisburserStatus = true;
	}

	public boolean isWorking() {
		return cashDisburserStatus;
	}
	
	public void disburse() {
		System.out.println("DISBURSED " + currentDisburseAmount + " dollar.");
		currentDisburseAmount = 0;
	}
	
	public void putMoney(int cashAmount) {
		this.currentDisburseAmount = cashAmount;
	}
}