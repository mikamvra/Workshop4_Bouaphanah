package contract.org;

import org.example.Vehicle;

import java.math.BigDecimal;

public class SaleContract extends Contract{
    private BigDecimal salesTax =

    public SaleContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
