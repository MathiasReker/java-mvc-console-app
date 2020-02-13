package com.app.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.app.models.Note;

/**
 * The console is the principal mean to interact with the user. 
 * @author Joel Gorin
 */
public class Console {
	
	private static Scanner scanner;

	/**
	 * Print in console a text.
	 * @param text to print.
	 */
	public static void print(String text) {
		System.out.println(text);
	}

	/**
	 * Print in console a text inline.
	 * @param text to print.
	 */
	public static void printInline(String text) {
		System.out.print(text);
	}
	
	/**
	 * Display a menu in console and return the user response.
	 * @param menuTitle is the title of menu.
	 * @param options is a list of options to display.
	 * @return option selected by user.
	 */
	public static String displayMenu(String menuTitle, ArrayList<String> options) {
		Console.print(menuTitle);
		for (int i = 0; i < options.size(); i++) {
			Console.print("\t[" + i + "] " + options.get(i));
		}
	    try {
	    	scanner = new Scanner(System.in);
	    	int value = scanner.nextInt();
	    	
	    	while (value < 0 || value >= options.size()) {
	    		Console.print("Try again!");
				value = scanner.nextInt();
				scanner.close(); 
	    	}
			scanner.close(); 
			return options.get(value);
		} 
	    catch (Exception ex) {
	    	throw ex;
	    }
	}
	
	/**
	 * Display a form and return the user response.
	 * @param formTitle is the title of form.
	 * @param inputs is a list of form inputs.
	 * @return an ArrayList with user response. 
	 */
	public static ArrayList<String> displayForm(String formTitle, ArrayList<String> inputs) {
		Console.print(formTitle);
		ArrayList<String> responses = new ArrayList<>();
		try {
			for (String input : inputs) {
				Console.print("\t" + input + ": ");
				scanner = new Scanner(System.in);
		    	String response = scanner.nextLine();
				responses.add(response);
				scanner.close();
			}
			return responses;
		} 
	    catch (Exception ex) {
	    	throw ex;
	    }
	}
	
	/**
	 * Print a note list in console.
	 * @param listName is the name of list.
	 * @param notes is the list of notes.
	 */
	public static void printNoteList(String listName, ArrayList<Note> notes) {
		Console.print(listName.toUpperCase());
		
		if (notes.isEmpty()) {
			Console.print("\tSorry! This list is empty.");
		}
		else {
			ArrayList<String> headers = new ArrayList<String>();
			headers.add("Title");
			headers.add("Body");
			headers.add("By");
			headers.add("Is Public");
			
			ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
			
			for (Note note : notes) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(note.getTitle());
				row.add(note.getBody());
				row.add(note.getUser().getNickname());
				row.add("" + note.isPublic());
				content.add(row);
			}
	
			ConsoleTable consoleTable = new ConsoleTable(headers,content);
			consoleTable.printTable();
		}
	}
	
	/**
	 * Print a note list in console on public mode.
	 * @param listName is the name of list.
	 * @param notes is the list of notes.
	 */
	public static void printPublicNotes(ArrayList<Note> notes) {
		Console.print("PUBLIC NOTES: ");
		
		if (notes.isEmpty()) {
			Console.print("\tSorry! Anybody wasn't create public notes.");
		}
		else {
			ArrayList<String> headers = new ArrayList<String>();
			headers.add("Title");
			headers.add("Body");
			headers.add("By");
			
			ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
			
			for (Note note : notes) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(note.getTitle());
				row.add(note.getBody());
				row.add(note.getUser().getNickname());
				content.add(row);
			}
	
			ConsoleTable consoleTable = new ConsoleTable(headers,content);
			consoleTable.printTable();
		}
	}

	/**
	 * Function waiting for the user to press Enter.
	 */
	public static void waitEnter() {
		Console.print("[Press Enter]");
		scanner = new Scanner(System.in);
		String readLine = null;
		do {
			readLine = scanner.nextLine();
		} while (!readLine.equals(""));
		scanner.close();
	}
	
	/**
	 * Clear the console.
	 * @throws InterruptedException
	 */
	public static void cleanConsole()  {
		try {			
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException ex) {
			Console.print(ex.getMessage());
		}
	}

	/**
	 * Close instances of Console.
	 */
	public static void close() {
		scanner.close(); 
	}
}
