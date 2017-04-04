package sm;
//SENG 523

//ATM

public class CardScanner implements CardScannerInterface {

	/**
	 * Represents the memory at cardInputAddr (specification) which holds the
	 * specified variable cardInput. (last read card number)
	 */
	private int cardInput;

	/**
	 * true card is inserted, false card is not inserted.
	 */
	private boolean cardEngine;

	// True = Normal/working
	// False = Faulty
	private boolean cardReadStatus;
	private boolean cardInsertStatus;
	private boolean cardEjectStatus;

	/**
	 * Constructor to create a new card scanner object to simulate the actions
	 * of a card scanner.
	 */
	public CardScanner() {
		this.cardReadStatus = true;
		this.cardInsertStatus = true;
		this.cardEjectStatus = true;

		this.cardEngine = false;
	}

	/**
	 * Simulates the insertion of a card and therefore relates to set
	 * cardInsertEngine in the specification to true by writing at
	 * cardInsertAddr.
	 * 
	 * @param The
	 *            number read (simulated) from the card.
	 */
	public void inputCard(int cardNumber) {
		if (cardNumber >= 0 && cardNumber <= 1000000) {
			cardInput = cardNumber;
			cardEngine = true;
		} else {
			throw new IllegalArgumentException(cardNumber + " is no valid card");
		}

	}

	/**
	 * Simulates the ejection of a card and therefore relates to set
	 * cardEjectEngine in the specification to true by writing at cardEjectAddr.
	 */
	public void ejectCard() {
		cardEngine = false;
	}

	/**
	 * Get the data which is saved at cardInputAddr (specification) representing
	 * the last read card number.
	 * 
	 * @return The last read card number.
	 */
	public int getCardInput() {
		return cardInput;
	}

	/**
	 * Returns true if card is inserted, false otherwise.
	 * 
	 * @return true if card is inserted, false otherwise.
	 */
	public boolean isCardInsertEngine() {
		return cardEngine;
	}

	public boolean getCardReadStatus() {
		return cardReadStatus;
	}

	public boolean getCardInsertStatus() {
		return cardInsertStatus;
	}

	public boolean getCardEjectStatus() {
		return cardEjectStatus;
	}

	public boolean getCardScannerStatus() {
		return cardReadStatus && cardInsertStatus && cardEjectStatus;
	}

}