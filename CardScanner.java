//SENG 523
//ATM


public class CardScanner implements CardScannerInterface
{
	//Constructor
	public CardScanner()
	{
		this.cardEjectStatus = true;
		this.cardEjectStatus = true;
		this.cardEjectStatus = true;
	}

	public void CardInput(int cardNumber) {
		if ( cardNumber >= 0 && cardNumber <= 1000000 ) {
			//TODO call check pin here. wherever it exists? should we even be calling checkpin?
		}
		
	}
	
	public void CardInsert() {
		//TODO call check pin here. wherever it exists? should we even be calling checkpin?
		
	}
	
	public void CardEject() {
		//TODO call check pin here. wherever it exists? should we even be calling checkpin?
		
	}
	
	//True = Normal/working
	//False = Faulty
	private boolean cardReadStatus;
	private boolean cardInsertStatus;
	private boolean cardEjectStatus;
}