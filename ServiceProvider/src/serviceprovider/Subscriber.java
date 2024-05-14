/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceprovider;

/**
 *
 * 
 * 
 */
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;

public class Subscriber {
    
    private int s_id;
    private static ArrayList ids = new ArrayList();
    private String name;
    private int age;
    private Boolean isActive = true;
    private ServiceProvider s_provider;
    private Invoice invoice;
    private int maxMin;
    private int maxMessage;
    private double maxMBs;

    public Subscriber(String name, int age, ServiceProvider s_provider, double usageLimit) {
        Random rnd = new Random();
        this.s_id = rnd.nextInt(8000001) + 1000000;
        while (ids.contains(s_id)) {
            this.s_id = rnd.nextInt(8000001) + 1000000;
        }
        ids.add(this.s_id);
        this.name = name;
        this.age = age;
        this.s_provider = s_provider;
        this.invoice = new Invoice(usageLimit, new Date());
    }

    public void updateStatus() {
        if (new Date().getTime() >= invoice.getDate()) {
            isActive = false;
        }
        if (this.invoice.isLimitedExceeded(this.invoice.getCurrentSpending())) {
            System.out.println("");
            System.out.println("- User has exceeded thier usage limit");
            isActive = false;
        } else {
            isActive = true;
        }
    }

    public void makeVoiceCall(int minute, Subscriber reciever) {
        if (maxMin < minute) {
            this.maxMin = minute;
        }

        if (this.isActive && reciever.isActive) {
            invoice.addCost(this.s_provider.calculateVoiceCallCost(minute, this));
            if (this.invoice.isLimitedExceeded(this.s_provider.calculateVoiceCallCost(minute, this))) {
                this.updateStatus();
            }
        } else if (this.isActive && !reciever.isActive) {
            System.out.println("Reciever is not an active subscriber");
        } else {
            System.out.println("Caller is not an active subscriber");
        }
    }

    public void sendMessage(int quantity, Subscriber reciever) {
        if (maxMessage < quantity) {
            this.maxMessage = quantity;
        }

        if (this.isActive && reciever.isActive) {
            invoice.addCost(this.s_provider.calculateMessagingCost(quantity, this, reciever));
            if (this.invoice.isLimitedExceeded(this.s_provider.calculateMessagingCost(quantity, this, reciever))) {
                this.updateStatus();
            }
        } else if (this.isActive && !reciever.isActive) {
            System.out.println("Reciever is not an active subscriber");
        } else {
            System.out.println("Messager is not an active subscriber");
        }

    }

    public void connectToInternet(double amount) {
        if (maxMBs < amount) {
            this.maxMBs = amount;
        }

        if (this.isActive) {
            invoice.addCost(this.s_provider.calculateInternetCost(amount, this));
            if (this.invoice.isLimitedExceeded(this.s_provider.calculateInternetCost(amount, this))) {
                this.updateStatus();
            }

        } else {
            System.out.println("You have to be an active subscriber to connect to the internet");
        }
    }

    public void changeServiceProvider(ServiceProvider s_provider) {
        if (s_provider == s_provider) {
            System.out.println("This person is already subscribed to this service provider");
        } else {
            this.s_provider = s_provider;
        }
    }

    public int getAge() {
        return this.age;
    }

    public ServiceProvider getServiceProvider() {
        return this.s_provider;
    }

    public int getID() {
        return this.s_id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getActivity() {
        return this.isActive;
    }

    public Invoice getInvoice() {
        return this.invoice;
    }

    public double getMaxMBs() {
        return maxMBs;
    }

    public int getMaxMessage() {
        return maxMessage;
    }

    public int getMaxMin() {
        return maxMin;
    }

}

    