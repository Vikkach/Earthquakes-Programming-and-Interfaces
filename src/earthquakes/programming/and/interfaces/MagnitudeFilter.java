/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquakes.programming.and.interfaces;

/**
 *
 * @author Виктория
 */
public class MagnitudeFilter implements Filter{
    
     private double magMin; 
     private double magMax;
    
    public MagnitudeFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    @Override
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax; 
    } 
    
    public String getName(){
        return "Magnitude";
    }
    
}
