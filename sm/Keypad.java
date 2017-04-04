package sm;
//SENG 523

//ATM

public class Keypad implements KeypadInterface {

	/**
	 * The maximal value for the data input of this keypad
	 * 
	 * TODO check whats correct (keypad says 1000, but database allwos up to
	 * 9999)
	 */
	public final static int DATA_INPUT_MAX = 9999;

	/**
	 * The data that was entered by the user.
	 */
	private int dataInput;
	
	private boolean enterPressed;
	
	private boolean cancelPressed;

	/**
	 * The status of the keypad.
	 * 
	 * true=normal, false=faulty
	 */
	private boolean keypadStatus;

	/**
	 * Keypad constructor
	 */
	public Keypad() {
		this.dataInput = 0;
		this.keypadStatus = true;
		this.enterPressed = false;
		this.cancelPressed = false;
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
	
	public void setEnterButtonPressed(boolean enterPressed) {
		this.enterPressed = enterPressed;
	}
	
	@Override
	public boolean isEnterButtonPressed() {
		return enterPressed;
	}
	
	public void setCancelButtonPressed(boolean cancelPressed) {
		this.cancelPressed = cancelPressed;
	}
	
	@Override
	public boolean isCancelButtonPressed() {
		return cancelPressed;
	}

	/**
	 * Returns true if the keypad reports that it is working properly.
	 * 
	 * @return true if the keypad is okay, false if it is faulty.
	 */
	@Override
	public boolean isKeypadStatus() {
		return keypadStatus;
	}

	/**
	 * Set the keypad to working (true) or faulty (false).
	 * 
	 * Used for simulating the system.
	 * 
	 * @param keypadStatus
	 *            The new keypad status
	 */
	public void setKeypadStatus(boolean keypadStatus) {
		this.keypadStatus = keypadStatus;
	}
}