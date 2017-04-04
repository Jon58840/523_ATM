package sm;
//SENG 523

//ATM

public class CashDisburser {

	private boolean cashDisburserStatus;

	// Constructor
	public CashDisburser() {
		cashDisburserStatus = true;
	}

	public boolean isWorking() {
		return cashDisburserStatus;
	}
}