package sm;

public interface KeypadInterface {

	/**
	 * To be called if a data key on the keypad was pressed. The entered data
	 * value has to fulfill the condition 0 <= dataValue <= 9
	 * 
	 * @param dataValue
	 *            The key value in the interval [0,9]
	 */
	void dataButtonPressed(int dataValue);

	/**
	 * Returns true if the enter button was pressed
	 * 
	 * @return true, if enter button was pressed. False, otherwise.
	 */
	public boolean isEnterButtonPressed();
	
	/**
	 * Returns true if the cancel button was pressed
	 * 
	 * @return true, if cancel button was pressed. False, otherwise.
	 */
	public boolean isCancelButtonPressed();

	/**
	 * Returns true if the keypad reports that it is working properly.
	 * 
	 * @return true if the keypad is okay, false if it is faulty.
	 */
	public boolean isKeypadStatus();

}
