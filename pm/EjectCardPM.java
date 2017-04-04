package pm;

import scb.PN;
import scb.SCB;
import sm.CardScanner;
import sm.Monitor;

public class EjectCardPM {

	public static void ejectCard(CardScanner cs, Monitor m) {
		if (!m.isMonitorStatus()) {
			System.out.println("Monitor Fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		} else if (!cs.getCardReadStatus()) {
			System.out.println("Card Reader fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		}

		if (SCB.serviceCompleted) {
			m.showMessages("Please collect your card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (SCB.serviceCanceled) {
			// same case as before... could be merged
			m.showMessages("Please collect your card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (SCB.timeOut) {
			m.showMessages("Service time out.", "Please collect your card.");
			cs.ejectCard();
			SCB.timeOut = false;
			SCB.setCurrentState(PN.WELCOME);
		} else if (!SCB.isValidCard) {
			m.showMessages("Invalid Card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (!SCB.isValidPin) {
			m.showMessages("Invalid PIN");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else {
			System.out.println("An exceptional fault was detected.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
		}

	}

}
