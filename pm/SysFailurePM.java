package pm;

import scb.SCB;
import sm.CardScanner;
import sm.Monitor;

public class SysFailurePM {

	public static void sysFailure(CardScanner cs, Monitor monitor) {
		System.out.println("System failure");

		// its somehow ironic that this will also be executed in case of a
		// monitor failure...
		monitor.showMessages("System failure. Please use another machine.");
		
		cs.ejectCard();
		
		// TODO what about card ejected status
		
		SCB.sysShutDown = true;
	}

}
