package com.app.controllers;

import java.util.ArrayList;

import com.app.App;
import com.app.console.Console;
import com.app.models.accounts.AccountType;

public class Controller {

	private App app;
	private boolean runningApp;
	
	public Controller(App app) {
		this.app = app;
		runningApp = false;
	}
	
	public void runApp() {
		Console.print("Welcome to Console App!");
		runningApp = true;
		
		while (runningApp) {
			ArrayList<String> menu = generateMainMenu();
			String response = Console.displayMenu("Main Menu:", menu);
			
			switch(response) {
			
			case "Close":
				runningApp = false;
				Console.cleanConsole();
				break;
				
			case "Show All Notes":
				try {
					Console.printNoteList("All notes:", this.app.getAllNotes());
				} catch (Exception ex) {
					Console.print(ex.getMessage());
				}
				Console.cleanConsole();
				break;
				
			case "Show Public Notes":
				try {
					Console.printPublicNotes(this.app.getPublicNotes());
				} catch (Exception ex) {
					Console.print(ex.getMessage());
				}
				Console.cleanConsole();
				break;
				
			case "Show My Notes":
				try {
					Console.printNoteList("My notes:", this.app.getAccountNotes());
				} catch (Exception ex) {
					
				}
				Console.cleanConsole();
				break;
				
			case "Login":
				ArrayList<String> loginInputs = new ArrayList<>();
				loginInputs.add("Nickname");
				loginInputs.add("Password");
				ArrayList<String> loginCredentials = Console.displayForm("Login", loginInputs);
				try {
					this.app.login(loginCredentials.get(0), 
							loginCredentials.get(1));
				} catch (Exception ex) {
					Console.print(ex.getMessage());
				}
				Console.cleanConsole();
				break;
				
			case "Register":
			case "Register an user":
				ArrayList<String> registerInputs = new ArrayList<>();
				registerInputs.add("Nickname");
				registerInputs.add("Password");
				ArrayList<String> registerCredentials = Console.displayForm("Register", registerInputs);
				try {
					this.app.addAccount(registerCredentials.get(0), 
							registerCredentials.get(1), 
							AccountType.USER);
				} catch (Exception ex) {
					Console.print(ex.getMessage());
				}
				Console.cleanConsole();
				break;
				
			case "Register a manager":
				ArrayList<String> registerManagerInputs = new ArrayList<>();
				registerManagerInputs.add("Nickname");
				registerManagerInputs.add("Password");
				ArrayList<String> registerManagerCredentials = Console.displayForm("Register", registerManagerInputs);
				try {
					this.app.addAccount(registerManagerCredentials.get(0), 
							registerManagerCredentials.get(1), 
							AccountType.MANAGER);
				} catch (Exception ex) {
					Console.print(ex.getMessage());
				}
				Console.cleanConsole();
				break;
				
			case "Logout":
				this.app.logout();
				Console.cleanConsole();
				break;
			
			case "Create Public Note":
			case "Publish An Anonym Note":
				ArrayList<String> publicNoteInput = new ArrayList<>();
				publicNoteInput.add("Title");
				publicNoteInput.add("Body");
				ArrayList<String> publicNote = Console.displayForm("Create Public Note", publicNoteInput);
				this.app.addNote(publicNote.get(0), publicNote.get(1), true);
				Console.cleanConsole();
				break;
				
			case "Create Private Note":
				ArrayList<String> privateNoteInput = new ArrayList<>();
				privateNoteInput.add("Title");
				privateNoteInput.add("Body");
				ArrayList<String> privateNote = Console.displayForm("Create Public Note", privateNoteInput);
				this.app.addNote(privateNote.get(0), privateNote.get(1), false);
				Console.cleanConsole();
				break;
				
			case "":
				Console.print("Ops... an error was occured!");
				break;
			}
		}
	}
	
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
