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
public class DepthFilter implements Filter{
    
    private double depthMin; 
    private double depthMax;
    
    public DepthFilter(double min, double max) { 
        depthMin = min;
        depthMax = max;
    } 

    @Override
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= depthMin && qe.getDepth() <= depthMax; 
    } 
    
}
