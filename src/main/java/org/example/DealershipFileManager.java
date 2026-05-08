package org.example;

import java.io.*;
import java.util.List;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Vehicles.csv"));
            String line;

            line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                dealership = new Dealership(parts[0], parts[1], parts[2]);
            }
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                 dealership.addVehicle(vehicle);
                }
            reader.close();
        } catch (IOException e) {
            System.out.println("There was a problem reading the inventory file.");
        } catch (Exception ex) {
            System.out.println("Something went wrong with the data in the file.");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Vehicles.csv"))) {

            String header = String.format("%s|%s|%s\n",
                    dealership.getName(), dealership.getAddress(), dealership.getPhone());
            writer.write(header);


            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicleLine = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                writer.write(vehicleLine);
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
