package scb;

import pm.CheckPinPM;
import pm.EjectCardPM;
import pm.InputWithdrawAmountPM;
import pm.WelcomePM;
import sm.AccountDatabase;
import sm.CardScanner;
import sm.Gui;
import sm.Keypad;
import sm.Monitor;

//SENG 523
//ATM

public class SCB {

	private static PN currentState;
	private static PN lastState; // TODO necessary??
	private static int pinTrialTimes;

	// scb status
	public static boolean isValidCard;

	// Constructor
	public static void initSCB() {
		currentState = PN.WELCOME;
		resetPinTrialTimes();
	}

	public static void decreasePinTrialTimes() {
		pinTrialTimes--;
	}

	public static void resetPinTrialTimes() {
		pinTrialTimes = 3;
	}

	public static int getPinTrialTimes() {
		return pinTrialTimes;
	}

	public static PN getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(PN currentState) {
		SCB.currentState = currentState;
	}

	public static void main(String[] args) {

		initSCB();

		CardScanner cs = new CardScanner();
		Keypad keypad = new Keypad();

		AccountDatabase db = new AccountDatabase();

		Gui gui = new Gui(cs, keypad);
		Monitor m = new Monitor(gui);

		int currentAccountNumber = AccountDatabase.INVALID_ACCOUNT_NUMBER;
		int amountToWithdraw = 0;

		boolean running = true;
		while (running) {
			// systemDispatch TODO maybe move to separate function
			switch (SCB.currentState) {
			case WELCOME:
				currentAccountNumber = WelcomePM.welcome(cs, m, db);
				break;
			case CHECK_PIN:
				CheckPinPM.checkPIN(currentAccountNumber, m, keypad, db);
				break;
			case INPUT_WITHDRAW_AMOUNT:
				InputWithdrawAmountPM.inputWithdrawAmount(currentAccountNumber, cs, m, keypad, db);
				break;
			case VERIFY_BALANCE:
				amountToWithdraw = VerifyBalancePM.verifyBalance(currentAccountNumber, m, keypad, db);
				break;
			case VERIFY_BILLS_AVAILABILTY:
				break;
			case DISBURSE_BILLS:
				break;
			case EJECT_CARD:
				EjectCardPM.ejectCard(cs, m);
				break;
			case SYSTEM_FAILURE:
				break;
			default:
				throw new IllegalStateException("current state " + currentState + "invalid");
			}
		}
	}

}