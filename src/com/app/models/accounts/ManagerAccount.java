package com.app.models.accounts;

public class ManagerAccount extends Account {

	public ManagerAccount(String nickname, String password) throws Exception {
		super();
		try {
			setNickname(nickname);
			changePasswordPermission = true;
			setPassword(password);
		} catch(Exception ex) {
			throw ex;
		}
	}

	@Override
	public AccountType getType() {
		return AccountType.MANAGER;
	}

}
