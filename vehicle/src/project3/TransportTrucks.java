package project3;

public class TransportTrucks extends Truck implements loadable {

  

    public boolean abroad;
    public int currentload = 0;

    public TransportTrucks(int loadingCapacity, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
    }

    public TransportTrucks(int loadingCapacity, boolean abroad, int numberOfTires, int plateNumber, double dailyFee) {
        super(loadingCapacity, numberOfTires, plateNumber, dailyFee);
        this.abroad = abroad;
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
        return "Transport Truck | Current Load: " + currentload + super.toString();
    }
}
