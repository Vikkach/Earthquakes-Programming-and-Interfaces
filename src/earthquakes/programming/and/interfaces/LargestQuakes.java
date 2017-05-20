/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package earthquakes.programming.and.interfaces;

import java.util.*;
/**
 *
 * @author Виктория
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        /*for (QuakeEntry entry : list){
            System.out.println(entry);
        }*/
        //System.out.println(indexOfLargest(list) + " " + list.get(indexOfLargest(list)).getMagnitude());
        int howMany = 5;
        ArrayList<QuakeEntry> filteredList = getLargest(list, howMany);
        for (QuakeEntry entry : filteredList){
            System.out.println(entry.getMagnitude() + " " + entry.getInfo());
        }
        System.out.println("read data for " + list.size() + " quakes");
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        double maxMagnitude = 0;
        int index = 0;
        for (int i = 0; i < quakeData.size(); i++){
            if(maxMagnitude < quakeData.get(i).getMagnitude()){
                maxMagnitude = quakeData.get(i).getMagnitude();
                index = i;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> topLargestMagnitude = new ArrayList<>();
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        for (int i = 0; i < howMany; i++){
            double maxMagn = 0;
            int index = 0;
            for (int j = 0; j < copy.size(); j++){
                if (maxMagn < copy.get(j).getMagnitude()){
                    maxMagn = copy.get(j).getMagnitude();
                    index = j;
                }
            }
            topLargestMagnitude.add(copy.get(index));
            copy.remove(index);
            maxMagn = 0;
        }
        return topLargestMagnitude;
    }
}
