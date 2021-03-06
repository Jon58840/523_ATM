package pm;

import scb.PN;
import scb.SCB;
import sm.CardScanner;
import sm.Monitor;

public class EjectCardPM {

	public static void ejectCard(CardScanner cs, Monitor monitor) {
		if (!monitor.isWorking()) {
			System.out.println("Monitor Fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		} else if (!cs.getCardReadStatus()) {
			System.out.println("Card Reader fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		}

		if (SCB.serviceCompleted) {
			monitor.showMessages("Please collect your card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (SCB.serviceCanceled) {
			// same case as before... could be merged
			monitor.showMessages("Please collect your card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (SCB.timeOut) {
			monitor.showMessages("Service time out.", "Please collect your card.");
			cs.ejectCard();
			SCB.timeOut = false;
			SCB.setCurrentState(PN.WELCOME);
		} else if (!SCB.isValidCard) {
			monitor.showMessages("Invalid Card.");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else if (!SCB.isValidPin) {
			monitor.showMessages("Invalid PIN");
			cs.ejectCard();
			SCB.setCurrentState(PN.WELCOME);
		} else {
			System.out.println("An exceptional fault was detected.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
		}

		// 2s delay Added (not in specification) to see the results and to
		// simulate time needed to eject card
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
