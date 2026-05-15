package contract.org;

import org.example.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract{
    private BigDecimal expextedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        // Only these 4!
        super(date, customerName, customerEmail, vehicleSold);

        BigDecimal price = BigDecimal.valueOf(vehicleSold.getPrice());
        this.expextedEndingValue = price.multiply(new BigDecimal(".50"));
        this.leaseFee = price.multiply(new BigDecimal(".07"));
    }


    @Override
    public double getTotalPrice() {
        BigDecimal price = BigDecimal.valueOf(getVehicleSold().getPrice());
        BigDecimal total = price.add(leaseFee);

        return total.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public double getMonthlyPayment() {
        double price = getTotalPrice();
        double interestRate = .04;
        int months= 36;

        double monthlyRate =  interestRate / 12;
        double monthlyPayment = price * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -months)));

        return new BigDecimal(monthlyPayment).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }
}
