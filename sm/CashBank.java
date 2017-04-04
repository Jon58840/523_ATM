package sm;
//SENG 523

//ATM

public class CashBank {

	private boolean cashBankStatus;
	
	private int cashAmount;
	
	private final CashDisburser cashDisburser;

	// Constructor
	public CashBank(int startCashAmount, CashDisburser cashDisburser) {
		cashBankStatus = true;
		this.cashAmount = startCashAmount;
		this.cashDisburser = cashDisburser;
	}

	public boolean isWorking() {
		return cashBankStatus;
	}
	
	public void decreaseCashByAmount(int amount) {
		this.cashAmount -= amount;
		cashDisburser.putMoney(amount);
	}
	
	public int availableAmountOfCash() {
		return cashAmount;
	}
}