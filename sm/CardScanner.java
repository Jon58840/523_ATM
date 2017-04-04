package sm;
//SENG 523
//ATM


public class CardScanner implements CardScannerInterface
{
	
	private int cardInput;

	/** 
	 * true card is inserted, false card not read yet
	 */
	private boolean cardInsertEngine;
	
	//Constructor
	public CardScanner()
	{
		this.cardReadStatus = true;
		this.cardInsertStatus = true;
		this.cardEjectStatus = true;
		
		this.cardInsertEngine = false;
	}

	public void inputCard(int cardNumber) {
		if ( cardNumber >= 0 && cardNumber <= 1000000 ) {
			//TODO call check pin here. wherever it exists? should we even be calling checkpin?
			cardInput = cardNumber;
			cardInsertEngine = true;
		} else {
			throw new IllegalArgumentException(cardNumber + " is no valid card");
		}
		
	}
	
	public int getCardInput() {
		return cardInput;
	}
	
	public boolean isCardInsertEngine() {
		return cardInsertEngine;
	}

	public void CardInsert() {
		//TODO call check pin here. wherever it exists? should we even be calling checkpin?
		
	}
	
	public void ejectCard() {
		//TODO call check pin here. wherever it exists? should we even be calling checkpin?
		
	}
	
	public boolean getCardReadStatus() {
		return cardReadStatus;
	}
	
	public boolean getCardInsertStatus () {
		return cardInsertStatus;
	}
	
	public boolean getCardEjectStatus() {
		return cardEjectStatus;
	}
	
	public boolean getCardScannerStatus() {
		return cardReadStatus && cardInsertStatus && cardEjectStatus;
	}
	
	//True = Normal/working
	//False = Faulty
	private boolean cardReadStatus;
	private boolean cardInsertStatus;
	private boolean cardEjectStatus;
}