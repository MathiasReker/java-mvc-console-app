package com.app.models.accounts;

public interface IAccount {
	int getID();
	String getNickname();
	void setNickname(String nickname) throws Exception;
	void setPassword(String password) throws Exception;
	void changePassword(String oldPassword, String newPassword) throws Exception;
	boolean validate(String password) throws Exception;
	AccountType getType();
}
