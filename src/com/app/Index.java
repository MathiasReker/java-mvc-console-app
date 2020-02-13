package com.app;

import com.app.controllers.Controller;
import com.app.models.accounts.AccountType;

/**
 * Index is used to configure the program and run it.
 * @author Joel Gorin
 * 
 */
public class Index {

	/**
	 * Run the program.
	 * @param args is the arguments or variables to set a specific 
	 * configuration when we running the program.
	 */
	public static void main(String[] args) {
		// Create app and set admin user.
		App app;
		
		try {
			app = new App();
			app.addAccount("admin", "pass", AccountType.MANAGER);
			
			Controller controller = new Controller(app);
			controller.runApp();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
