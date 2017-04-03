package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.CardScanner;
import sm.Monitor;

public class WelcomePM {

	public static void welcome(CardScanner cs, Monitor m, AccountDatabase db) {

		boolean cardInserted = false;
		while (!cardInserted) {
			if (m.isMonitorStatus() && cs.getCardScannerStatus()) {

				m.showMessages("Welcome!", "Please insert your card.");

				// could be moved to while condition but this way it looks like
				// in the specification
				cardInserted = cs.isCardInsertEngine();

			} else {

				if (!m.isMonitorStatus()) {
					System.out.println("Monitor fault.");
				} else if (!cs.getCardScannerStatus()) {
					System.out.println("Card scanner fault.");
				}

				SCB.setCurrentState(PN.SYSTEM_FAILURE);

			}

			/*
			 * this construction of polling and resetting is incredibly ugly and
			 * stupid but it is conform to the specification given in the
			 * reference solution.
			 */
			try {
				Thread.sleep(100); // to ensure this takes not 100% CPU
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int accountNumber = cs.getCardInput();
		
		if(db.getAccountStatus(accountNumber)) {
			// TODO wait for pin timer
			// TODO valid card status ??
			SCB.resetPinTrialTimes();
			SCB.setCurrentState(PN.CHECK_PIN);
		} else {
			// TODO valid card status ??
			SCB.setCurrentState(PN.EJECT_CARD);
		}
	}

}
