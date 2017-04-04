package scb;

import pm.CheckPinPM;
import pm.DisburseBillsPM;
import pm.EjectCardPM;
import pm.InputWithdrawAmountPM;
import pm.SysFailurePM;
import pm.VerifyBalancePM;
import pm.VerifyBillsAvailabiltyPM;
import pm.WelcomePM;
import sm.AccountDatabase;
import sm.CardScanner;
import sm.CashBank;
import sm.CashDisburser;
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
	public static boolean sysShutDown;

	// Constructor
	public static void initSCB() {
		currentState = PN.WELCOME;
		isValidCard = true;
		sysShutDown = false;
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

		CardScanner cardScanner = new CardScanner();
		Keypad keypad = new Keypad();

		AccountDatabase db = new AccountDatabase();

		CashBank cashBank = new CashBank();
		CashDisburser cashDisbursur = new CashDisburser();

		Gui gui = new Gui(cardScanner, keypad);
		Monitor monitor = new Monitor(gui);

		int currentAccountNumber = AccountDatabase.INVALID_ACCOUNT_NUMBER;

		while (!sysShutDown) {
			// systemDispatch TODO maybe move to separate function
			switch (SCB.currentState) {
			case WELCOME:
				currentAccountNumber = WelcomePM.welcome(cardScanner, monitor, db);
				break;
			case CHECK_PIN:
				CheckPinPM.checkPIN(currentAccountNumber, monitor, keypad, db);
				break;
			case INPUT_WITHDRAW_AMOUNT:
				InputWithdrawAmountPM.inputWithdrawAmount(currentAccountNumber, cardScanner, monitor, keypad, db);
				break;
			case VERIFY_BALANCE:
				VerifyBalancePM.verifyBalance(currentAccountNumber, monitor, keypad, db);
				break;
			case VERIFY_BILLS_AVAILABILTY:
				VerifyBillsAvailabiltyPM.verifyBillsAvailability(cardScanner, monitor, keypad, cashBank, db);
				break;
			case DISBURSE_BILLS:
				DisburseBillsPM.disburseBills(currentAccountNumber, cardScanner, monitor, keypad, cashBank,
						cashDisbursur, db);
				break;
			case EJECT_CARD:
				EjectCardPM.ejectCard(cardScanner, monitor);
				break;
			case SYSTEM_FAILURE:
				SysFailurePM.sysFailure(cardScanner, monitor);
				break;
			default:
				throw new IllegalStateException("current state " + currentState + "invalid");
			}
		}
	}

}