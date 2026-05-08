package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

                String vin = parts[0];
                String year = parts[1];
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                if (dealership != null) {
                    dealership.addVehicle(vehicle);
                }
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

    }
}
