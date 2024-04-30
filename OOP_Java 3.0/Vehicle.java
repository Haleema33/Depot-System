import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Vehicle {
	private String make;
	private String model;
	private int weight;
	private String regNo;

	public Vehicle(String make, String model, int weight, String regNo) {
		this.make = make;
		this.model = model;
		this.weight = weight;
		this.regNo = regNo;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

}

// =============
class Truck extends Vehicle {
	private int maxCargoCapacity;

	public Truck(String make, String model, int weight, String regNo, int maxCargoCapacity) {
		super(make, model, weight, regNo);
		this.maxCargoCapacity = maxCargoCapacity;
	}

	public int getMaxCargoCapacity() {
		return maxCargoCapacity;
	}

	public void setMaxCargoCapacity(int maxCargoCapacity) {
		this.maxCargoCapacity = maxCargoCapacity;
	}
}

class Tanker extends Vehicle {
	private int maxLiquidCapacity;
	private String liquidType;

	public Tanker(String make, String model, int weight, String regNo, int maxLiquidCapacity, String liquidType) {
		super(make, model, weight, regNo);
		this.maxLiquidCapacity = maxLiquidCapacity;
		this.liquidType = liquidType;
	}

	public int getMaxLiquidCapacity() {
		return maxLiquidCapacity;
	}

	public void setMaxLiquidCapacity(int maxLiquidCapacity) {
		this.maxLiquidCapacity = maxLiquidCapacity;
	}

	public String getLiquidType() {
		return liquidType;
	}

	public void setLiquidType(String liquidType) {
		this.liquidType = liquidType;
	}
}

class VehicleManager {
	static final String TRUCK_FILE_PATH = "truck.csv";
	static final String TANKER_FILE_PATH = "tanker.csv";
	static final String DRIVER_FILE_PATH = "users.csv";
	static final String WORKSCHEDULE_FILE_PATH = "workschedule.csv";

	public void addVehicle(Vehicle vehicle) {
		try {
			if (vehicle instanceof Truck) {
				FileWriter writer = new FileWriter(TRUCK_FILE_PATH, true);
				String line = String.format("%s,%s,%d,%s,%d", vehicle.getMake(), vehicle.getModel(),
						vehicle.getWeight(), vehicle.getRegNo(), ((Truck) vehicle).getMaxCargoCapacity());
				writer.write(line + "\n");
				writer.close();
			} else if (vehicle instanceof Tanker) {
				FileWriter writer = new FileWriter(TANKER_FILE_PATH, true);
				String line = String.format("%s,%s,%d,%s,%d,%s", vehicle.getMake(), vehicle.getModel(),
						vehicle.getWeight(), vehicle.getRegNo(), ((Tanker) vehicle).getMaxLiquidCapacity(),
						((Tanker) vehicle).getLiquidType());
				writer.write(line + "\n");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Truck> getTrucksFromCSV() {
		// this method is called in Menu.java when the user wants to View Truck details
		ArrayList<Truck> trucks = new ArrayList<>(); // making an arraylist to store the truck information

		// System.out.println("Make \t Model \t Weight\t regNo \t maxCargoCapacity ");
		System.out.printf("%-10s %-10s %-10s %-10s %-20s\n", "Make", "Model", "Weight", "RegNo", "maxCargoCapacity");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(VehicleManager.TRUCK_FILE_PATH)));
			// using bufferedreader to read the values from truck.csv
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				// using while loop to read all the values from reader object into String Line;
				String[] values = line.split(",");
				String make = values[0];
				String model = values[1];
				int weight = Integer.parseInt(values[2]);
				String regNo = values[3];
				int maxCargoCapacity = Integer.parseInt(values[4]);

				// Storing the values in Temporarily String and integer variables

				Truck truck = new Truck(make, model, weight, regNo, maxCargoCapacity);
				trucks.add(truck);
				// adding the Truck values to arraylist
				// System.out.println(make + " \t " + model + "\t" + weight + "\t" + regNo + "\t
				// " + maxCargoCapacity);

				System.out.printf("%-10s %-10s %-10s %-10s %-20s\n", make, model, weight, regNo, maxCargoCapacity);
				// printing the values
			}
			reader.close(); // closing the reader
		} catch (IOException e) {
			e.printStackTrace();
		}
		return trucks;
	}

	public static ArrayList<Truck> getTankersFromCSV() {
		// this method is called in Menu.java when the user wants to View Tanker details
		ArrayList<Truck> tanker = new ArrayList<>();
		// making an arraylist to store the truck information

		System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s\n", "Make", "Model", "Weight", "RegNo", "Max Liquid Cap",
				"Liquid Type");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(VehicleManager.TANKER_FILE_PATH)));
			// using bufferedreader to read the values from tanker.csv
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				// using while loop to read all the values from reader object into String Line;
				String[] values = line.split(",");
				String make = values[0];
				String model = values[1];
				int weight = Integer.parseInt(values[2]);
				String regNo = values[3];
				int maxLiquidCapacity = Integer.parseInt(values[4]);
				String liquidType = values[5];
				// Storing the values in Temporarily String and integer variables
				System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s\n", make, model, weight, regNo,
						maxLiquidCapacity, liquidType);
				// printing the values
			}
			reader.close(); // closing the reader
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tanker;
	}

	public static void deleteVehicle(String regNo) {
		try {
			// search the truck file for a vehicle with the given registration number
			List<String> truckLines = Files.readAllLines(Paths.get(TRUCK_FILE_PATH));
			List<String> updatedTruckLines = new ArrayList<>();
			for (String line : truckLines) {
				String[] parts = line.split(",");
				if (!parts[3].equals(regNo)) {
					// this is not the vehicle we want to delete, so keep it in the file
					updatedTruckLines.add(line);
				}
			}
			// overwrite the truck file with the updated contents
			FileWriter truckWriter = new FileWriter(TRUCK_FILE_PATH);
			for (String line : updatedTruckLines) {
				truckWriter.write(line + "\n");
			}
			truckWriter.close();

			// search the tanker file for a vehicle with the given registration number
			List<String> tankerLines = Files.readAllLines(Paths.get(TANKER_FILE_PATH));
			List<String> updatedTankerLines = new ArrayList<>();
			for (String line : tankerLines) {
				String[] parts = line.split(",");
				if (!parts[3].equals(regNo)) {
					// this is not the vehicle we want to delete, so keep it in the file
					updatedTankerLines.add(line);
				}
			}
			// overwrite the tanker file with the updated contents
			FileWriter tankerWriter = new FileWriter(TANKER_FILE_PATH);
			for (String line : updatedTankerLines) {
				tankerWriter.write(line + "\n");
			}
			tankerWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
