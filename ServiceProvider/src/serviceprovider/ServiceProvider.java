/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceprovider;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * 
 */
public class ServiceProvider {
private int p_id;
    private static ArrayList ids = new ArrayList();
    private String name;
    private double voiceCallCost;
    private double messageCost;
    private double internetCost;
    private int discountRatio;
    private ArrayList<Subscriber> subscribersList = new ArrayList<Subscriber>();
    private int totalTime;
    private int totalMessages;
    private double totalMBs;

    public ServiceProvider(String name, double voiceCallCost, double messageCost, double internetCost) {
        Random rnd = new Random();
        this.p_id = rnd.nextInt(101) + 500;
        while (ids.contains(p_id)) {
            this.p_id = rnd.nextInt(101)+600;
        }
        ids.add(this.p_id);
        this.name = name;
        this.voiceCallCost = voiceCallCost;
        this.messageCost = messageCost;
        this.internetCost = internetCost;
        this.subscribersList = new ArrayList<Subscriber>();
    }

    public double calculateVoiceCallCost(int minute, Subscriber caller) {
        this.totalTime += minute;
        if (caller.getAge() >= 10 && caller.getAge() < 18) {
            if (minute <= 5) {
                return 0;
            } else {
                return ((minute - 5) * voiceCallCost) * ((double)(100-discountRatio)/100);
            }
        } else if (caller.getAge() >= 65) {
            return (minute * voiceCallCost) * ((double)(100-discountRatio)/100);
        }
        return (minute * voiceCallCost);
    }

    public double calculateMessagingCost(int quantity, Subscriber sender, Subscriber reciever) {
        this.totalMessages += quantity;
        if (sender.getAge() >= 10 && reciever.getAge() < 18) {
            if (quantity <= 10) {
                return 0;
            } else {
                if (sender.getServiceProvider() == reciever.getServiceProvider()) {
                    return ((quantity - 10) * messageCost) * ((double)(100-discountRatio)/100);
                } else {
                    return (quantity - 10) * messageCost;
                }
            }
        }
        if (sender.getServiceProvider() == reciever.getServiceProvider()) {
            return (quantity * messageCost) * ((double)(100-discountRatio)/100);
        }
        return quantity * messageCost;
    }

    public double calculateInternetCost(double amount, Subscriber user) {
        this.totalMBs += amount;
        if (user.getAge() >= 10 && user.getAge() < 18) {
            if (amount <= 5) {
                return 0;
            } else {
                return (amount - 5) * internetCost;
            }
        } else {
            return amount * internetCost;
        }
    }

    public boolean addSubscriber(Subscriber s) { //I'm not sure why this is boolean instead of void but I made it so that it returns "true" whent he subscriber is added
        this.subscribersList.add(s);
        return this.subscribersList.contains(s);
    }

    public boolean removeSubscriber(Subscriber s) { //This will return false if subscriber is removed
        this.subscribersList.remove(s);
        return this.subscribersList.contains(s);

    }

    public void setDiscountRatio(int discountRatio) {
        this.discountRatio = discountRatio;
    }

    public void setMessageCost(double messageCost) {
        this.messageCost = messageCost;
    }

    public void setVoiceCallCost(double voiceCallCost) {
        this.voiceCallCost = voiceCallCost;
    }

    public void setInternetCost(double internetCost) {
        this.internetCost = internetCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountRatio() {
        return this.discountRatio;
    }

    public double getMessageCost() {
        return this.messageCost;
    }

    public double getVoiceCallCost() {
        return this.voiceCallCost;
    }

    public double getInternetCost() {
        return this.internetCost;
    }

    public String getName() {
        return this.name;
    }

    public int getP_ID() {
        return this.p_id;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public double getTotalMBs() {
        return totalMBs;
    }

    public int getTotalMessaegs() {
        return totalMessages;
    }
    
}

    
    

