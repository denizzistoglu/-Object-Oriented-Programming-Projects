/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceprovider;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author deniz
 */
public class SimulateSystem {
    
public static ArrayList<Subscriber> subscribersList = new ArrayList<Subscriber>();
public static ArrayList<ServiceProvider> serviceProvidersList = new ArrayList<ServiceProvider>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Simulating a Communication Managment and Payment System...");
        System.out.println("");

        int n = 1, index = 0;
        while (n != 11) {
            MenuPrinter();
            System.out.print("Select Option: ");
            n = input.nextInt();
            switch (n) {
                case 1://Create a new Service Provider
                    System.out.println("");
                    System.out.println("Please input the following attributes to define the service provider:");
                    System.out.println("");
                    System.out.print("Enter service providers name: ");
                    String name = input.next();
                    System.out.print("Enter the desired voice call cost (Dollars per minute): ");
                    double voiceCallCost = input.nextDouble();
                    System.out.print("Enter the desired message cost (Dollars per message): ");
                    double messageCost = input.nextDouble();
                    System.out.print("Enter desired Internet cost (Dollars per MB of internet): ");
                    double internetCost = input.nextDouble();
                    System.out.print("Enter a discount ratio for certain users (0-100%): ");
                    int discountRatio = input.nextInt();
                    System.out.println("");
                    
                    serviceProvidersList.add(new ServiceProvider(name, voiceCallCost, messageCost, internetCost));
                    serviceProvidersList.get(index).setDiscountRatio(discountRatio);
                    
                    System.out.println("Creating new service provider...");
                    System.out.println("Done.");
                    System.out.println("The ID of the Service Provider that you have created is: "+serviceProvidersList.get(index).getP_ID());
                    System.out.println("");

                    index++;
                    break;
                    
                case 2://Create a new Subscriber
                    System.out.println("");
                    if (serviceProvidersList.isEmpty()) {
                        System.out.println("Error : There are currently no available service providers to create any new subscriber");
                        System.out.println("");
                        break;
                    }
                    System.out.println("Please input the following attributes to define the subscriber: ");
                    System.out.println("");
                    System.out.print("Enter subscriber's name: ");
                    String name2 = input.next();
                    System.out.print("Enter subscriber's age: ");
                    int age = input.nextInt();
                    System.out.print("Please enter the ID of the desired service provider: ");
                    int p_id = input.nextInt();

                    int indexP_ID = serviceProviderSearcher(p_id);

                    System.out.print("Enter subscriber's invoice usage limit: ");
                    double usageLimit = input.nextDouble();
                    System.out.println("");

                    subscribersList.add(new Subscriber(name2, age, serviceProvidersList.get(indexP_ID), usageLimit));
                            
                    System.out.println("Creating new subscriber...");
                    System.out.println("Done.");
                    System.out.println("The ID of the Subscriber that you have created is: "+ subscribersList.get(indexP_ID).getID());
                    System.out.println("");

                    
                    break;
                    
                case 3: //Voice Call
                    System.out.println("");
                    if (subscribersList.size() <= 1) {
                        System.out.println("Error : There aren't enough available subscribers to initiate calls");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Please enter the ID of the caller: ");
                    int callerID = input.nextInt();
                    int cIDindex = subscriberSearcher(callerID);

                    System.out.print("Please enter the ID of the reciever: ");
                    int recieverID = input.nextInt();
                    int rIDindex = subscriberSearcher(recieverID);

                    System.out.print("Please enter the length of the call in minutes: ");
                    int time = input.nextInt();

                    subscribersList.get(cIDindex).makeVoiceCall(time, subscribersList.get(rIDindex));
                    System.out.println("Calling ...");
                    System.out.println("Call has been Operated ! ");
                    System.out.println("");

                    break;
                    
                case 4://Messaging
                    System.out.println("");
                    if (subscribersList.size() <= 1) {
                        System.out.println("Error : There aren't enough available subscribers to send any messages");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Please enter the ID of the messenger : ");
                    int senderID = input.nextInt();
                    int sIDindex = subscriberSearcher(senderID);

                    System.out.print("Please enter the ID of the reciever: ");
                    int recieverID2 = input.nextInt();
                    int rIDindex2 = subscriberSearcher(recieverID2);

                    System.out.print("Please enter the amount of the messages: ");
                    int quantity = input.nextInt();

                    subscribersList.get(sIDindex).sendMessage(quantity, subscribersList.get(rIDindex2));
                    
                    System.out.println("Sending the Message ...");
                    System.out.println("Message delivered ! ");
                    System.out.println("");
                    break;
                    
                case 5://Internet
                    System.out.println("");
                    if (subscribersList.isEmpty() || serviceProvidersList.isEmpty()) {
                        System.out.println("Error : There aren't any available subscribers to connect to the internet");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Please enter the ID of the subscriber that wants to connect to the internet : ");
                    int s_id = input.nextInt();
                    int s_idIndex = subscriberSearcher(s_id);

                    System.out.print("Amount of data in MBs: ");
                    double data = input.nextDouble();

                    subscribersList.get(s_idIndex).connectToInternet(data);
                    System.out.println("");
                    
                    break;
                    
                case 6: //Pay Invoice
                    System.out.println("");
                    if (subscribersList.isEmpty() || serviceProvidersList.isEmpty()) {
                        System.out.println("Error : There aren't any available subscribers to pay an invoice");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Enter the ID of the subscriber that wants to pay: ");
                    int s_id2 = input.nextInt();
                    int s_idIndex2 = subscriberSearcher(s_id2);

                    System.out.print("Enter amount to pay in dollars: ");
                    int amount = input.nextInt();

                    subscribersList.get(s_idIndex2).getInvoice().pay(amount);
                    if (!subscribersList.get(s_idIndex2).getInvoice().isLimitedExceeded(subscribersList.get(s_idIndex2).getInvoice().getCurrentSpending())) {
                        subscribersList.get(s_idIndex2).updateStatus();
                    }
                    break;
                    
                case 7: //Change ServiceProvider
                    System.out.println("");
                    if (serviceProvidersList.size() <= 1 || subscribersList.isEmpty()) {
                        System.out.println("Error : There are either no available subscribers to change service providers, or an insufficient amount of service providers");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Enter ID of the subscriber that wants to change service provider: ");
                    int s_id3 = input.nextInt();
                    int s_idIndex3 = subscriberSearcher(s_id3);

                    System.out.print("Enter ID of desired service provider: ");

                    int p_id2 = input.nextInt();
                    int p_idIndex = serviceProviderSearcher(p_id2);

                    subscribersList.get(s_idIndex3).changeServiceProvider(serviceProvidersList.get(p_idIndex));
                    System.out.println("");
                    System.out.println("Service Provider has been changed successfully ! ");
                    System.out.println("");
                    
                    break;
                    
                case 8: //Change Limit  
                    System.out.println("");
                    if (subscribersList.isEmpty() || serviceProvidersList.isEmpty()) {
                        System.out.println("Error : There aren't any available subscribers to change the usage limit");
                        System.out.println("");
                        break;
                    }
                    System.out.print("Enter ID of subscriber that wants to change usage limit: ");
                    int s_id4 = input.nextInt();
                    int s_idIndex4 = subscriberSearcher(s_id4);

                    System.out.print("Enter desired usage limit: ");
                    double newLimit = input.nextDouble();
                    System.out.println("");

                    subscribersList.get(s_idIndex4).getInvoice().changeUsageLimit(newLimit);
                    
                    System.out.println("");
                    System.out.println("The Limit has been changed successfully ! ");
                    System.out.println("");
                    
                    if (!subscribersList.get(s_idIndex4).getInvoice().isLimitedExceeded(subscribersList.get(s_idIndex4).getInvoice().getCurrentSpending())) {
                        subscribersList.get(s_idIndex4).updateStatus();
                    }
                    break;
                    
                case 9: //List all Subscribers
                    System.out.println("");
                    if (subscribersList.isEmpty()) {
                        System.out.println("Error : There aren't any available subscribers to list");
                        System.out.println("");
                        break;
                    }
                    System.out.println("Subscribers List: ");
                    System.out.println("----------------------------------------------------");
                    for (int i = 0; i < subscribersList.size(); i++) {
                        System.out.println("#" + (i + 1) + " " + subscribersList.get(i).getName());
                        System.out.println("- Age: " + subscribersList.get(i).getAge());
                        System.out.println("- ID: " + subscribersList.get(i).getID());
                        System.out.println("- Active: " + subscribersList.get(i).getActivity());
                        System.out.println("- Service Provider (Name): " + subscribersList.get(i).getServiceProvider().getName());
                        System.out.println("- Usage Limit (Invoice): " + subscribersList.get(i).getInvoice().getUsageLimit());
                    }
                    System.out.println("");
                    break;
                    
                case 10: //List all Service Providers 
                    System.out.println("");
                    if (serviceProvidersList.isEmpty()) {
                        System.out.println("Error : There aren't any available service providers to list");
                        System.out.println("");
                        break;
                    }
                    System.out.println("Service Providers List: ");
                    System.out.println("----------------------------------------------------");
                    for (int i = 0; i < serviceProvidersList.size(); i++) {
                        System.out.println("#" + (i + 1) + " " + serviceProvidersList.get(i).getName());
                        System.out.println("- ID: " + serviceProvidersList.get(i).getP_ID());
                        System.out.println("- Voice Call Cost: " + serviceProvidersList.get(i).getVoiceCallCost());
                        System.out.println("- Message Cost: " + serviceProvidersList.get(i).getMessageCost());
                        System.out.println("- Internet Cost: " + serviceProvidersList.get(i).getInternetCost());
                        System.out.println("- Discount Ratio: " + serviceProvidersList.get(i).getDiscountRatio());
                    }
                    System.out.println("");
                    break;
                    
                case 11: //Exit
                    if (serviceProvidersList.size() <= 1 || subscribersList.isEmpty()) {
                        break;
                    }
                    for (int i = 0; i < serviceProvidersList.size(); i++) {
                        System.out.println("");
                        System.out.println("Service Providers: ");
                        System.out.println("#" + (i + 1) + " " + serviceProvidersList.get(i).getName());
                        System.out.println("- ID: " + serviceProvidersList.get(i).getP_ID());
                        System.out.println("Total voice call time: " + serviceProvidersList.get(i).getTotalTime());
                        System.out.println("Total messages: " + serviceProvidersList.get(i).getTotalMessaegs());
                        System.out.println("Total MBs used: " + serviceProvidersList.get(i).getTotalMBs());
                    }

                    System.out.println("------------------------------------------------------");

                    for (int i = 0; i < subscribersList.size(); i++) {
                        System.out.println("Subscribers: ");
                        System.out.println("#" + (i + 1) + " " + subscribersList.get(i).getName());
                        System.out.println("- ID: " + subscribersList.get(i).getID());
                        System.out.println("Current spending: " + subscribersList.get(i).getInvoice().getCurrentSpending());
                        System.out.println("Total spending: " + subscribersList.get(i).getInvoice().getTotalSpending());
                    }

                    System.out.println("------------------------------------------------------");

                    int maxTime = 0,
                     maxMessage = 0,
                     indexTime = 0,
                     indexMessage = 0,
                     indexInternet = 0;
                    double maxInternet = 0;

                    for (int i = 0; i < subscribersList.size(); i++) {
                        if (subscribersList.get(i).getMaxMin() > maxTime) {
                            maxTime = subscribersList.get(i).getMaxMin();
                            indexTime = i;
                        }

                    }
                    for (int i = 0; i < subscribersList.size(); i++) {
                        if (subscribersList.get(i).getMaxMessage() > maxMessage) {
                            maxMessage = subscribersList.get(i).getMaxMessage();
                            indexMessage = i;
                        }

                    }
                    for (int i = 0; i < subscribersList.size(); i++) {
                        if (subscribersList.get(i).getMaxMBs() > maxInternet) {
                            maxInternet = subscribersList.get(i).getMaxMBs();
                            indexInternet = i;
                        }
                    }

                    System.out.println("User with the most time on voice calls: ");
                    System.out.println("- Name: " + subscribersList.get(indexTime).getName());
                    System.out.println("- ID: " + subscribersList.get(indexTime).getID());
                    System.out.println("- Total voice call time: " + maxTime + " minutes");

                    System.out.println("------------------------------------------------------");

                    System.out.println("User with the most sent messages: ");
                    System.out.println("- Name: " + subscribersList.get(indexMessage).getName());
                    System.out.println("- ID: " + subscribersList.get(indexMessage).getID());
                    System.out.println("- Total amount of messages: " + maxMessage + " messages");

                    System.out.println("------------------------------------------------------");

                    System.out.println("User with the most internet usage: ");
                    System.out.println("- Name: " + subscribersList.get(indexInternet).getName());
                    System.out.println("- ID: " + subscribersList.get(indexInternet).getID());
                    System.out.println("- Total MBs of internet used: " + maxInternet + " MBs");
                    break;
                    
                default:
                    System.out.println("Syntax Error. Please enter a valid option");
                    break;
            }
        }
    }

    public static void MenuPrinter() {
        System.out.println("Application Options::");
        System.out.println("------------------------------------");
        System.out.println("1 - Create a new service provider");
        System.out.println("2 - Create a new subscriber");
        System.out.println("3 - Voice Call: A subscriber calls another subscriber");
        System.out.println("4 - Messaging: A subscriber sends a message to another subscriber");
        System.out.println("5 - Internet: A subscriber connects to the internet");
        System.out.println("6 - Pay Invoice: A subscriber pays thier invoice");
        System.out.println("7 - Change Service Provider: A subscriber will change their service provider");
        System.out.println("8 - Change Limit: A subscriber changes thier limit for the invoice");
        System.out.println("9 - List all subscribers");
        System.out.println("10 - List all service Providers");
        System.out.println("11 - Exit");
        System.out.println("");
    }

    public static int serviceProviderSearcher(int p_id) {
        Scanner input = new Scanner(System.in);
        int y = 0;
        for (int z = 0; z < serviceProvidersList.size(); z++) {
            if (serviceProvidersList.get(z).getP_ID() == p_id) {
                y = z;
                return y;
            } else {
                if (z + 1 == serviceProvidersList.size()) {
                    System.out.print("Please enter a valid ID for a service provider: ");
                    p_id = input.nextInt();
                    z = 0;
                }
            }
        }
        return y;
    }

    public static int subscriberSearcher(int s_id) {
        Scanner input = new Scanner(System.in);
        int y = 0;
        for (int z = 0; z < subscribersList.size(); z++) {
            if (subscribersList.get(z).getID() == s_id) {
                y = z;
                return y;
            } else {
                if (z + 1 == subscribersList.size()) {
                    System.out.print("Please enter a valid ID for a subscriber: ");
                    s_id = input.nextInt();
                    subscriberSearcher(s_id);     
                }
            }
        }
        return y;
    }

}

    