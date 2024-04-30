import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	public static void main(String[] args) {
		String filePath = "users.csv"; // Path to your CSV file

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;

			// Read and print each line of the CSV file
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(","); // Assuming comma-separated values
				for (String value : values) {
					System.out.print(value + "\t");
				}
				System.out.println(); // Move to the next line after printing all values of a row
			}

			reader.close(); // Close the reader
		} catch (IOException e) {
			System.err.println("Error reading CSV file: " + e.getMessage());
			e.printStackTrace(); // Print the stack trace for debugging
		}
	}
}
