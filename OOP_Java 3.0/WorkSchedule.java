import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkSchedule {
	
	// this method is called in Menu.java to view the work schedule
	public static void viewWorkSchedule() {
		
		// using printf to print according to required format.
		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", "Make", "Model", "Weight", "RegNo", "Id",
				"Name", "Phone");
		
		try {
			// Reading the file using its path and storing it into buffered reader object
			BufferedReader reader = new BufferedReader(new FileReader(new File(VehicleManager.WORKSCHEDULE_FILE_PATH)));
			
			String line;
			reader.readLine();
			
			//A while loop is used to avoid restricting the number of repetitions 
			//because the values in the csv file may change each time it runs.


			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				String make = values[0];
				String model = values[1];
				int weight = Integer.parseInt(values[2]);
				String regNo = values[3];
				String Id = values[4];
				String Name = values[5];
				String Phone = values[6];


				System.out.printf("%-10s %-10s %-10s %-10s %-10s %-20s %-10s\n", make, model, weight, regNo, Id, Name,
						Phone);
				
			}
			reader.close();
			// closing the reader
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // viewWorkSchedule method ends here.
    
	// this method is called in the Menu to create the schedule
	public static void createWorkSchedule() throws IOException {
		
		Scanner scan = new Scanner(System.in); 
		ArrayList<Truck> trucks = new ArrayList<>(); 
		BufferedReader reader; 
		int answer;
		do {
			System.out.println("Press 1 to create a schedule for Truck \nPress 2 to create a schedule for Tanker");
			answer = scan.nextInt();
		} while (answer != 1 && answer != 2); 
		if (answer == 1) {
			
			reader = new BufferedReader(new FileReader(new File(VehicleManager.TRUCK_FILE_PATH)));
			// initailizing the bufferedreader object of Truck file path
			try {
				String line;
				reader.readLine();
				while ((line = reader.readLine()) != null) { // using while loop to read the line of csv file
					String[] values = line.split(","); // spliting line into an array of Strings
					String make = values[0];
					String model = values[1];
					int weight = Integer.parseInt(values[2]);
					String regNo = values[3];
					int maxCargoCapacity = Integer.parseInt(values[4]);

					// Storing the values in String and integer Varibales
					Truck truck = new Truck(make, model, weight, regNo, maxCargoCapacity);
					trucks.add(truck);
					// adding the values to the arraylist
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// if the user entered 2, they would be able to create a schedule for Tankers
			reader = new BufferedReader(new FileReader(new File(VehicleManager.TANKER_FILE_PATH)));
			// initailizing the bufferedreader object of Tanker file path
			try {
				String line;
				reader.readLine();
				while ((line = reader.readLine()) != null) { // using while loop to read the line of csv file
					String[] values = line.split(","); // spliting line into an array of Strings
					String make = values[0];
					String model = values[1];
					int weight = Integer.parseInt(values[2]);
					String regNo = values[3];
					int maxLiquidCapacity = Integer.parseInt(values[4]);
					// Storing the values in String and integer Varibales
					Truck truck = new Truck(make, model, weight, regNo, maxLiquidCapacity);
					trucks.add(truck);
					// adding the values to the arraylist
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// After user selected the Vehicle, The information is stored in Arraylist

		int count = 1;
		System.out.println("----------------Vehicle Information----------------");
		for (Truck truck : trucks) { // Printing the vehicle information from arraylist
			System.out.println("Vehicle number #" + count);
			System.out.println("Make: " + truck.getMake());
			System.out.println("Model: " + truck.getModel());
			System.out.println("Weight: " + truck.getWeight());
			System.out.println("Registration Number: " + truck.getRegNo());
			// System.out.println("Max Cargo Capacity: " + truck.getMaxCargoCapacity());
			System.out.println();
			count = count + 1; // setting counter to let the user know about the Vehicle Number
		}

		System.out.println("Please Enter the Vehicle number to add to the WorkSchedule");
		int vehicle_Number = scan.nextInt();
		vehicle_Number = vehicle_Number - 1;
		// Taking user input and subtracting -1 as index stars from 0

		Truck truck = trucks.get(vehicle_Number);
		// Taking user input getting the arraylist
		System.out.println("You have Selected a Vehicle of Make: =" + truck.getMake() + "Model: " + truck.getModel()
				+ "Weight: " + truck.getWeight() + "Registration Number: " + truck.getRegNo() + "Max Cargo Capacity: ");
		

		ArrayList<User> driver = new ArrayList<>(); // creating a new ArrayList
		try {
			BufferedReader reader2 = new BufferedReader(new FileReader(new File(VehicleManager.DRIVER_FILE_PATH)));
			// using Buffered reader to read values from users.csv file of path stored in
			// DRIVER_FILE_PATH.
			String line;
			reader2.readLine();
			while ((line = reader2.readLine()) != null) { // using while loop to read the values from the file
				String[] values = line.split(",");
				String username = values[0];
				String password = values[1];
				int id = Integer.parseInt(values[2]);
				String role = values[3];
				String name = values[4];
				String address = values[5];
				String phone = values[6];

				// storing the values in String and integer variables to be used to print to the
				// screen
				User drive = new User(username, password, id, role, name, address, phone);
				driver.add(drive); // adding the driver information to the array

			}
			reader2.close(); // closing the reader
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("----------------Driver Information----------------");
		int counter = 1;
		int index = 0;
		for (User drive : driver) { // priting all the driver information from users.csv to the screen
			drive = driver.get(index);
			System.out.println("\n---User number #---" + counter);
			System.out.println("id: " + drive.getId());
			System.out.println("role: " + drive.getRole());
			System.out.println("Name: " + drive.getName());
			System.out.println("Address " + drive.getAddress());
			System.out.println("Phone " + drive.getPhone());
			index = index + 1;
			counter = counter + 1;
		}

		System.out.println("Please Enter the Driver number # to add to the WorkSchedule");
		int Driver_Number = scan.nextInt();
		Driver_Number = Driver_Number - 1;
		// Taking user input to let the user choose the driver to be added to the work
		// schedule

		User drive = driver.get(Driver_Number); // storing the information to an array
		System.out.println("You have Selected Driver with id: " + drive.getId() + " role: " + drive.getRole() + " Name: "
				+ drive.getName() + " Address " + drive.getAddress() + " Phone " + drive.getPhone());
		// printing the information the screen.

		try {
			FileWriter writer = new FileWriter("workschedule.csv", true); // Initializing FileWriter with file address
			String line = String.format("%s,%s,%d,%s,%d,%s,%s", truck.getMake(), truck.getModel(), truck.getWeight(),
					truck.getRegNo(), drive.getId(), drive.getName(), drive.getPhone());
			writer.write(line + "\n"); 
			writer.close(); // closing the writer
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	
	// Method to delete work schedule
	public static void deleteWorkSchedule() throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> workSchedules = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("workschedule.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                workSchedules.add(line);
            }
        }

        if (workSchedules.isEmpty()) {
            System.out.println("No work schedules found to delete.");
            return;
        }

        System.out.println("Current Work Schedules:");
        for (int i = 0; i < workSchedules.size(); i++) {
            System.out.println((i + 1) + ". " + workSchedules.get(i));
        }

        System.out.println("Enter the number of the work schedule to delete:");
        int scheduleNumber = scan.nextInt();
        if (scheduleNumber < 1 || scheduleNumber > workSchedules.size()) {
            System.out.println("Invalid schedule number.");
            return;
        }

        workSchedules.remove(scheduleNumber - 1);

        try (FileWriter writer = new FileWriter("workschedule.csv")) {
            for (String schedule : workSchedules) {
                writer.write(schedule + "\n");
            }
        }

        System.out.println("Work schedule deleted successfully.");
    }
}
