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
import sm.SysClock;

//SENG 523
//ATM

public class SCB {

	public static final int START_AMOUNT_OF_CASH_IN_ATM = 10000; // dollar

	private static PN currentState;
	private static int pinTrialTimes;

	// scb status (TODO remove unused status)
	public static boolean serviceCompleted;
	public static boolean serviceCanceled;
	public static boolean timeOut;
	public static boolean isValidCard;
	public static boolean isValidPin;
	public static boolean isValidAmount;
	public static boolean isValidBalance;
	public static boolean isBillsAvailable;
	public static boolean isBillsDisbursed;
	public static boolean sysShutDown;

	/**
	 * Initializes the system control block. This function has to be called
	 * before the ATM starts.
	 */
	private static void initSCB() {
		currentState = PN.WELCOME;
		serviceCompleted = false;
		serviceCanceled = false;
		timeOut = false;
		isValidCard = true;
		isValidPin = false;
		isBillsAvailable = false;
		isBillsDisbursed = false;
		sysShutDown = false;
		resetPinTrialTimes();
	}

	/**
	 * Decreases the number of remaining pin trials of the user by one.
	 */
	public static void decreasePinTrialTimes() {
		pinTrialTimes--;
	}

	/**
	 * Resets the number of pin trials to the initial value (3)
	 */
	public static void resetPinTrialTimes() {
		pinTrialTimes = 3;
	}

	/**
	 * Returns the number of remaining pin trials of the user.
	 * 
	 * @return The number of remaining pin trials
	 */
	public static int getPinTrialTimes() {
		return pinTrialTimes;
	}

	/**
	 * Sets the current state of the system to the given one.
	 * 
	 * @param currentState
	 *            The new system state.
	 */
	public static void setCurrentState(PN currentState) {
		SCB.currentState = currentState;
	}

	public static void main(String[] args) {

		/*
		 * Prepare system
		 */
		initSCB();

		CardScanner cardScanner = new CardScanner();
		Keypad keypad = new Keypad();

		AccountDatabase db = new AccountDatabase();

		CashDisburser cashDisburser = new CashDisburser();
		CashBank cashBank = new CashBank(START_AMOUNT_OF_CASH_IN_ATM, cashDisburser);

		Gui gui = new Gui(cardScanner, keypad, cashDisburser);
		Monitor monitor = new Monitor(gui);

		SysClock systemClock = new SysClock();
		systemClock.start();

		int currentAccountNumber = AccountDatabase.INVALID_ACCOUNT_NUMBER;
		
		/*
		 * Start system loop (dispatch)
		 */
		while (!sysShutDown) {
			// systemDispatch
			switch (SCB.currentState) {
			case WELCOME:
				currentAccountNumber = WelcomePM.welcome(cardScanner, monitor, db, systemClock);
				break;
			case CHECK_PIN:
				CheckPinPM.checkPIN(currentAccountNumber, monitor, keypad, db, systemClock);
				break;
			case INPUT_WITHDRAW_AMOUNT:
				InputWithdrawAmountPM.inputWithdrawAmount(currentAccountNumber, monitor, keypad, db, systemClock);
				break;
			case VERIFY_BALANCE:
				VerifyBalancePM.verifyBalance(currentAccountNumber, monitor, keypad, db, systemClock);
				break;
			case VERIFY_BILLS_AVAILABILTY:
				VerifyBillsAvailabiltyPM.verifyBillsAvailability(currentAccountNumber, monitor, keypad, cashBank, db,
						systemClock);
				break;
			case DISBURSE_BILLS:
				DisburseBillsPM.disburseBills(currentAccountNumber, cardScanner, monitor, keypad, cashBank,
						cashDisburser, db, systemClock);
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