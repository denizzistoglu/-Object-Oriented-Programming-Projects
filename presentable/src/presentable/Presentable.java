/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mid2practice;

import java.util.ArrayList;

/**
 *
 * @author deniz
 */
public interface Presentable {
    
    public void printAll( ArrayList<Presentable> myList);
        
    public void remove( ArrayList<Presentable> myList, int i);
        
    public void printPresTypes( ArrayList<Presentable> myList);
    
}
