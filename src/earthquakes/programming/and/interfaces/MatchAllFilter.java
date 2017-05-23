/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquakes.programming.and.interfaces;

import java.util.ArrayList;

/**
 *
 * @author New
 */
public class MatchAllFilter implements Filter{
    
    private ArrayList<Filter> listOfFilters ;
    
    public MatchAllFilter(){
        listOfFilters = new ArrayList<>();
        
    }
    
    public void addFilter(Filter f){
        listOfFilters.add(f);
    }
    
    @Override
    public boolean satisfies(QuakeEntry qe){
        for (Filter f : listOfFilters) {
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String name = "";
        for (Filter f :  listOfFilters){
            name = name + " " + f.getName();
        }
        return name;
    }
    
}
