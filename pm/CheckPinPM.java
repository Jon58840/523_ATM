package pm;

import scb.PN;
import scb.SCB;
import sm.AccountDatabase;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class CheckPinPM {

	public static void checkPIN(int currentAccountNumber, Monitor monitor, KeypadInterface keypad, AccountDatabase db,
			SysTimer sysClock) {

		boolean dataEntered = false;
		while (!dataEntered) {
			if (!sysClock.timerRunDown() && monitor.isWorking() && keypad.isWorking()) {
				monitor.showMessages("Enter your PIN.");
				dataEntered = keypad.isEnterButtonPressed();
			} else {
				if (sysClock.timerRunDown() && sysClock.isTimerOn()) {
					System.out.println("Operation time out.");
					SCB.timeOut = true; // added (not in specification) 
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

		final int pin = keypad.getDataInput(true);
		if (pin == db.getAccount(currentAccountNumber).getPIN()) {
			SCB.isValidPin = true;
			sysClock.setTimer(10); // set timer for input withdraw amount
			SCB.setCurrentState(PN.INPUT_WITHDRAW_AMOUNT);
		} else {
			SCB.isValidPin = false;
			SCB.decreasePinTrialTimes();
			if (SCB.getPinTrialTimes() > 0) {
				monitor.showMessages("Wrong PIN.", "Do you want to try again?");

				// 3s delay
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (keypad.isEnterButtonPressed()) {
					SCB.setCurrentState(PN.CHECK_PIN); // retry pin
					sysClock.setTimer(10); // missing in specification
				} else if (keypad.isCancelButtonPressed()) {
					SCB.serviceCanceled = true;
					SCB.setCurrentState(PN.EJECT_CARD);
				} else {
					monitor.showMessages("Invalid PIN.");
					SCB.isValidPin = false;
					SCB.setCurrentState(PN.EJECT_CARD);
				}
			} else {
				// TODO not mentioned in specification
				// but returning the Card is not the best id...
			}
		}

	}

}
