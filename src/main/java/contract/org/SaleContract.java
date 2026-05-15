package contract.org;

import org.example.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleContract extends Contract{
    private BigDecimal salesTax = new BigDecimal("0.05");
    private BigDecimal recordingFee = new BigDecimal("100.00");
    private boolean finance;

    public SaleContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        BigDecimal vehiclePrice = BigDecimal.valueOf(getVehicleSold().getPrice());

        BigDecimal processingFee;
        if (getVehicleSold().getPrice() < 10000){
        processingFee = new BigDecimal("295.00");
        } else {
            processingFee = new BigDecimal("495.00");
        }
        BigDecimal taxAmount = vehiclePrice.multiply(salesTax);
        BigDecimal total = vehiclePrice.add(taxAmount).add(recordingFee).add(processingFee);

        return total.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) {
            return 0.0;
        }
        double price = getTotalPrice();
        double interestRate;
        int months;

        if(price >= 10000){
            interestRate = 0.0425;
            months = 48;
        }else {
            interestRate = 0.0525;
            months = 24;
        }
        BigDecimal thePrice = new BigDecimal(Double.toString(price));
        BigDecimal theMonthlyRate = new BigDecimal(Double.toString(interestRate/12));

        double monthlyPayment = price * ( (interestRate / 12) /
                (1 - Math.pow(1 + (interestRate / 12), -months)) );
        return new BigDecimal(monthlyPayment).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
