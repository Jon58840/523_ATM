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
		populateDatabase();
	}

	private void populateDatabase() {
		int accNo = 1234;
		int idx = 0;
		accountsArray[idx] = new Account(accNo, true, 4321, "John Doe", 100, 40, 0);
		accountMapping.put(accNo, idx);
		
		accNo = 10;
		idx = 1;
		accountsArray[idx] = new Account(accNo, true, 2222, "asdi awu", 1000, 80, 0);
		accountMapping.put(accNo, idx);
		
		// TODO more
	}

	public boolean getAccountStatus(int accountNumber) {
		if (accountMapping.containsKey(accountNumber)) {
			return accountsArray[accountMapping.get(accountNumber)].getAccountStatus();
		}
		return false;
	}
	
	public Account getAccount(int accountNumber) {
		if (accountMapping.containsKey(accountNumber)) {
			return accountsArray[accountMapping.get(accountNumber)];
		}
		throw new IllegalArgumentException(accountNumber +" does not belong to any account");
	}
}