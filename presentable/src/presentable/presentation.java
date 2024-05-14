/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mid2practice;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author deniz
 */
public class presentation implements Presentable , Comparable, Cloneable {
    protected Date date; 
    protected String Presentername;
    protected String titleofthepresentation;
    protected static int numberofpresentations= 0; 
    
    public presentation(){
        date = new Date();
        numberofpresentations++;
    }
    public presentation(String title, String Presentername){
        date = new Date();
        titleofthepresentation = title;
        this.Presentername=Presentername;
        numberofpresentations++;
    }
    public String toString(){
        return titleofthepresentation+" given by "+Presentername;
    }
    @Override
    public void printAll( ArrayList<Presentable> myList){
        for(int i= 0; i<= myList.size()-1; i++){
            System.out.println(myList.get(i).toString());
        }
    }
        
    @Override
    public void remove( ArrayList<Presentable> myList, int i){
        if(myList.size()<=i){
            System.out.println("not a valid integer");
        }else 
            myList.remove(i);
    }
        
    @Override
    public void printPresTypes( ArrayList<Presentable> myList){
        
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof presentation){
           return((presentation) o).compareTo(o);
        }else 
            return 0;
    }
    
}
