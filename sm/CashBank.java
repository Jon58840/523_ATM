package sm;
//SENG 523

//ATM

public class CashBank {

	/**
	 * true = working. false=faulty.
	 */
	private boolean cashBankStatus;

	/**
	 * The amount of money in the ATMs cash bank.
	 */
	private int cashAmount;

	/**
	 * Reference to the cash disburser to transfer money from the cash bank to
	 * the cash disburser.
	 */
	private final CashDisburser cashDisburser;

	/**
	 * Constructor of a CashBank device object.
	 * 
	 * @param startCashAmount
	 *            the initial amount of money in the cash bank
	 * @param cashDisburser
	 *            a reference to the related cash disburser
	 */
	public CashBank(int startCashAmount, CashDisburser cashDisburser) {
		cashBankStatus = true;
		this.cashAmount = startCashAmount;
		this.cashDisburser = cashDisburser;
	}

	/**
	 * Returns true if this cash bank is working properly, false otherwise.
	 * 
	 * @return true, if bank is working, false if faulty.
	 */
	public boolean isWorking() {
		return cashBankStatus;
	}

	/**
	 * Decreases the amount of money in this cash bank by the given amount and
	 * moves it into the disburser.
	 * 
	 * @param amount
	 *            The amount of money to be put into the disburser
	 */
	public void decreaseCashByAmount(int amount) {
		this.cashAmount -= amount;
		cashDisburser.putMoney(amount);
	}

	/**
	 * Returns the amount of money contained in this cash bank.
	 * 
	 * @return The amount of money contained in this cash bank
	 */
	public int availableAmountOfCash() {
		return cashAmount;
	}
}