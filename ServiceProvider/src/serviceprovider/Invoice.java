/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceprovider;

/**
 *
 *
 */
import java.util.Date;

public class Invoice {
   private double usageLimit;
    private double currentSpending;
    private Date lastDayToPay = new Date();
    private double totalSpending;

    public Invoice(double usageLimit, Date lastDayToPay) {
        this.usageLimit = usageLimit;
        this.currentSpending = 0;
        this.lastDayToPay.setTime((long) (lastDayToPay.getTime() + (2592 * Math.pow(10, 6))));
    }

    public boolean isLimitedExceeded(double amount) { 
        return amount > this.usageLimit;
    }

    public void addCost(double amount) {
        this.totalSpending += amount;
        this.currentSpending += amount;
    }

    public void pay(double amount) {
        System.out.println("");
        System.out.println("- Current spending: " + this.currentSpending);
        System.out.println("- Subscriber paid " + amount + "$ to their invoice");
        
        if (amount > currentSpending) {
            System.out.println("- Subscriber overpaid by " + (amount - this.currentSpending) + "$");
            System.out.println("- " + (amount - this.currentSpending) + "$ of change left over, they have been saved in the invoice");
        }
        
        this.currentSpending -= amount;
        
        System.out.println("- " + this.currentSpending + "$ money left over in the invoice");
        System.out.println("");
        
        if (this.currentSpending <= 0) {
            this.lastDayToPay.setTime(0);
        }
    }

    public void changeUsageLimit(double amount) {
        this.usageLimit = amount;
    }

    public double getUsageLimit() {
        return this.usageLimit;
    }

    public double getCurrentSpending() {
        return this.currentSpending;
    }

    public long getDate() {
        return this.lastDayToPay.getTime();
    }

    public double getTotalSpending() {
        return this.totalSpending;
    }

}

    