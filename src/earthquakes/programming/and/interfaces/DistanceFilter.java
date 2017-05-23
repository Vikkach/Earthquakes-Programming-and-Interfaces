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
public class DistanceFilter implements Filter{
    
    private Location location; 
    private double distanceMax;
    
    public DistanceFilter(Location loc, double max) { 
        location = loc;
        distanceMax = max;
    } 

    @Override
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(location) < distanceMax; 
    } 
    
}
