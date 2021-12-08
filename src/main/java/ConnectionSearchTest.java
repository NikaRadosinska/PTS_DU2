import org.javatuples.Pair;
import org.javatuples.Quartet;

import java.util.*;

public class ConnectionSearchTest {
    public static void main(String[] args) {
        HashMap<String, Vector<String>> allStops = new HashMap<>();
        allStops.put("00",new Vector<>(Arrays.asList("1down","1up","2down","2up")));
        allStops.put("01",new Vector<>(Arrays.asList("2down","2up")));
        allStops.put("02",new Vector<>(Arrays.asList("3down", "3up")));
        allStops.put("03",new Vector<>(Arrays.asList("3down", "3up","5down", "5up")));
        allStops.put("04",new Vector<>(Arrays.asList("3down", "3up")));
        allStops.put("10",new Vector<>(Arrays.asList("1down","1up")));
        allStops.put("11",new Vector<>(Arrays.asList("4down","4up")));
        allStops.put("12",new Vector<>(Arrays.asList("4down","4up")));
        allStops.put("13",new Vector<>(Arrays.asList("1down","1up")));
        allStops.put("14",new Vector<>(Arrays.asList("4down","4up","3down","3up")));
        allStops.put("20",new Vector<>(Arrays.asList("1down","1up","6down","6up")));
        allStops.put("21",new Vector<>(Arrays.asList("7down","7up","6down","6up")));
        allStops.put("22",new Vector<>(Arrays.asList("6down","6up")));
        allStops.put("23",new Vector<>(Arrays.asList("6down","6up","3down","3up")));
        allStops.put("24",new Vector<>(Arrays.asList("10down","10up")));
        allStops.put("30",new Vector<>(Arrays.asList("1down","1up")));
        allStops.put("31",new Vector<>(Arrays.asList("7down","7up","8down","8up")));
        allStops.put("32",new Vector<>(Arrays.asList("8down","8up")));
        allStops.put("33",new Vector<>(Arrays.asList("5down", "5up")));
        allStops.put("34",new Vector<>(Arrays.asList("10down","10up")));
        allStops.put("40",new Vector<>(Arrays.asList("9down","9up")));
        allStops.put("41",new Vector<>(Arrays.asList("9down","9up","7down","7up")));
        allStops.put("42",new Vector<>(Arrays.asList("9down","9up")));
        allStops.put("43",new Vector<>(Arrays.asList("9down","9up","3down","3up")));
        allStops.put("44",new Vector<>(Arrays.asList("9down","9up","10down","10up")));

        /*
        HashMap<String, Pair<String ,Vector<Integer>>> allLines = new HashMap<>();
        HashMap<String, Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>>> allLineSegments = new HashMap<>();
        allLines.put("1up", new Pair<>("0", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50))));
        allLineSegments.put("1up", new Vector<>(Arrays.asList(new Quartet<>())))
        */

    }
}
