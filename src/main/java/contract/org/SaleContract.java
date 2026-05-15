package contract.org;

import org.example.Vehicle;

import java.math.BigDecimal;

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
        Vehicle v = getVehicleSold();
        BigDecimal processingFee;
        if (v.getPrice() < 10000){
        processingFee = new BigDecimal("295.00");
        } else
            processingFee = new BigDecimal("495.00");
        double v1 = v.getPrice() + (v.getPrice() * salesTax) + recordingFee + processingFee;
        return v1;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
