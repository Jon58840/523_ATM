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
	
	public void setCashDisburserStatus(boolean isWorking) {
		cashDisburserStatus = isWorking;
	}
	
	public void disburse() {
		System.out.println("CashDisburser:\tDISBURSED " + currentDisburseAmount + " dollar.");
		currentDisburseAmount = 0;
	}
	
	public void putMoney(int cashAmount) {
		this.currentDisburseAmount = cashAmount;
	}
}