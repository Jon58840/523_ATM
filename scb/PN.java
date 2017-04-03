package scb;

public enum PN {

	UNDEFINED(0), WELCOME(1), CHECK_PIN(2), INPUT_WITHDRAW_AMOUNT(3), VERIFY_BALANCE(4), VERIFY_BILLS_AVAILABILTY(5), DISBURSE_BILLS(
			6), EJECT_CARD(7), SYSTEM_FAILURE(8);

	public final int numericVal;

	private PN(int numericVal) {
		this.numericVal = numericVal;
	}

}
