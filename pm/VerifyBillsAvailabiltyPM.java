package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.CashBank;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class VerifyBillsAvailabiltyPM {

	public static void verifyBillsAvailability(int accountNumber, Monitor monitor, KeypadInterface keypad, CashBank cb,
			AccountDatabase db, SysTimer sysClock) {
		
		// Specification: replaced card scanner by keypad as it makes no
		// sense at all to check the scanner here but the keypad is used.
		if (!monitor.isWorking()) {
			System.out.println("Monitor fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		} else if (!keypad.isWorking()) {
			System.out.println("Keypad fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		}
		
		if(cb.isWorking()) {
			
			final int amountToWithdraw = db.getAccount(accountNumber).getCurrentWithdraw();
			if(cb.availableAmountOfCash() >= amountToWithdraw) {
				SCB.isBillsAvailable = true;// TODO status needed ?!
				SCB.setCurrentState(PN.DISBURSE_BILLS);
			} else {
				SCB.isBillsAvailable = false;
				monitor.showMessages("No sufficient cash available in this machine.", "Retry a new amount?");
				
				// 3s delay
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (keypad.isEnterButtonPressed()) {
					sysClock.setTimer(10);
					SCB.setCurrentState(PN.INPUT_WITHDRAW_AMOUNT); // retry
				} else {
					// guard not needed as we would create a deadlock...
					SCB.serviceCanceled = true;
					SCB.setCurrentState(PN.EJECT_CARD);
				}
			}
		} else {
			// TODO status not needed?!
			System.out.println("Bills storage faulty");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
		}
		
	}

}
