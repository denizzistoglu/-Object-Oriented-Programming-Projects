/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mid2practice;

/**
 *
 * @author deniz
 */
public abstract class oral extends presentation {
    protected int weekday;
    protected int timeslot;
    
    public oral(){
        
    }
    public oral(String presenter1, String title, int day1, int time1){
        super(presenter1,title);
        
    }
    public String toString(){
        return titleofthepresentation+" given by "+Presentername;
    }
    public abstract String howtopresent();
    
    
}
