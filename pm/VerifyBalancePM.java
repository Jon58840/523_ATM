package pm;

import sm.AccountDatabase;
import sm.KeypadInterface;
import sm.Monitor;
import sm.SysTimer;

public class VerifyBalancePM {

	public static int verifyBalance(int currentAccountNumber, Monitor m, KeypadInterface keypad, AccountDatabase db,
			SysTimer sysClock) {
		int amountToWithdraw = 0; // totally useless return parameter because
									// never used later as saved in db...

		// TODO implement PM

		return amountToWithdraw;
	}

}
