package sm;
//SENG 523

//ATM

public class CashBank {

	private boolean cashBankStatus;

	// Constructor
	public CashBank() {
		cashBankStatus = true;
	}

	public boolean isWorking() {
		return cashBankStatus;
	}
}