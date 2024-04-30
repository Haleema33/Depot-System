import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class manager_menu {
    static Scanner scan = new Scanner(System.in);
    int ans;
    VehicleManager manager = new VehicleManager();
    int ans2;

    public static void main(String[] args) throws IOException {

        // ========
        int answer;
        do {
            System.out.println("\nPress 0 to go to manager_menu. \nPress 1 to end the program.");
            answer = scan.nextInt();
        } while (answer != 0 && answer != 1);
        if (answer == 0) {
            manager_menu m = new manager_menu();
            m.display_menu();
        } else {
            System.out.println("Thank you for using our Depot System Application.");
        }
    }

    public void display_menu() throws IOException {
    	System.out.println("Please select an option:");
    	System.out.println("1) View Work Schedule");
    	System.out.println("2) Assign Work Schedule");
    	System.out.println("3) Delete Work Schedule\n");

    	System.out.println("4) View Driver Details");
    	System.out.println("5) View Vehicle Details\n");

    	
    	System.out.println("6) Add a Driver");
    	System.out.println("7) Add a Vehicle\n");

    	System.out.println("8) Remove a Driver");
    	System.out.println("9) Remove a Vehicle\n");
    	
        ans = scan.nextInt();

        switch (ans) {
            case 1:
            	WorkSchedule.viewWorkSchedule();
                main(null);
                break;

            case 2:
            	WorkSchedule.createWorkSchedule();
                main(null);
                break;

            case 3:
                WorkSchedule.deleteWorkSchedule();
                main(null);
                break;
           
            case 4:

            	 Driver.viewDrivers();
                 main(null);
                 break;

               

            case 5:
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

            case 6:
                Driver.addDriver();
                main(null);
                break;
            case 7:
                do {
                    try {
                        System.out.println("To add new Truck Vehicle, Press 0 \nTo add new Tanker Vehicle, press 1!");
                        ans2 = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scan.nextLine(); // consume the invalid input
                        continue; // go back to the beginning of the loop
                    }
                } while (ans2 != 0 && ans2 != 1);
                scan.nextLine();

                if (ans2 == 0) {
                    System.out.println("________________Truck_________");
                    System.out.print("Enter the make of the truck: ");
                    String make = scan.nextLine();
                    System.out.print("Enter the model of the truck: ");
                    String model = scan.nextLine();
                    System.out.print("Enter the weight of the truck: ");
                    int weight = scan.nextInt();
                    scan.nextLine(); // consume newline character
                    System.out.print("Enter the registration number of the truck: ");
                    String regNo = scan.nextLine();
                    System.out.print("Enter the maximum cargo capacity of the truck: ");
                    int maxCargoCapacity = scan.nextInt();
                    scan.nextLine(); // consume newline character

                    Truck truck = new Truck(make, model, weight, regNo, maxCargoCapacity);
                    manager.addVehicle(truck);

                    System.out.println("Details added successfully.");
                    main(null);
                } else if (ans2 == 1) {
                    System.out.println("________________Tanker_________");
                    System.out.print("Enter the make of the tanker: ");
                    String make = scan.nextLine();
                    System.out.print("Enter the model of the tanker: ");
                    String model = scan.nextLine();
                    System.out.print("Enter the weight of the tanker: ");
                    int weight = scan.nextInt();
                    scan.nextLine(); // consume newline character
                    System.out.print("Enter the registration number of the tanker: ");
                    String regNo = scan.nextLine();
                    System.out.print("Enter the maximum liquid capacity of the tanker: ");
                    int maxLiquidCapacity = scan.nextInt();
                    scan.nextLine(); // consume newline character
                    System.out.print("Enter the type of liquid carried by the tanker: ");
                    String liquidType = scan.nextLine();

                    Tanker tanker = new Tanker(make, model, weight, regNo, maxLiquidCapacity, liquidType);
                    manager.addVehicle(tanker);

                    System.out.println("Details added successfully.");
                    main(null);
                }
                
                break;
            case 8:
                Driver.deleteDriver();
                main(null);
                break;
            case 9:
                scan.nextLine();

                System.out.println("Enter Regno of the Vehicle: ");
                String ans = scan.nextLine();

                manager.deleteVehicle(ans);
                System.out.println("Vehicle deleted successfully");
                main(null);
                break;
            
            default:
                break;
        }
    }

   
}
