package pm;

import scb.PN;
import scb.SCB;
import sm.Account;
import sm.AccountDatabase;
import sm.CardScanner;
import sm.CashBank;
import sm.CashDisburser;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class DisburseBillsPM {

	public static void disburseBills(int accountNumber, CardScanner cd, Monitor monitor, KeypadInterface keypad,
			CashBank cashBank, CashDisburser disburser, AccountDatabase db, SysTimer sysClock) {

		if (!monitor.isWorking()) {
			System.out.println("Monitor fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		} else if (!cashBank.isWorking()) {
			System.out.println("Bills storage fault.");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
			return;
		}

		if (disburser.isWorking()) {
			monitor.showMessages("Please collect money, thank you.");
			final int amountToWithdraw = db.getAccount(accountNumber).getCurrentWithdraw();

			cashBank.decreaseCashByAmount(amountToWithdraw);
			final Account acc = db.getAccount(accountNumber);
			acc.setBalance(acc.getBalance() - amountToWithdraw);

			disburser.disburse();
			SCB.isBillsDisbursed = true;
			SCB.serviceCompleted = true;
			SCB.setCurrentState(PN.EJECT_CARD);

		} else {
			SCB.isBillsDisbursed = false;
			System.out.println("Bills disburser fault");
			SCB.setCurrentState(PN.SYSTEM_FAILURE);
		}
	}

}
