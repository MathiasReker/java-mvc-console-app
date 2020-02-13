package com.app.models;

import com.app.models.accounts.*;

public class Note {
	
	private static int noteCounter = 0;
	
	private int id;
	private Account user;
	private String title;
	private String body;
	private boolean isPublic;
	
	public Note(Account user) {
		generateID();
		setUser(user);
	}
	
	public static int getNumberOfNotes() {
		return noteCounter;
	}

	private void generateID() {
		this.id = noteCounter;
	}
	
	public int getID() {
		return this.id;
	}

	public Account getUser() {
		return user;
	}

	private void setUser(Account user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
}
