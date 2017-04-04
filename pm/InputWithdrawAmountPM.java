package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class InputWithdrawAmountPM {

	public static void inputWithdrawAmount(int currentAccountNumber, Monitor monitor, KeypadInterface keypad,
			AccountDatabase db, SysTimer sysClock) {

		boolean dataEntered = false;
		while (!dataEntered) {
			// Specification: replaced card scanner by keypad as it makes no
			// sense at all to check the scanner here but the keypad is used.
			if (!sysClock.timerRunDown() && monitor.isWorking() && keypad.isWorking()) {
				monitor.showMessages("Enter the amount you wish to withdraw ($20 ... $500)");
				dataEntered = keypad.isEnterButtonPressed();
			} else {
				if (sysClock.timerRunDown() && sysClock.isTimerOn()) {
					System.out.println("Operation time out.");
					SCB.timeOut = true;
					SCB.setCurrentState(PN.EJECT_CARD);
					return;
				} else if (!monitor.isWorking()) {
					System.out.println("Monitor fault.");
					SCB.setCurrentState(PN.SYSTEM_FAILURE);
					return;
				} else if (!keypad.isWorking()) {
					System.out.println("Keypad fault.");
					SCB.setCurrentState(PN.SYSTEM_FAILURE);
					return;
				}
			}
		}

		final int amountToWithdraw = keypad.getDataInput(true);

		if (20 <= amountToWithdraw
				&& amountToWithdraw <= db.getAccount(currentAccountNumber).getMaxAllowableWithdraw()) {
			SCB.isValidAmount = true;
			db.getAccount(currentAccountNumber).setCurrentWithdraw(amountToWithdraw);
			SCB.setCurrentState(PN.VERIFY_BALANCE);
		} else {
			SCB.isValidAmount = false;
			monitor.showMessages("Amount required is out of range.", "Do you want to enter a different amount?");

			// 3s delay
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (keypad.isEnterButtonPressed()) {
				SCB.setCurrentState(PN.INPUT_WITHDRAW_AMOUNT);
				sysClock.setTimer(10);
			} else if (keypad.isCancelButtonPressed()) {
				SCB.serviceCanceled = true;
				SCB.setCurrentState(PN.EJECT_CARD);
			} else {
				// this case is missing in the specification.
				monitor.showMessages("Canceled automatically.");
				SCB.serviceCanceled = true;
				SCB.setCurrentState(PN.EJECT_CARD);
			}
		}

	}

}
