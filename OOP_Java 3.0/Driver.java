import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Driver {


	public static void viewDrivers() throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader("users.csv"));
		try {
			String row;
			boolean isHeader = true;
			String headerFormat = "%-5s %-6s %-20s %-30s %-12s %n";
			String dataFormat = "%-5d %-6s %-20s %-30s %-12s %n";

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (isHeader) {
					System.out.format(headerFormat, "ID", "Role", "Name", "Address", "Phone");
					isHeader = false;
				} else if (data[3].equals("driver")) {
					// Assuming the existence of a User class with the following constructor
					User driver = new User(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5],
							data[6]);
					System.out.format(dataFormat, driver.getId(),
							driver.getRole(), driver.getName(), driver.getAddress(), driver.getPhone());
				}
			}
		} finally {
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}

	public static void addDriver() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the driver's username:");
		String username = scanner.nextLine();
		System.out.println("Enter the driver's password:");
		String password = scanner.nextLine();
		System.out.println("Enter the driver's ID:");
		int id = scanner.nextInt();
		scanner.nextLine(); // consume the newline character
		System.out.println("Enter the driver's name:");
		String name = scanner.nextLine();
		System.out.println("Enter the driver's address:");
		String address = scanner.nextLine();
		System.out.println("Enter the driver's phone number:");
		String phone = scanner.nextLine();

		// Create a string with the driver's details separated by commas
		String driverDetails = username + "," + password + "," + id + ",driver," + name + "," + address + "," + phone;

		// Write the driver's details to the users.csv file
		try {
			FileWriter writer = new FileWriter("users.csv", true); // append to file
			writer.write(driverDetails + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

		System.out.println("Driver " + name + " added successfully.");
	}

	public static void deleteDriver() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the ID of the driver to be deleted:");
		int id = 0;
		try {
			id = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid integer ID.");
			return;
		}

		// Read the lines of the file into a list
		List<String> lines = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file.");
			return;
		}

		// Find the driver with the specified ID and remove it from the list
		boolean found = false;
		for (Iterator<String> iter = lines.iterator(); iter.hasNext();) {
			String line = iter.next();
			String[] parts = line.split(",");
			if (parts.length >= 4 && parts[2].equals(String.valueOf(id)) && parts[3].equals("driver")) {
				iter.remove();
				found = true;
			}
		}

		if (!found) {
			System.out.println("Driver not found.");
			return;
		}

		// Write the updated list back to the file
		try {
			FileWriter writer = new FileWriter("users.csv");
			for (String line : lines) {
				writer.write(line);
				writer.write('\n');
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			return;
		}

		System.out.println("Driver deleted successfully.");

	}

}
