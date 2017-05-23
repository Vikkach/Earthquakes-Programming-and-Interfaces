package earthquakes.programming.and.interfaces;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter fMagn = new MagnitudeFilter(4.0, 5.0);
        ArrayList<QuakeEntry> filteredByMagn  = filter(list, fMagn); 
        Filter fDepth = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> filteredByDepth  = filter(filteredByMagn, fDepth); 
        //Location city = new Location(35.42, 139.43);
        //Filter fDistance = new DistanceFilter(city, 10000000);
        //ArrayList<QuakeEntry> filteredByDistance  = filter(list, fDistance); 
        //Filter fPhrase = new PhraseFilter("end", "Japan");
        //ArrayList<QuakeEntry> filteredByPhrase  = filter(filteredByDistance, fPhrase); 
        for (QuakeEntry qe: filteredByDepth) { 
            System.out.println(qe);
        } 
        System.out.println(filteredByDepth.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
         EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter fMagn = new MagnitudeFilter(0.0, 2.0);
        maf.addFilter(fMagn);
        Filter fDepth = new DepthFilter(-100000.0, -10000.0);
        maf.addFilter(fDepth);
        Filter fPhrase = new PhraseFilter("any", "a");
        maf.addFilter(fPhrase);
        ArrayList<QuakeEntry> filteredList  = filter(list, maf);
        for (QuakeEntry qe : filteredList){
            System.out.println(qe);
        }
        System.out.println(filteredList.size());
        System.out.println("Filters used are:" + maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter fMagn = new MagnitudeFilter(0.0, 3.0);
        maf.addFilter(fMagn);
        Location city = new Location(36.1314, -95.9372);
        Filter fDistance = new DistanceFilter(city, 10000000);
        maf.addFilter(fDistance);
        Filter fPhrase = new PhraseFilter("any", "Ca");
        maf.addFilter(fPhrase);
        ArrayList<QuakeEntry> filteredList  = filter(list, maf);
        for (QuakeEntry qe : filteredList){
            System.out.println(qe);
        }
        System.out.println(filteredList.size());
    }

}
