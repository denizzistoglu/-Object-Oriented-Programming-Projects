package project3;


public class SUV extends Car  {

    public String typeOfWheel;

    public SUV(String color, int seatingCapacity, int numOfDoors, int numberOfTires, int plateNumber, double dailyFee) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
       
        
    }

    public SUV(String color, int seatingCapacity, int numOfDoors, int numberOfTires, int plateNumber, double dailyFee, String typeOfWheel) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.typeOfWheel = typeOfWheel;
    }
    @Override
    public String toString() {
        return super.toString()+" type of wheel: "+typeOfWheel ;
    }


}
