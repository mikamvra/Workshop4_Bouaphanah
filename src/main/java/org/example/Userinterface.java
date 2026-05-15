package org.example;

import javax.xml.transform.Source;
import java.util.Scanner;
import java.util.List;
public class Userinterface {
    private Dealership dealership;
    private Scanner scanner;
    private Vehicle vehicle;


    public Userinterface() {
        this.scanner = new Scanner(System.in);
        init();
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();

    }

    public void display() {
        init();
        boolean running = true;

        while (running) {
            System.out.println("\u001B[36m\n~~ Welcome to the Car Dealership ~~\u001B[0m");
            System.out.println("1. Find vehicles within a price range");
            System.out.println("2. Find vehicles by make/model");
            System.out.println("3. Find vehicles by year range");
            System.out.println("4. Find vehicles by color");
            System.out.println("5. Find vehicles by mileage range");
            System.out.println("6. Find vehicles by type (sedan, truck, SUV, etc.)");
            System.out.println("7. List ALL vehicles");
            System.out.println("8. Add a vehicle");
            System.out.println("9. Remove a vehicle");
            System.out.println("0. Quit");
            System.out.print("Please choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "0":
                    running = false;
                    System.out.println("\u001B[31;1mExiting Program!\u001B[0m");
                    break;
                default:
                    System.out.println("Invalid option. Please Try Again!");
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.println("Enter Minimum: ");
        double min = scanner.nextDouble();
        System.out.println("Enter Maximum: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max); // Come back to this!!!
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter Make: ");
        String make = scanner.nextLine();
        System.out.println("Enter Model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model); // Come back to this!!!
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.println("Enter Minimum Year: ");
        double min = scanner.nextDouble();
        System.out.println("Enter Maximum Year: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByYear((int) min, (int) max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.println("Enter Color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color); // Come back to this!!!
        displayVehicles(vehicles);

    }

    public void processGetByMileageRequest() {
        System.out.println("Enter Minimum Mileage: ");
        double min = scanner.nextDouble();
        System.out.println("Enter Maximum Mileage: ");
        double max = scanner.nextDouble();
        scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage((int) min, (int) max); // Come back to this!!!
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter Vehicle Type: ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.println("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Type (SUV, Truck, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter Odometer reading: ");
        int miles = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, miles, price);

        this.dealership.addVehicle(newVehicle);
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(this.dealership);

        System.out.println("Vehicle Successfully Added!");
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle you wish to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicleToRemove = null;
        for (Vehicle v : this.dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicleToRemove = v;
                break;
            }
        }

        if (vehicleToRemove != null) {
            this.dealership.removeVehicle(vehicleToRemove);

            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(this.dealership);

            System.out.println("Vehicle with VIN " + vin + " was successfully removed.");
        } else {
            System.out.println("Error: No vehicle found with VIN " + vin);
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-10s %-6s %-12s %-12s %-10s %-10s %-10s %-10s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Miles", "Price");
        System.out.println("----------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            System.out.printf("%-10d %-6d %-12s %-12s %-10s %-10s \u001B[31;1m%-10d\u001B[0m \u001B[32;1m$%-9.2f\n\u001B[0m",
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());
        }
    }
    public void processAllVehiclesRequest() {
        List<Vehicle> inventory = this.dealership.getAllVehicles();
        displayVehicles(inventory);
    }
    public void contractRequest(){
        System.out.println("Enter VIN of vehicle you would like to sell/lease");
        int vin = scanner.nextInt();
        scanner.nextLine();
    }
}
