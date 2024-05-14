package project3;

public class StationWagon extends Car implements remotelyDropable, remotelyDelivered, loadable {
    
    public int loadingCapacity;
    public int currentload = 0;

    public StationWagon(String color, int seatingCapacity, int numOfDoors, int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(color, seatingCapacity, numOfDoors, numberOfTires, plateNumber, dailyFee);
        this.loadingCapacity = loadingCapacity;
    }

    @Override
    public void deliverMe() {
        System.out.println("Delivered to " + this.getDeliverLocation());
    }

    @Override
    public void dropMe() {
        System.out.println("Vehicle dropped off at : " + this.getDropLocation());
        System.out.println("Total Price: " + this.getDailyFee());
    }

    @Override
    public void loadMe(int wantedLoad) throws OverWeightException {
        if (wantedLoad <= loadingCapacity) {
            System.out.println("loaded");
            currentload = wantedLoad;
        } else {
            throw new OverWeightException("Too much load");
        }
    }
    @Override
    public String toString() {
        return "Station Wagon | Loading Capacity: " + loadingCapacity + super.toString();
    }

}
