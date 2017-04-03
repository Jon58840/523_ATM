package pm;

import scb.PN;
import scb.SCB;
import sm.CardScanner;
import sm.Monitor;

public class EjectCardPM {
	
	public static void ejectCard(CardScanner cs, Monitor m) {
		if(!m.isMonitorStatus()) {
			System.out.println("Monitor Fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		} else if (!cs.getCardReadStatus()) {
			System.out.println("Card Reader fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		}
		
		// TODO implement remaining PM
		
	}

}
