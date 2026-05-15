package contract.org;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContactFileManager {
    public void saveContract(Contract contract){
        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("contracts.cvs", true))){
            String data = "";
            if (contract instanceof SaleContract){
                SaleContract s = (SaleContract) contract;
                data = String.format("SALE|%s|%s|%d|%s|%s|%s|%.2f|%.2f|%s|%.2f\n", s.getDate(), s.getCustomerName(), s.getCustomerEmail(),
                        s.getVehicleSold().getVin(), s.getVehicleSold().getMake(), s.getVehicleSold().getModel(), s.getTotalPrice(), s.getMonthlyPayment());
            } else if (contract instanceof LeaseContract) {
                LeaseContract l = (LeaseContract) contract;
                data = String.format("LEASE|%s|%s|%s|%d|%s|%s|%.2f|%.2f|%.2f\n", l.getDate(), l.getCustomerName(), l.getCustomerEmail(),
                        l.getVehicleSold().getVin(), l.getVehicleSold().getMake(), l.getVehicleSold().getModel(), l.getTotalPrice(), l.getMonthlyPayment());
            }
            bufferedWriter.write(data);
        } catch (IOException e) {
            System.out.println("Error! unable to save contract: " + e.getMessage());
        }
    }
}
