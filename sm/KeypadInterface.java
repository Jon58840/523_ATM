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
	 * Returns true if the enter button was pressed between the last call of this function and now.
	 * This means that this method will reset the value.
	 * 
	 * @return true, if enter button was pressed. False, otherwise.
	 */
	public boolean isEnterButtonPressed();
	
	/**
	 * Returns true if the cancel button was pressed between the last call of this function and now.
	 * This means that this method will reset the value.
	 * 
	 * @return true, if cancel button was pressed. False, otherwise.
	 */
	public boolean isCancelButtonPressed();
	
	/**
	 * Returns the latest data input. If reset is set to true, data input will
	 * be reset afterwards so new data can be read without problems.
	 * 
	 * @param reset
	 *            true if the returned data input of the keypad should be reset.
	 * 
	 * @return the latest data input.
	 */
	public int getDataInput(boolean reset);

	/**
	 * Returns true if the keypad reports that it is working properly.
	 * 
	 * @return true if the keypad is okay, false if it is faulty.
	 */
	public boolean isKeypadStatus();

}
