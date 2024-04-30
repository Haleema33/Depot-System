import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {

	private ArrayList<User> users; // An ArrayList to store all the users loaded from the CSV file

	// Constructor that loads all the users from the CSV file
	public LoginSystem() {
		users = loadUsers();
	}

	// Method to authenticate the user by checking their username and password
	// Returns the User object if the login is successful, otherwise returns null
	public User login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	// Private method to load all the users from the CSV file and return an
	// ArrayList
	private ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<>(); // ArrayList to store all the users

		try {
			File file = new File("users.csv"); // Open the CSV file
			Scanner scanner = new Scanner(file); // Create a Scanner object to read the file
			scanner.nextLine(); // Skip the header line

			// Loop through each line of the CSV file and create a User object for each row
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(","); // Split the line into an array of strings
				String username = line[0]; // First element is the username
				String password = line[1]; // Second element is the password
				int id = Integer.parseInt(line[2]); // Third element is the ID, which needs to be converted to an
				// integer
				String role = line[3]; // Fourth element is the role
				String name = line[4]; // Fifth element is the name
				String address = line[5]; // Sixth element is the address
				String phone = line[6]; // Seventh element is the phone number
				User user = new User(username, password, id, role, name, address, phone); // Create a User object with
																							// the parsed data
				users.add(user); // Add the User object to the ArrayList
			}

			scanner.close(); // Close the scanner
		} catch (FileNotFoundException e) { // If the file is not found, catch the FileNotFoundException and print an
			// error message
			System.out.println("Error: File not found");
		}

		return users; // Return the ArrayList of User objects
	}
}
