package com.app.models.accounts;

import com.app.utils.Crypto;

public abstract class Account implements IAccount {
	
	private static int accountCounter = 0;
	protected int id;
	protected String nickname;
	protected String password;
	protected boolean changePasswordPermission;
	
	
	public Account() {
		generateID();
		accountCounter++;
		this.nickname = null;
		this.password = null;
		this.changePasswordPermission = true;
	}
	
	private void generateID() {
		this.id = accountCounter;
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public String getNickname() {
		return this.nickname;
	}

	@Override
	public void setNickname(String nickname) throws Exception {
		if (nickname == null) {
			throw new Exception("The nickname can't be null.");
		}
		else {
			this.nickname = nickname;
		}
	}

	@Override
	public void setPassword(String password) throws Exception {
		if (password == null) {
			throw new Exception("The password can't be null.");
			
		}
		else if (!changePasswordPermission) {
			throw new Exception("Permission to change password is denied.");
		}
		else {
			this.password = Crypto.encrypt(password);
			changePasswordPermission = false;
		}
	}
	
	@Override
	public void changePassword(String oldPassword, String newPassword) throws Exception {
		if (this.password.equals(oldPassword)) {
			this.changePasswordPermission = true;
			setPassword(newPassword);
		} else {
			throw new Exception("The password is not same to old password.");
		}
			
	}

	@Override
	public boolean validate(String password) throws Exception {
		if (this.password.equals(Crypto.encrypt(password))) {
			return true;
		}
		else {
			throw new Exception("Is the wrong password.");
		}
	}

	@Override
	public abstract AccountType getType();

}
