import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class driver_menu {
	static Scanner scan = new Scanner(System.in);
	int ans;
	VehicleManager manager = new VehicleManager();
	int ans2;

	public static void main(String[] args) throws IOException {

		// ========
		int answer;
		do {
			System.out.println("\nPress 0 to go to driver_menu. \nPress 1 to end the program.");
			answer = scan.nextInt();
		} while (answer != 0 && answer != 1);
		if (answer == 0) {
			driver_menu m = new driver_menu();
			m.display_menu();
		} else {
			System.out.println("Thank you for using our Depot System Application.");
		}
	}

	public void display_menu() throws IOException {
		System.out.println("1) View Work Schedule");
		System.out.println("2) View Vehicle details");
		System.out.println("3) View Driver details");

		ans = scan.nextInt();

		switch (ans) {
		case 1:
			WorkSchedule.viewWorkSchedule();
			main(null);
			break;

		case 2:
			do {
				try {
					System.out.println("\nTo view Trucks Details, press 0\nTo view Tankers Details, press 1\n");
					ans2 = scan.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number.");
					scan.nextLine(); // consume the invalid input
					continue; // go back to the beginning of the loop
				} // using do while loop to make the user chose one of the inputs (1 or 2)
			} while (ans2 != 0 && ans2 != 1);
			if (ans2 == 0) {
				manager.getTrucksFromCSV();
			} else if (ans2 == 1) {
				manager.getTankersFromCSV();
			}
			main(null);
			break;

		case 3:
			Driver.viewDrivers();
			main(null);
			break;
		}
	}

}
