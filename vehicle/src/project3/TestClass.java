package project3;

import java.util.Date;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestClass {

    public static VehiclePark park = new VehiclePark(new File("vehicles.txt"), new File("rented.txt"), new File("booked.txt"), new File("customers.txt"), new File("admins.txt"), new File("dailyReport.txt")); //Passing files into VehiclePark
    public static Scanner input = new Scanner(System.in);
    public static Customer c;

    public static void main(String[] args) {

        int exit = 0;
        boolean adminLogin = false;
        boolean customerLogin = false;

        while (exit == 0) { //Loop to determine type of user (Customer or Admin)
            System.out.println("Do you want to enter as a (1) Customer or (2) Admin or (3) Exit");
            System.out.println("");
            System.out.print("Option: ");
            int option = input.nextInt();
            System.out.println("");
            switch (option) {
                case 1:
                    System.out.println("Are you a (1) new user or (2) registered user?");
                    System.out.println("");
                    System.out.print("Option: ");
                    int option7 = input.nextInt();
                    System.out.println("");
                    switch (option7) { //New User Login
                        case 1:
                            System.out.println("Enter these details to register into the system: ");
                            System.out.println("");
                            System.out.print("Enter name: ");
                            String name = input.next();
                            System.out.print("Enter age: ");
                            int age = input.nextInt();
                            loginDetails(name, age, "new");
                            customerLogin = true;
                            break;

                        case 2: //Registered User Login
                            loginDetails(null, 0, "registered");
                            customerLogin = true;
                            break;

                        default:
                            break;
                    }
                    break;
                case 2: //Admin Login
                    if (loginDetails(null, 0, "Admin")) {
                        adminLogin = true;
                    }

                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            while (adminLogin || customerLogin) { //Once logged in, this will display the option until login is false
                if (customerLogin) { //Menu for Customer
                    System.out.println("(1) Logout | (2) Display Options");
                    System.out.println("");
                    System.out.print("Option: ");
                    int option5 = input.nextInt();
                    System.out.println("");
                    switch (option5) {
                        case 1: //Logout
                            customerLogin = false;
                            break;
                        case 2: //Menu
                            System.out.println("• (Press 1) Display all vehicles \n"
                                    + "• (Press 2) Display available vehicles \n"
                                    + "• (Press 3) Display available vehicles by type \n"
                                    + "• (Press 4) Book a vehicle \n"
                                    + "• (Press 5) cancel my booking \n"
                                    + "• (Press 6) rent a vehicle \n"
                                    + "• (Press 7) drop a vehicle\n"
                                    + "• (Press 8) Quit");
                            System.out.println("");
                            System.out.print("Option: ");
                            int option8 = input.nextInt();
                            System.out.println("");
                            switch (option8) { //Menu details
                                case 1: { //Display vehicles
                                    try {
                                        park.displayVehicles();
                                        System.out.println("");
                                    } catch (IOException ex) {
                                        System.out.println("Input/output exception");
                                    } catch (ClassNotFoundException ex) {
                                        System.out.println("Class couldn't be found");
                                    }
                                }
                                break;

                                case 2: //Display available vehicles
                                    System.out.println("Enter a range of dates to see the available vehicles: ");
                                    System.out.println("");
                                    enterDate("display");
                                    break;

                                case 3: //Display available vehicles with a given type
                                    System.out.println("Enter a range of dates to see the available vehicles with a given type: ");
                                    System.out.println("");
                                    enterDate("type");
                                    break;

                                case 4: //Book a vehicle
                                    System.out.println("Enter booking dates: ");
                                    System.out.println("");
                                    enterDate("book");
                                    break;

                                case 5: //Cancel booking                                  
                                    try {
                                    System.out.print("Enter number plate of car you want to cancel booking for: ");
                                    System.out.println("");
                                    int numberPlate2 = input.nextInt();
                                    park.cancelBook(numberPlate2);
                                } catch (IOException ex) {
                                    System.out.println("Input/output exception");
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Class couldn't be found");
                                }
                                break;

                                case 6: //Rent a vehicle
                                    System.out.println("Enter renting dates: ");
                                    System.out.println("");
                                    enterDate("rent");
                                    break;

                                case 7: //Drop a vehicle
                                    try {
                                    System.out.print("Enter number plate of the vehicle you want to drop: ");
                                    System.out.println("");
                                    int numberPlate4 = input.nextInt();
                                    park.dropVehicle(numberPlate4);
                                } catch (IOException ex) {
                                    System.out.println("Input/output exception");
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Class couldn't be found");
                                }
                                break;

                                case 8: //Exit the system
                                    System.exit(0);
                                    break;
                            }
                            break;
                    }
                }
                if (adminLogin) { //Menu for admin
                    System.out.println("(1) Logout | (2) Display Options");
                    System.out.println("");
                    System.out.print("Option: ");
                    int option5 = input.nextInt();
                    System.out.println("");
                    switch (option5) { //Menu Details
                        case 1: //Logout
                            adminLogin = false;
                            break;

                        case 2: //Menu
                            System.out.println("• (Press 1) Display all vehicles\n"
                                    + "• (Press 2) Display available vehicles.\n"
                                    + "• (Press 3) Add a new vehicle to the system.\n"
                                    + "• (Press 4) Remove vehicle\n"
                                    + "• (Press 5) Create a Daily Report\n"
                                    + "• (Press 6) Add a new Admin \n"
                                    + "• (Press 7) Quit.");
                            System.out.println("");
                            System.out.print("Option: ");
                            int option1 = input.nextInt();
                            System.out.println("");
                            switch (option1) {
                                case 1: { //Display vehicles
                                    try {
                                        park.displayVehicles();
                                        System.out.println("");
                                    } catch (IOException ex) {
                                        System.out.println("Input/output exception");
                                    } catch (ClassNotFoundException ex) {
                                        System.out.println("Class couldn't be found");
                                    }
                                }
                                break;

                                case 2: //Display available vehicles
                                    System.out.println("Enter a range of dates to see the available vehicles: ");
                                    System.out.println("");
                                    enterDate("display");
                                    System.out.println("");
                                    break;

                                case 3: //Add a vehicle
                                    System.out.println("Which type of vehicle do you want to add? (1) Car | (2) Truck: ");
                                    System.out.println("");
                                    System.out.print("Option: ");
                                    int option2 = input.nextInt();
                                    System.out.println("");
                                    switch (option2) {
                                        case 1: //Type of car
                                            System.out.println("What type of car do you want to add? (1) Sports Car | (2) StationWagon | (3) SUV: ");
                                            System.out.println("");
                                            System.out.print("Option: ");
                                            int option3 = input.nextInt();
                                            System.out.println("");
                                            switch (option3) {
                                                case 1: //Sports car                                               
                                                    System.out.println("Enter the details of the Sports Car");
                                                    System.out.println("");
                                                    createCar("Sports");
                                                    break;

                                                case 2: //Station wagon                                               
                                                    System.out.println("Enter the details of the Stationwagon");
                                                    System.out.println("");
                                                    createCar("Stationwagon");
                                                    break;

                                                case 3: //SUV                                                     
                                                    System.out.println("Enter the details of the SUV");
                                                    System.out.println("");
                                                    createCar("SUV");
                                                    break;

                                            }
                                            break;

                                        case 2: //Truck
                                            System.out.println("Which type of truck do you want to add? (1) Small Truck | (2) Transport Truck: ");
                                            System.out.println("");
                                            System.out.print("Option: ");
                                            int option4 = input.nextInt();
                                            System.out.println("");
                                            switch (option4) {
                                                case 1: //Small Truck
                                                    System.out.println("Enter the details of the Small Truck");
                                                    System.out.println("");
                                                    createTruck("smalltruck");
                                                    break;

                                                case 2: //Transport Truck
                                                    System.out.println("Enter the details of the Transport Truck: ");
                                                    System.out.println("");
                                                    createTruck("transporttruck");
                                                    break;

                                            }
                                            break;
                                    
                                    
                                    }

                                case 4: //Remove a vehicle
                                    System.out.print("Enter the plate number of the car you want to remove: ");
                                    int plateNumber = input.nextInt();

                                    try {
                                        park.removeVehicle(plateNumber);
                                    } catch (IOException ex) {
                                        System.out.println("Input/output exception");
                                    } catch (ClassNotFoundException ex) {
                                        System.out.println("Class couldn't be found");
                                    }
                                    break;

                                case 5:  //Write up a dailyreport
                                    try {
                                    printFileContent(park.dailyReport());
                                    System.out.println("");
                                } catch (IOException ex) {
                                    System.out.println("Input/output exception");
                                } catch (ClassNotFoundException ex) {
                                    System.out.println("Class couldn't be found");
                                }
                                break;

                                case 6 : // Add a new admin

                                    System.out.println("Enter these details to register the admin into the system: ");
                                    System.out.println("");
                                    System.out.print("Enter name: ");
                                    String name = input.next();
                                    System.out.println("");
                                    loginDetails(name, 0, "newAdmin");
                                    break;

                                case 7: //Exit
                                    System.exit(0);
                                    break;
                            }
                            break;

                            default:
                            System.out.println("Not an option");
                            break;
                    }
                }
            }
        }
    }

    public static Class getClass(String c) {
        c = c.toLowerCase();

        if (c.equals("sports")) {
            return Sports.class;
        } else if (c.equals("suv")) {
            return SUV.class;
        } else if (c.equals("stationwagon")) {
            return StationWagon.class;
        } else if (c.equals("transporttruck")) {
            return TransportTrucks.class;
        } else if (c.equals("smalltruck")) {
            return SmallTrucks.class;
        }
        return null;
    }

    public static void printFileContent(File fileName) {
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void createCar(String type) {
        System.out.print("Enter Color: ");
        String color = input.next();
        System.out.print("Enter seatingCapactiy: ");
        int seatingCapacity = input.nextInt();
        System.out.print("Enter number of doors: ");
        int numberOfDoors = input.nextInt();
        System.out.print("Enter number of tires: ");
        int numberOfTires = input.nextInt();
        System.out.print("Enter number plate: ");
        int numberPlate = input.nextInt();
        System.out.print("Enter daily fee: ");
        int dailyFee = input.nextInt();
        try {
            if (type.toLowerCase().equals("Sports".toLowerCase())) {
                System.out.print("Enter horse power:");
                int horsePower = input.nextInt();
                System.out.println("");
                Sports s = new Sports(color, seatingCapacity, numberOfDoors, horsePower, numberOfTires, numberPlate, dailyFee);
                park.addVehicle(s);
            } else if (type.toLowerCase().equals("SUV".toLowerCase())) {
                System.out.print("Enter type of wheel:");
                String typeOfWheel = input.next();
                System.out.println("");
                SUV sss = new SUV(color, seatingCapacity, numberOfDoors, numberOfTires, numberPlate, dailyFee, typeOfWheel);
                park.addVehicle(sss);
            } else {
                System.out.print("Enter loading capacity: ");
                int loadingCapacity = input.nextInt();
                System.out.println("");
                StationWagon ss = new StationWagon(color, seatingCapacity, numberOfDoors, loadingCapacity, numberOfTires, numberPlate, dailyFee);
                park.addVehicle(ss);
            }
        } catch (IOException ex) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
    }

    public static void enterDate(String action) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            System.out.print("Enter starting date (dd/mm/yyyy):");
            String stringDate = input.next();
            Date startDate = formatter.parse(stringDate);
            System.out.print("Enter ending date (dd/mm/yyyy):");
            String stringDate2 = input.next();
            Date endDate = formatter.parse(stringDate2);

            if (action.toLowerCase().equals("display")) {
                System.out.println("");
                park.displayAvailableVehicles(startDate, endDate);
            } else if (action.toLowerCase().equals("type")) {
                System.out.print("Enter desired type (Sports / SUV / StationWagon / TransportTruck / SmallTruck): ");
                String vehicleType = input.next();
                System.out.println("");
                park.displayAvailableVehicles(endDate, endDate, getClass(vehicleType));
            } else if (action.toLowerCase().equals("rent")) {
                System.out.print("Enter number plate of the vehicle you want to rent: ");
                int numberPlate = input.nextInt();
                System.out.println("");
                park.rentVehicle(numberPlate, startDate, endDate, c);
            } else if (action.toLowerCase().equals("book")) {
                System.out.print("Enter number plate of the vehicle you want to book: ");
                int numberPlate = input.nextInt();
                System.out.println("");
                park.bookVehicle(numberPlate, startDate, endDate, c);
            }
        } catch (ParseException ex) {
            System.out.println("Parse exception");
        } catch (IOException ex) {
            System.out.println("input/output exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
    }

    public static void createTruck(String type) {
        System.out.print("Enter loading capacity: ");
        int loadingCapacity = input.nextInt();
        System.out.print("Enter number of tires: ");
        int numberOfTires = input.nextInt();
        System.out.print("Enter number plate: ");
        int numberPlate = input.nextInt();
        System.out.print("Enter daily fee: ");
        int dailyFee = input.nextInt();
        System.out.println("");
        try {
            if (type.equals("smalltruck")) {
                SmallTrucks s = new SmallTrucks(loadingCapacity, numberOfTires, numberPlate, dailyFee);
                park.addVehicle(s);
            } else if (type.equals("transporttruck")) {
                TransportTrucks s = new TransportTrucks(loadingCapacity, numberOfTires, numberPlate, dailyFee);
                park.addVehicle(s);
            }
        } catch (IOException ex) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
    }

    public static boolean loginDetails(String name, int age, String action) {
        System.out.print("Enter email (forexample@gmail.com) : ");
        String email = input.next();
        System.out.print("Enter password: ");
        String password = input.next();
        System.out.println("");
        try {
            if (action.equals("new")) {
                Customer c1 = new Customer(name, age, email, password);
                park.addCustomer(c1);
            } else if (action.equals("registered")) {
                c = park.getCustomer(email, password);
                while (c == null) {
                    System.out.println("Invalid user");
                    System.out.print("(1) Retry or (2) Forgot password: ");
                    int wrong = input.nextInt();
                    System.out.println("");
                    switch (wrong) {
                        case 1:
                            System.out.print("Enter password again: ");
                            password = input.next();
                            c = park.getCustomer(email, password);
                            break;
                        case 2:
                            System.out.println("Security Questions: ");
                            System.out.println("");
                            System.out.print("Enter your full name: ");
                            name = input.next();
                            System.out.print("Enter your age when you registered : ");
                            age = input.nextInt();
                            c = park.changePassword(name, age, email);
                            break;
                    }
                }
            } else if (action.equals("Admin")) {
                return park.checkAdmin(email, password);
            } else if (action.equals("newAdmin")) {
                Admin a = new Admin(name, email, password);
                park.addAdmin(a);
            }
        } catch (IOException ex) {
            System.out.println("input/output exception");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not foudn exception");
        }
        return true;
    }
}
