package earthquakes.programming.and.interfaces;

/**
 * Write a description of class QuakeSortInPlace here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {

    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    public void onePassSortByMagnitude(ArrayList<QuakeEntry> in, int from) {
        int minIdx = getSmallestMagnitude(in, from);
        QuakeEntry qi = in.get(from);
        QuakeEntry qmin = in.get(minIdx);
        in.set(from, qmin);
        in.set(minIdx, qi);
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < 50; i++) {
            int maxIndex = getLargestDepth(in, i);
            if (in.get(maxIndex).getDepth() > in.get(i).getDepth()) {
                QuakeEntry qMax = in.get(maxIndex);
                QuakeEntry qI = in.get(i);
                in.set(i, qMax);
                in.set(maxIndex, qI);
            }
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()) {
                QuakeEntry qI = quakeData.get(i);
                QuakeEntry qI1 = quakeData.get(i + 1);
                quakeData.set(i + 1, qI);
                quakeData.set(i, qI1);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean state = true;
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                state = false;
            }
        }
        return state;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            passes++;
            boolean state = checkInSortedOrder(in);
            if (state == true) {
                break;
            }
        }
        System.out.println(passes + " passes need to sort the elements");
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i = 0; i < in.size(); i++) {
            onePassSortByMagnitude(in, i);
            passes++;
            boolean state = checkInSortedOrder(in);
            if (state == true) {
                break;
            }
        }
        System.out.println(passes + " passes need to sort the elements");
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (int i = 0; i < 55; i++) {
            System.out.println(list.get(i));
        }

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}
