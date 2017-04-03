package sm;

import java.util.HashMap;
import java.util.Map;

//SENG 523
//ATM

public class AccountDatabase {
	// Constants
	private static final int MAX_ACCOUNTS = 5;
	public static final int INVALID_ACCOUNT_NUMBER = -1;

	// Member Variables
	private Account[] accountsArray;
	private Map<Integer, Integer> accountMapping;

	// Constructor
	public AccountDatabase() {
		this.accountMapping = new HashMap<>();
		this.accountsArray = new Account[MAX_ACCOUNTS];
	}

	private void populateDatabase() {
		// TODO
	}

	public boolean getAccountStatus(int accountNumber) {
		if (accountMapping.containsKey(accountNumber)) {
			return accountsArray[accountMapping.get(accountNumber)].getAccountStatus();
		}
		return false;
	}
}