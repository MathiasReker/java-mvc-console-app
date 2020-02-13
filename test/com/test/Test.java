package com.test;

import com.app.App;
import com.app.console.Console;
import com.app.models.accounts.AccountType;

public class Test {

	public static void main(String[] args) {
		
		App app;
		
		try {
			app = new App();
			
			// ACCOUNT TEST
			app.addAccount("admin", "pass", AccountType.MANAGER);
			app.addAccount("Nick", "123", AccountType.USER);
			app.addAccount("Nath", "456", AccountType.USER);
			
			// NOTES TESTS
			try {
				app.login("Nick", "123");
				app.addNote("First Note", "This is a public note", true);
				app.addNote("Second Note", "This is a public note with a lot of content in the body.", true);
				app.addNote("Third Note", "This is a private note", false);
				app.addNote("Fourth Note", "This is a private note", false);
				app.logout();
				
				app.login("Nath", "456");
				app.addNote("First Note", "This is a public note", true);
				app.addNote("Second Note", "This is a private note with a lot of content in the body.", false);
				app.addNote("Third Note", "This is a private note", false);
				app.addNote("Fourth Note", "This is a private note", false);
				app.logout();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Console.printNoteList("Public notes", app.getPublicNotes());
			
			try {
				app.login("Nath", "456");
				Console.printNoteList("My notes", app.getAccountNotes());
				app.logout();
			} 
			catch (Exception ex) {
				Console.print(ex.getMessage());
			}
			
			try {
				app.login("admin", "pass");
				Console.printNoteList("All notes", app.getAllNotes());
				app.logout();
			} 
			catch (Exception ex) {
				Console.print(ex.getMessage());
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

	}

}
