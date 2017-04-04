package pm;

import sm.AccountDatabase;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class CheckPinPM {

	public static void checkPIN(int currentAccountNumber, Monitor m, KeypadInterface keypad,
			AccountDatabase db, SysTimer sysClock) {
		
		boolean dataEntered = false;
		while(!dataEntered) {
			if(!sysClock.timerRunDown() && m.isMonitorStatus() && keypad.isKeypadStatus()) {
				m.showMessages("Enter your PIN.");
				dataEntered = keypad.isEnterButtonPressed();
			} else {
				// TODO else
			}
		}
		
		// TODO implement remaining PM
	}

}
