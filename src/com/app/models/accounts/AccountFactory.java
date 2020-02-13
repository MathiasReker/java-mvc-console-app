package com.app.models.accounts;

public class AccountFactory {
	
	public static Account createAccount(String nickname, String password, AccountType type) throws Exception {
		Account accountCreated = null;
		if (type.equals(AccountType.MANAGER)) {
			accountCreated = new ManagerAccount(nickname, password);
			
		}
		if (type.equals(AccountType.USER)) {
			accountCreated = new UserAccount(nickname, password);
			
		}
		
		return accountCreated;
	}

}
