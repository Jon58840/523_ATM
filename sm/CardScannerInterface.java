package sm;

public interface CardScannerInterface {

	/**
	 * Simulates the insertion of a card and therefore relates to set
	 * cardInsertEngine in the specification to true by writing at
	 * cardInsertAddr.
	 * 
	 * @param The
	 *            number read (simulated) from the card.
	 */
	public void inputCard(int cardNumber);

	/**
	 * Simulates the ejection of a card and therefore relates to set
	 * cardEjectEngine in the specification to true by writing at cardEjectAddr.
	 */
	public void ejectCard();

	/**
	 * Returns true if card is inserted, false otherwise.
	 * 
	 * @return true if card is inserted, false otherwise.
	 */
	public boolean isCardInsertEngine();

	/**
	 * Set the card scanner to working (true) or faulty (false).
	 * 
	 * Used for simulating the system.
	 * 
	 * @param cardReaderIsWorking
	 *            true, if working. false, otherwise.
	 */
	public void setCardReaderStatus(boolean cardReaderIsWorking);
}
