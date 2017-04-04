package sm;
//SENG 523

//ATM

public class CashBank {

	private boolean cashBankStatus;
	
	private int cashAmount;
	
	private final CashDisburser cashDisburser;

	// Constructor
	public CashBank(CashDisburser cashDisburser) {
		this.cashDisburser = cashDisburser;
		cashBankStatus = true;
	}

	public boolean isWorking() {
		return cashBankStatus;
	}
	
	public void decreaseCashByAmount(int amount) {
		this.cashAmount -= amount;
		cashDisburser.putMoney(cashAmount);
	}
}