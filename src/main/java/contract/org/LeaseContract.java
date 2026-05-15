package contract.org;

import org.example.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract{
    private BigDecimal expextedEndingValue;
    private BigDecimal leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        BigDecimal price = BigDecimal.valueOf(vehicleSold.getPrice());
        this.expextedEndingValue = price.multiply(new BigDecimal(".50"));
        this.leaseFee = price.multiply(new BigDecimal("0.07"));

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
        int
        return 0;
    }
}
