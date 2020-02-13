package com.app.controllers;

import java.util.ArrayList;

import com.app.App;
import com.app.console.Console;
import com.app.models.accounts.AccountType;

/**
 * The controller is the way to connect the application to the console.
 * @author Joel Gorin
 */
public class Controller {

	private App app;
	private boolean runningApp;
	private ArrayList<String> accountParams;
	private ArrayList<String> noteParams;
	
	/**
	 * Controller constructor.
	 * @param app is the aplication.
	 */
	public Controller(App app) {
		this.app = app;
		this.runningApp = false;

		// Initialize account parameters
		this.accountParams = new ArrayList<>();
		this.accountParams.add("Nickname");
		this.accountParams.add("Password");

		// Initialize note parameters
		this.noteParams = new ArrayList<>();
		this.noteParams.add("Title");
		this.noteParams.add("Body");
	}
	
	/**
	 * Run the application.
	 */
	public void runApp() {
		Console.print("Welcome to Console App!");
		runningApp = true;
		
		while (runningApp) {
			Console.cleanConsole();
			ArrayList<String> menu = generateMainMenu();
			String response = Console.displayMenu("Main Menu:", menu);
			
			switch(response) {
				case "Close":
					runningApp = false;
					Console.close();
					break;
					
				case "Show All Notes":
					showAllNotes();
					break;
					
				case "Show Public Notes":
					showPublicNotes();
					break;
					
				case "Show My Notes":
					showAccountNotes();
					break;
					
				case "Login":
					login();
					break;
					
				case "Register":
				case "Register an user":
					register(AccountType.USER);
					break;
					
				case "Register a manager":
					register(AccountType.MANAGER);
					break;
					
				case "Logout":
					this.app.logout();
					break;
				
				case "Create Public Note":
				case "Publish An Anonym Note":
					createNote(true);
					break;
					
				case "Create Private Note":
					createNote(false);
					break;
					
				case "":
					Console.print("Ops... an error was occured!");
					Console.waitEnter();
					break;
			}
		}
	}

	/**
	 * Show a table that contains all notes.
	 */
	private void showAllNotes() {
		try {
			Console.printNoteList("All notes:", this.app.getAllNotes());
		} catch (Exception ex) {
			Console.print(ex.getMessage());
		}
		Console.waitEnter();
	}

	/**
	 * Show a table that contains all public notes of all users.
	 */
	private void showPublicNotes() {
		try {
			Console.printPublicNotes(this.app.getPublicNotes());
		} catch (Exception ex) {
			Console.print(ex.getMessage());
		}
		Console.waitEnter();
	}

	/**
	 * Show a table that contains all notes of one user.
	 */
	private void showAccountNotes() {
		try {
			Console.printNoteList("My notes:", this.app.getAccountNotes());
		} catch (Exception ex) {
			Console.print(ex.getMessage());
		}
		Console.waitEnter();
	}

	/**
	 * User login.
	 */
	private void login() {
		ArrayList<String> credentials = Console.displayForm("Login", this.accountParams);
		try {
			this.app.login(credentials.get(0), credentials.get(1));
		} catch (Exception ex) {
			Console.print(ex.getMessage());
		}
		Console.waitEnter();
	}

	/**
	 * User registration.
	 */
	private void register(AccountType userType) {
		ArrayList<String> credentials = Console.displayForm("Register", this.accountParams);
		try {
			this.app.addAccount(credentials.get(0), credentials.get(1), userType);
		} catch (Exception ex) {
			Console.print(ex.getMessage());
		}
	}

	/**
	 * Display create note form.
	 * @param isPublic determine if the note is public or private.
	 */
	private void createNote(boolean isPublic) {
		ArrayList<String> publicNote = Console.displayForm("Create Note", this.noteParams);
		this.app.addNote(publicNote.get(0), publicNote.get(1), isPublic);
	}

	/**
	 * Generate menu to display in console depending if 
	 * user is logged or if the user is manager.
	 * @return an Arraylist with menu options.
	 */
	public ArrayList<String> generateMainMenu() {
		ArrayList<String> mainMenu = new ArrayList<>();
		
		if (this.app.sessionIsOpen()) {
			if (this.app.isManagerSession()) {
				mainMenu.add("Register an user");
				mainMenu.add("Register a manager");
				mainMenu.add("Show All Notes");
			}
			mainMenu.add("Show Public Notes");
			mainMenu.add("Show My Notes");
			mainMenu.add("Create Public Note");
			mainMenu.add("Create Private Note");
			mainMenu.add("Logout");
		}
		else {
			mainMenu.add("Show Public Notes");
			mainMenu.add("Publish An Anonym Note");
			mainMenu.add("Login");
			mainMenu.add("Register");
		}
		mainMenu.add("Close");
		
		return mainMenu;
	}
}
