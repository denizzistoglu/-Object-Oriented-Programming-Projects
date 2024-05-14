
package project3;

import java.util.Date;
import java.util.Scanner;
import java.io.Serializable;

public abstract class Vehicle implements rentable, Serializable {

    private Customer customer;
    private boolean isRented;
    public static int idSet = 0;
    public int id;
    protected int numberOfTires;
    private int plateNumber;
    protected double dailyFee;
    private String dropLocation;
    private String deliverLocation;
    private Date startDate;
    private Date endDate;
    private Date rentingDate;

    public Vehicle(int numberOfTires, int plateNumber, double dailyFee) {
        this.numberOfTires = numberOfTires;
        this.plateNumber = plateNumber;
        this.dailyFee = dailyFee;
        idSet++;
        id = idSet;
    }

    public double getDailyFee() {
        long time = ((this.endDate.getTime() - this.startDate.getTime()));
        int days = (int) (time * 1.1574 * Math.pow(10, -8));
        return dailyFee * days;
    }

    @Override
    public void rentMe(Date starting, Date ending, Customer c) throws SorryWeDontHaveThatOneException {
        if (this instanceof bookable) {
            if (((bookable) this).getIsBooked()) {
                System.out.println("Vehicle is already booked");
            }
        }
        if (isRented) {
            throw new SorryWeDontHaveThatOneException("This car is not avaialble");
        }
        Scanner input = new Scanner(System.in);
        this.startDate = starting;
        this.endDate = ending;
        if (this instanceof remotelyDropable && this instanceof remotelyDelivered) {
            System.out.print("Pick a drop off location: ");
            this.dropLocation = input.next();
            System.out.print("Pick a delivery location: ");
            this.deliverLocation = input.next();
        }
        isRented = true;
        this.customer = c;
        this.rentingDate = new Date();
    }

    public String getDeliverLocation() {
        return deliverLocation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public boolean getBeingUsed() {
        return this.isRented;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getRentingDate() {
        return rentingDate;
    }

    @Override
    public String toString() {
        String RentedOrNot;
        if (isRented) {
            RentedOrNot = "is rented from " + startDate + " till " + endDate;
        } else {
            RentedOrNot = "is not rented! ";
        }
        return  " | Plate number: " + plateNumber + " | Daily Fee: " +  dailyFee + " | " + RentedOrNot;
    }

}
