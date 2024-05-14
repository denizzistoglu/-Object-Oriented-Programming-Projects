package project3;
import java.io.Serializable;

public class Admin implements Serializable {
    private String name = "Admin";
    private String email = "Admin@gmail.com";
    private String password = "BunnyInHeadLights123";
    
    
    public Admin(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;                                                     
    }

    public Admin() {
        
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    
    
}

  