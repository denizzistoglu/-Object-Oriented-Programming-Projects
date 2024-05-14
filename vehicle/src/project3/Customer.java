package project3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public class Customer implements Serializable {

    private transient Scanner input = new Scanner(System.in);
    private static transient ArrayList emailList = new ArrayList<String>();
    private String name;
    private int age;
    private String email;
    private String password;

    public Customer(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        while(emailList.contains(email)){
            System.out.println("Email already inuse, please chose another one");
            System.out.println("Enter new email: " );
            email = input.next();
        }
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " | Age: " + this.age + " | Email: " + this.email;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public static ArrayList getEmailList() {
        return emailList;
    }
    
    
    

}
