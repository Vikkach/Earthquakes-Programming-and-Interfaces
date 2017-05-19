package earthquakes.programming.and.interfaces;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {

    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry quakeData1 : quakeData) {
            if (quakeData1.getMagnitude() > magMin) {
                answer.add(quakeData1);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry entry : quakeData) {
            Location entryLocation = entry.getLocation();
            double distEntryFrom = from.distanceTo(entryLocation);
            if (distEntryFrom < distMax) {
                answer.add(entry);
            }

        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry entry : quakeData){
            if (entry.getDepth() > minDepth && entry.getDepth() < maxDepth){
                answer.add(entry);
            }
        }
        return answer;
    }
    //String where has one of three values: "start","end", or "any"
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for(QuakeEntry entry : quakeData){
            String title = entry.getInfo();
            if (where.equals("start") && title.substring(0, phrase.length()).equals(phrase)){
                answer.add(entry);
            } if (where.equals("end") && title.substring(title.length() - phrase.length(), title.length()).equals(phrase)){
                answer.add(entry);
            } if (where.equals("any") && title.contains(phrase)){
                answer.add(entry);
            }
        }
        return answer;
    }
    
   public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        String where = "start";
        String phrase = "Explosion";
        ArrayList<QuakeEntry> filteredList = filterByPhrase(list, where, phrase);
        for(QuakeEntry entry : filteredList){
            System.out.println(entry.getDepth() + " " + entry.getMagnitude() + " " + entry.getInfo());
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase + " at " + where);
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double minDepth = -10000.0;
        double maxDepth = -5000.0;
        ArrayList<QuakeEntry> filteredList = filterByDepth(list, minDepth, maxDepth);
        for(QuakeEntry entry : filteredList){
            System.out.println(entry.getDepth() + " " + entry.getMagnitude() + " " + entry.getInfo());
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria");
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double magMin = 5.0;
        ArrayList<QuakeEntry> filteredList = filterByMagnitude(list, magMin);
        for (QuakeEntry filterEntry : filteredList) {
            System.out.println(filterEntry);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria");

    }

    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        double distMax = 1000000.0;
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> filteredList = filterByDistanceFrom(list, distMax, city);
        for(QuakeEntry entry : filteredList){
            System.out.println(entry.getInfo());
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria");

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

}
