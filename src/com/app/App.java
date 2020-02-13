package com.app;

import java.util.ArrayList;

import com.app.models.*;
import com.app.models.accounts.*;

/**
 * Main class of program. 
 * @author Joel Gorin
 */
public class App {
	
	private ArrayList<Account> accounts;
	private ArrayList<Note> notes;
	private Account currentAccount;
	private Account anonymAccount;

	public App() throws Exception {
		accounts = new ArrayList<>();
		notes = new ArrayList<>();
		currentAccount = null;
		anonymAccount = new UserAccount("Anonym", "");
	}
	
	/**
	 * Create and add a new account to list of accounts.
	 * @param nickname of account.
	 * @param password of account.
	 * @param type of account from AccountType.
	 * @see AccountType
	 */
	public void addAccount(String nickname, String password, AccountType type) {
		Account newAccount;
		try {
			newAccount = AccountFactory.createAccount(nickname, password, type);
			accounts.add(newAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	private Account getAccountByID(int id) {
		for(Account account : this.accounts) {
			if (account.getID() == id) {
				return account;
			}
		}
		return null;
	}*/
	
	private Account getAccountByNickname(String nickname) {
		for(Account account : this.accounts) {
			if (nickname.contentEquals(account.getNickname())) {
				return account;
			}
		}
		return null;
	}
	
	public void login(String nickname, String password) throws Exception {
		if (this.currentAccount == null) {
			Account account = getAccountByNickname(nickname);
			try {
				if (account != null && account.validate(password)) {
					this.currentAccount = account;
				}
			}
			catch (Exception ex) {
				throw ex;
			}
		} 
		else {
			throw new Exception("A session it's already open.");
		}
	}
	
	public void logout() {
		currentAccount = null;
	}
	
	public void addNote(String title, String body, boolean isPublic) {
		Note newNote;
		if (this.currentAccount != null) {
			newNote = new Note(this.currentAccount);
		}
		else {
			newNote = new Note(this.anonymAccount);
		}
		newNote.setTitle(title);
		newNote.setBody(body);
		newNote.setPublic(isPublic);
		// Add note to array
		this.notes.add(newNote);
	}
	
	public ArrayList<Note> getAllNotes() throws Exception {
		if (this.currentAccount == null) {
			throw new Exception("There must be at least one session open.");
		}
		if (this.currentAccount.getType().equals(AccountType.MANAGER)) {
			return this.notes;
		}
		else {
			throw new Exception("Only manager user can get all notes.");
		}
	} 

	public ArrayList<Note> getPublicNotes() {
		ArrayList<Note> publicNotes = new ArrayList<>();
		for (Note note : this.notes) {
			if (note.isPublic()) {
				publicNotes.add(note);
			}
		}
		return publicNotes;
	}
	
	public ArrayList<Note> getAccountNotes() throws Exception {
		if (this.currentAccount == null) {
			throw new Exception("There must be at least one session open.");
		}
		else {
			ArrayList<Note> accountNotes = new ArrayList<>();
			for (Note note : this.notes) {
				if (this.currentAccount.equals(note.getUser())) {
					accountNotes.add(note);
				}
			}
			return accountNotes;
		}
	}

	public boolean sessionIsOpen() {
		return (this.currentAccount != null);
	}
	
	public boolean isManagerSession() {
		if (sessionIsOpen()) {
			return (this.currentAccount.getType().equals(AccountType.MANAGER));
		}
		else {
			return false;
		}
	}
	
}
