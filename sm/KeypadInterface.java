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

}
