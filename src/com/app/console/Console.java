package com.app.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.app.models.Note;

public class Console {
	
	private static Scanner scanner;

	public static void print(String text) {
		System.out.println(text);
	}

	public static void printInline(String text) {
		System.out.print(text);
	}
	
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
	    	}
	    		    		
			return options.get(value);
		} 
	    catch (Exception ex) {
	    	throw ex;
	    }
	}
	
	public static ArrayList<String> displayForm(String formTitle, ArrayList<String> inputs) {
		Console.print(formTitle);
		ArrayList<String> responses = new ArrayList<>();
		try {
			for (String input : inputs) {
				Console.print("\t" + input + ": ");
				scanner = new Scanner(System.in);
		    	String response = scanner.nextLine();
		    	responses.add(response);
			}
			return responses;
		} 
	    catch (Exception ex) {
	    	throw ex;
	    }
	}
	
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
	
	public static void cleanConsole() {
		try {

	        if (System.getProperty("os.name").contains("Windows"))

	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

	        else

	            Runtime.getRuntime().exec("clear");

	    } catch (IOException | InterruptedException ex) {}
	}
}
