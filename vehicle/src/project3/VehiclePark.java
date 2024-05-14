package project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class VehiclePark {

    private Admin a = new Admin();
    private ArrayList vehicleList = new ArrayList<Vehicle>();
    private File vehicles;
    private ArrayList rentedVehicleList = new ArrayList<Vehicle>();
    private File rentedVehicles;
    private ArrayList bookedVehicleList = new ArrayList<Vehicle>();
    private File bookedVehicles;
    private ArrayList registeredCustomerList = new ArrayList<Customer>();
    private File registeredCustomers;
    private ArrayList adminList = new ArrayList<Admin>();
    private File admins;
    private File dailyReport;

    public VehiclePark(File vehicles, File rentedVehicles, File bookedVehicles, File registeredCustomers, File admins, File dailyReport) {
        this.vehicles = vehicles;
        this.rentedVehicles = rentedVehicles;
        this.bookedVehicles = bookedVehicles;
        this.registeredCustomers = registeredCustomers;
        this.dailyReport = dailyReport;
        this.admins = admins;
    }

    public void displayVehicles() throws Exception {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (int i = 0; i < v.size(); i++) {
            System.out.println(((Vehicle) v.get(i)).toString());
        
    }

    public void addVehicle(Vehicle veh) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (vehicles.length() != 0) {
            this.vehicleList = getCurrentFileIntoArray(vehicles);
        }
        vehicleList.add(veh);
        getCurrentArrayIntoFile(vehicleList, vehicles);
    }

    public void removeVehicle(int plateNumber) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle veh = getVehicle(plateNumber);
        if (vehicles.length() != 0) {
            this.vehicleList = getCurrentFileIntoArray(vehicles);
        }
        for (int i = 0; i < vehicleList.size(); i++) {
            if (((Vehicle) vehicleList.get(i)).getPlateNumber() == veh.getPlateNumber()) {
                vehicleList.remove(i);
            }
        }
        getCurrentArrayIntoFile(vehicleList, vehicles);
    }

    public void displayAvailableVehicles(Date date1, Date date2) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Vehicle> v = getCurrentFileIntoArray(vehicles);
        for (Vehicle vehicle : v) {
            if (!vehicle.getBeingUsed()) {
                if (v instanceof bookable) {
                    bookable v1 = (bookable) v;
                    if (!v1.getIsBooked()) {
                        System.out.println((v1.toString()));
                    } else if (date1.getTime() < v1.getBookStart().getTime() && date2.getTime() < v1.getBookStart().getTime()) {
                        System.out.println(v1.toString());
                    } else if (date1.getTime() > v1.getBookEnd().getTime() && date2.getTime() > v1.getBookEnd().getTime()) {
                        System.out.println(v1.toString());
                    }
                } else {
                    System.out.println(vehicle.toString());
                }
            } else if (date1.getTime() < vehicle.getStartDate().getTime() && date2.getTime() < vehicle.getStartDate().getTime()) {
                System.out.println(vehicle.toString());
            } else if (date1.getTime() > vehicle.getEndDate().getTime() && date2.getTime() > vehicle.getEndDate().getTime()) {
                System.out.println(vehicle.toString());
            }
        }
    }

    public void displayAvailableVehicles(Date date1, Date date2, Class<?> vehicleType) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (Object vehicle : v) { //checking renting date
            if (vehicleType.isInstance(vehicle)) {
                Vehicle veh = (Vehicle) vehicle;
                if (!veh.getBeingUsed()) {
                    if (veh instanceof bookable) {
                        bookable v1 = (bookable) veh;
                        if (!v1.getIsBooked()) {
                            System.out.println((v1.toString()));
                        } else if (date1.getTime() < v1.getBookStart().getTime() && date2.getTime() < v1.getBookStart().getTime()) {
                            System.out.println(v1.toString());
                        } else if (date1.getTime() > v1.getBookEnd().getTime() && date2.getTime() > v1.getBookEnd().getTime()) {
                            System.out.println(v1.toString());
                        }
                    } else {
                        System.out.println((veh.toString()));
                    }
                } else if (date1.getTime() < veh.getStartDate().getTime() && date2.getTime() < veh.getStartDate().getTime()) {
                    System.out.println(veh.toString());
                } else if (date1.getTime() > veh.getEndDate().getTime() && date2.getTime() > veh.getEndDate().getTime()) {
                    System.out.println(veh.toString());
                }
            }
        }

    }

    public void bookVehicle(int numberPlate, Date bookStart, Date bookEnd, Customer c) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle book = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (book == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (book instanceof bookable) {
            try {
                removeVehicle(book.getPlateNumber());
                ((bookable) book).bookMe(bookStart, bookEnd, c);
                vehicleList.add(book);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                if (bookedVehicles.length() != 0) {
                    this.bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                }
                bookedVehicleList.add(book);
                getCurrentArrayIntoFile(bookedVehicleList, bookedVehicles);
            } catch (SorryWeDontHaveThatOneException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle cannot be booked");
        }
    }

    public void cancelBook(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle book = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (book == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (book instanceof bookable && ((bookable) book).getIsBooked()) {
            try {
                removeVehicle(book.getPlateNumber());
                ((bookable) book).cancelMe();
                vehicleList.add(book);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                if (bookedVehicles.length() != 0) {
                    this.bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                }
                bookedVehicleList = getCurrentFileIntoArray(bookedVehicles);
                for (int i = 0; i < bookedVehicleList.size(); i++) {
                    if (((Vehicle) bookedVehicleList.get(i)).getPlateNumber() == book.getPlateNumber()) {
                        bookedVehicleList.remove(i);
                    }
                }
                getCurrentArrayIntoFile(bookedVehicleList, bookedVehicles);
            } catch (NoCancellationYouMustPayException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle wasnt booked");
        }
    }

    public void rentVehicle(int numberPlate, Date start, Date end, Customer c) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle rent = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (rent == null) {
            System.out.println("That vehicle doesnt exist");
        } else {
            try {
                removeVehicle(rent.getPlateNumber());
                rent.rentMe(start, end, c);
                vehicleList.add(rent);
                getCurrentArrayIntoFile(vehicleList, vehicles);
                System.out.println(vehicleList.get(0).toString());
                if (rentedVehicles.length() != 0) {
                    this.rentedVehicleList = getCurrentFileIntoArray(rentedVehicles);
                }
                rentedVehicleList.add(rent);
                getCurrentArrayIntoFile(rentedVehicleList, rentedVehicles);
            } catch (SorryWeDontHaveThatOneException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void dropVehicle(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle dropVehicle = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (dropVehicle == null) {
            System.out.println("that vehicle doesnt exist");
        } else if (dropVehicle instanceof remotelyDropable && (((Vehicle) dropVehicle).getDropLocation() != null)) {
            removeVehicle(dropVehicle.getPlateNumber());
            ((remotelyDropable) dropVehicle).dropMe();
            vehicleList.add(dropVehicle);
            getCurrentArrayIntoFile(vehicleList, vehicles);
        } else {
            System.out.println("That vehicle cannot be remotely dropable");
        }
    }

    public void load(int numberPlate, int amount) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vehicle load = getVehicle(numberPlate);
        vehicleList = getCurrentFileIntoArray(vehicles);
        if (load == null) {
            System.out.println("That vehicle doesnt exist");
        } else if (load instanceof loadable) {
            try {
                removeVehicle(load.getPlateNumber());
                ((loadable) load).loadMe(amount);
                vehicleList.add(load);
                getCurrentArrayIntoFile(vehicleList, vehicles);
            } catch (OverWeightException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("That vehicle cannot be dropped");
        }
    }

    public File dailyReport() throws FileNotFoundException, IOException, ClassNotFoundException {
        PrintWriter report = new PrintWriter(dailyReport);
        report.println("Daily Report: ");
        report.println();
        if (rentedVehicles.length() != 0) {
            ArrayList rented = getCurrentFileIntoArray(rentedVehicles);
            report.println("List of Vehicles rented  today:");
            for (Object vehicle : rented) {
                Vehicle v = (Vehicle) vehicle;
                if (v.getRentingDate().getTime() > (new Date().getTime() - 8.64 * Math.pow(10, 7)) && v.getRentingDate().getTime() < new Date().getTime()) {
                    report.println();
                    report.println(v.toString());
                    report.println(v.getCustomer().toString());
                }
            }
        }
        report.println();
        if (bookedVehicles.length() != 0) {
            ArrayList booked = getCurrentFileIntoArray(bookedVehicles);
            report.println("List of Vehicles booked today:");
            for (Object vehicle : booked) {
                Vehicle v = (Vehicle) vehicle;
                if (((bookable) v).getBookingDate().getTime() > (new Date().getTime() - 8.64 * Math.pow(10, 7)) && ((bookable) v).getBookingDate().getTime() < new Date().getTime()) {
                    report.println();
                    report.println(v.toString());
                }
            }
        }
        report.flush();
        report.close();
        return dailyReport;
    }

    public Vehicle getVehicle(int numberPlate) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList v = getCurrentFileIntoArray(vehicles);
        for (int i = 0; i < v.size(); i++) {
            if (((Vehicle) v.get(i)).getPlateNumber() == numberPlate) {
                return (Vehicle) v.get(i);
            }
        }
        return null;
    }

    public ArrayList getCurrentFileIntoArray(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream input = new FileInputStream(f);
        ObjectInputStream ob = new ObjectInputStream(input);
        ArrayList aaa = (ArrayList) ob.readObject();
        ob.close();
        return aaa;
    }

    public void addCustomer(Customer customer) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (registeredCustomers.length() != 0) {
            this.registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        }
        registeredCustomerList.add(customer);
        getCurrentArrayIntoFile(registeredCustomerList, registeredCustomers);
    }

    public Customer getCustomer(String email, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        for (int i = 0; i < registeredCustomerList.size(); i++) {
            if (((Customer) registeredCustomerList.get(i)).getEmail().equals(email) && ((Customer) registeredCustomerList.get(i)).getPassword().equals(password)) {
                return (Customer) registeredCustomerList.get(i);
            }
        }
        return null;
    }

    public void addAdmin(Admin newAdmin) throws FileNotFoundException, IOException, ClassNotFoundException {

        if (admins.length() != 0) {
            this.adminList = getCurrentFileIntoArray(admins);
        }
        adminList.add(newAdmin);
        getCurrentArrayIntoFile(adminList, admins);
    }

    public boolean checkAdmin(String email, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        if ((a.getEmail().equals(email) && a.getPassword().equals(password))) {
            return a.getEmail().equals(email) && a.getPassword().equals(password);
        }
        if (admins.length() == 0) {
            return false;
        }
        adminList = getCurrentFileIntoArray(admins);
        for (int i = 0; i < adminList.size(); i++) {
            if (((Admin) adminList.get(i)).getEmail().equals(email) && ((Admin) adminList.get(i)).getPassword().equals(password)) {
                return true;
            }
            System.out.println("Invalid credentials");
        }
        return false;
    }

    public void getCurrentArrayIntoFile(ArrayList a, File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream o = new FileOutputStream(f);
        ObjectOutputStream ob = new ObjectOutputStream(o);
        ob.writeObject(a);
        ob.close();
    }

    public Customer changePassword(String name, int age, String email) throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        registeredCustomerList = getCurrentFileIntoArray(registeredCustomers);
        for (int i = 0; i < registeredCustomerList.size(); i++) {
            if (((Customer) registeredCustomerList.get(i)).getEmail().equals(email)) {
                if (((Customer) registeredCustomerList.get(i)).getName().equals(name) && ((Customer) registeredCustomerList.get(i)).getAge() == age) {
                    System.out.print("Enter your new password: ");
                    String password = input.next();
                    System.out.println("");
                    ((Customer) registeredCustomerList.get(i)).setPassword(password);
                    getCurrentArrayIntoFile(registeredCustomerList, registeredCustomers);
                    return (Customer) registeredCustomerList.get(i);
                } else {
                    System.out.println("Wrong information");
                }
            }
        }
        System.out.println("Email doesnt exist");
        return null;
    }

}
