package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.CardScanner;
import sm.Monitor;
import sm.SysTimer;

public class WelcomePM {

	public static int welcome(CardScanner cs, Monitor monitor, AccountDatabase db, SysTimer systemClock) {

		boolean cardInserted = false;
		while (!cardInserted) {
			if (monitor.isWorking() && cs.getCardScannerStatus()) {

				monitor.showMessages("Welcome!", "Please insert your card.");

				// could be moved to while condition but this way it looks like
				// in the specification
				cardInserted = cs.isCardInsertEngine();

			} else {

				if (!monitor.isWorking()) {
					System.out.println("Monitor fault.");
				} else if (!cs.getCardScannerStatus()) {
					System.out.println("Card scanner fault.");
				}

				SCB.setCurrentState(PN.SYSTEM_FAILURE);
				return AccountDatabase.INVALID_ACCOUNT_NUMBER;

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

		if (db.getAccountStatus(accountNumber)) {
			systemClock.setTimer(10);
			SCB.isValidCard = true;
			SCB.resetPinTrialTimes();
			SCB.setCurrentState(PN.CHECK_PIN);
		} else {
			SCB.isValidCard = false;
			SCB.setCurrentState(PN.EJECT_CARD);
		}

		return accountNumber;
	}

}
