
package project3;

public class Truck extends Vehicle {

    public int loadingCapacity;

    public Truck(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(numberOfTires, plateNumber, dailyFee);
        this.loadingCapacity = loadingCapacity;
    }
    @Override
    public String toString() {
        return " | Loading Capacity: " + loadingCapacity + super.toString();
    }

}

