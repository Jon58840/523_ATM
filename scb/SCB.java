package scb;
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
		
		CardScanner cs = new CardScanner();
		Keypad keypad = new Keypad();
		
		AccountDatabase db = new AccountDatabase();
		
		Gui gui = new Gui(cs, keypad);
		Monitor m = new Monitor(gui);
		
		boolean running = true;
		while(running) {
			// systemDispatch TODO maybe move to separate function
			switch (SCB.currentState) {
			case WELCOME:
				WelcomePM.welcome(cs, m, db);
				break;

			default:
				throw new IllegalStateException("current state " + currentState + "invalid");
			}
		}
	}
	

}