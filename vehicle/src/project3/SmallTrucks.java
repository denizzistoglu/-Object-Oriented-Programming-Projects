
package project3;
public class SmallTrucks extends Truck implements remotelyDropable, remotelyDelivered, loadable {

    public int currentLoad = 0;

    public SmallTrucks(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
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
            currentLoad = wantedLoad;
        } else {
            throw new OverWeightException("Overweight");
        }
    }
    @Override
    public String toString() {
        return "Small Truck | Current Load: "+ currentLoad + super.toString();
    }
}


