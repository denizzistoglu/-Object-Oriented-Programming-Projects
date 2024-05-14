
package project3;

public abstract class Car extends Vehicle implements rentable {

    public String color;
    public int seatingCapacity;
    public int numOfDoors;
    

    public Car(String color, int seatingCapacity, int numOfDoors, int numberOfTires, int plateNumber, double dailyFee) {
        super(numberOfTires, plateNumber, dailyFee);
        this.color = color;
        this.numOfDoors = numOfDoors;
        this.seatingCapacity = seatingCapacity;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public void setCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }
    @Override
    public String toString() {
        return " | Color: " + color + " | Number Of Doors: " + numOfDoors + " | Seating Capacity: " + seatingCapacity + super.toString();
    }

}
