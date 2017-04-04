package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class VerifyBalancePM {

	public static int verifyBalance(int currentAccountNumber, Monitor monitor, KeypadInterface keypad,
			AccountDatabase db, SysTimer sysClock) {

		// skipped loop from specification as it either leads to deadlock or is
		// never executed.
		if (!monitor.isWorking()) {
			System.out.println("Monitor fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return -1;
		} else if (!keypad.isWorking()) {
			System.out.println("Keypad fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return -1;
		}

		// totally useless return parameter because never used later as saved in
		// db
		int amountToWithdraw = db.getAccount(currentAccountNumber).getCurrentWithdraw();

		// in specification this comparision is falsely set to equal instead of
		// smaller equal, which makes no sense at all.
		if (amountToWithdraw <= db.getAccount(currentAccountNumber).getBalance()) {
			SCB.isValidBalance = true;
			SCB.setCurrentState(PN.VERIFY_BILLS_AVAILABILTY);
		} else {
			SCB.isValidBalance = false;
			monitor.showMessages("Account balance is insufficient to withdraw.", "Retry a smaller amount?");
			
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

		return amountToWithdraw;
	}

}
