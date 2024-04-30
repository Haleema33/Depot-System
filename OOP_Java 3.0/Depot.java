import java.io.IOException;
import java.util.Scanner;

public class Depot {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in); 
		// Create a LoginSystem object to manage user login
		LoginSystem loginSystem = new LoginSystem(); 



		boolean loggedIn = false; // Set a flag to keep track of whether the user is logged in or not
		User currentUser = null; // Set the currentUser to null initially

		while (!loggedIn) { // Keep looping until the user is logged in
			System.out.println(
					"\nWelcome to our Depot System Application!" + " \nPlease enter user credentials to continue!");
			System.out.println("\nEnter your username:"); // Prompt the user to enter their username
			String username = scanner.nextLine(); // Read the username from user input
			System.out.println("Enter your password:"); // Prompt the user to enter their password
			String password = scanner.nextLine();// Read the password from user input

			currentUser = loginSystem.login(username,password); // Attempt to log in with the given username and
																	// password

			if (currentUser != null) { // If the login was successful
				loggedIn = true; // Set the loggedIn flag to true to break out of the while loop
			} else {
				System.out.println("Invalid username or password. Please try again.");
				// If the login was not successful, display an error message and prompt the user
				// to try again.
			}
		}

		if (currentUser.getRole().equals("manager")) { // If the current user is a manager
			System.out.println("     Welcome, 'Depot manager' to our system!\n"); // Display a welcome message for managers
			manager_menu managerMenu = new manager_menu(); // Assuming the class name is ManagerMenu
			managerMenu.display_menu();

		} else if (currentUser.getRole().equals("driver")) { // If the current user is a driver
			System.out.println("      Welcome, 'Driver' to our system!\n"); // Display a welcome message for drivers
			driver_menu driverMenu = new driver_menu();
			driverMenu.display_menu();

		} else {
			System.out.println("Unknown role: " + currentUser.getRole()); // If the role of the user is not recognized,
																			// display an error message
		}
		scanner.close(); // Close the scanner object to avoid resource leaks
	}
}
