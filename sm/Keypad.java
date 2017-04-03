package sm;
//SENG 523
//ATM

public class Keypad implements KeypadInterface {

	/**
	 * The maximal value for the data input of this keypad
	 * 
	 * TODO check whats correct (keypad says 1000, but database allwos up to 9999)
	 */
	public final static int DATA_INPUT_MAX = 9999;

	/**
	 * The data that was entered by the user.
	 */
	private int dataInput;

	/**
	 * Keypad constructor
	 */
	public Keypad() {
		this.dataInput = 0;
	}

	@Override
	public void dataButtonPressed(int dataValue) {
		if (dataValue < 0 || dataValue > 9) {
			throw new IllegalArgumentException(
					"dataButtonPressed: The given value " + dataValue + "is not in the range of [0,9]");
		}
		dataInput = (dataInput * 10 + dataValue);
		if (dataInput > DATA_INPUT_MAX) {
			// reset the input value if larger than max
			dataInput = dataValue;
		}

	}
}