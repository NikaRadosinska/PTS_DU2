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
        allStops.put("13",new Vector<>(Arrays.asList("5down","5up","4down","4up")));
        allStops.put("14",new Vector<>(Arrays.asList("4down","4up")));
        allStops.put("20",new Vector<>(Arrays.asList("1down","1up","6down","6up")));
        allStops.put("21",new Vector<>(Arrays.asList("7down","7up","6down","6up")));
        allStops.put("22",new Vector<>(Arrays.asList("6down","6up")));
        allStops.put("23",new Vector<>(Arrays.asList("6down","6up","5down","5up")));
        allStops.put("24",new Vector<>(Arrays.asList("10down","10up")));
        allStops.put("30",new Vector<>(Arrays.asList("1down","1up")));
        allStops.put("31",new Vector<>(Arrays.asList("7down","7up","8down","8up")));
        allStops.put("32",new Vector<>(Arrays.asList("8down","8up")));
        allStops.put("33",new Vector<>(Arrays.asList("5down", "5up")));
        allStops.put("34",new Vector<>(Arrays.asList("10down","10up")));
        allStops.put("40",new Vector<>(Arrays.asList("9down","9up")));
        allStops.put("41",new Vector<>(Arrays.asList("9down","9up","7down","7up")));
        allStops.put("42",new Vector<>(Arrays.asList("9down","9up")));
        allStops.put("43",new Vector<>(Arrays.asList("9down","9up","5down","5up")));
        allStops.put("44",new Vector<>(Arrays.asList("9down","9up","10down","10up")));


        HashMap<String, Pair<String ,Vector<Integer>>> allLines = new HashMap<>();
        HashMap<String, Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>>> allLineSegments = new HashMap<>();
        allLines.put("1up", new Pair<>("00", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("1up", new Vector<>(Arrays.asList(new Quartet<>(7, new HashMap<>(), 5, "10"), new Quartet<>(9, new HashMap<>(), 5, "20"),new Quartet<>(8, new HashMap<>(), 5, "30"))));
        allLines.put("1down", new Pair<>("30", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("1down", new Vector<>(Arrays.asList(new Quartet<>(8, new HashMap<>(), 5, "20"), new Quartet<>(9, new HashMap<>(), 5, "10"), new Quartet<>(7, new HashMap<>(), 5, "00"))));
        allLines.put("2up", new Pair<>("00", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("2up", new Vector<>(Collections.singletonList(new Quartet<>(3, new HashMap<>(), 5, "01"))));
        allLines.put("2down", new Pair<>("01", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("2down", new Vector<>(Collections.singletonList(new Quartet<>(3, new HashMap<>(), 5, "00"))));
        allLines.put("3up", new Pair<>("02", new Vector<>(Arrays.asList(0,10,20,30,40,50,60,70,80,90,100))));
        allLineSegments.put("3up", new Vector<>(Arrays.asList(new Quartet<>(7, new HashMap<>(), 5, "03"),new Quartet<>(4, new HashMap<>(), 5, "04"))));
        allLines.put("3down", new Pair<>("04", new Vector<>(Arrays.asList(0,10,20,30,40,50,60,70,80,90,100))));
        allLineSegments.put("3down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "03"),new Quartet<>(7, new HashMap<>(), 5, "02"))));
        allLines.put("4up", new Pair<>("11", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("4up", new Vector<>(Arrays.asList(new Quartet<>(8, new HashMap<>(), 5, "12"), new Quartet<>(7, new HashMap<>(), 5, "13"),new Quartet<>(4, new HashMap<>(), 5, "14"))));
        allLines.put("4down", new Pair<>("14", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("4down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "13"), new Quartet<>(7, new HashMap<>(), 5, "12"),new Quartet<>(8, new HashMap<>(), 5, "11"))));
        allLines.put("5up", new Pair<>("03", new Vector<>(Arrays.asList(0,9,18,27,36,45,54,63,72,81,90,99))));
        allLineSegments.put("5up", new Vector<>(Arrays.asList(new Quartet<>(7, new HashMap<>(), 5, "13"), new Quartet<>(9, new HashMap<>(), 5, "23"), new Quartet<>(8, new HashMap<>(), 5, "33"),new Quartet<>(4, new HashMap<>(), 5, "43"))));
        allLines.put("5down", new Pair<>("43", new Vector<>(Arrays.asList(0,9,18,27,36,46,54,63,72,81,90,99))));
        allLineSegments.put("5down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "33"), new Quartet<>(8, new HashMap<>(), 5, "23"), new Quartet<>(9, new HashMap<>(), 5, "13"),new Quartet<>(7, new HashMap<>(), 5, "03"))));
        allLines.put("6up", new Pair<>("20", new Vector<>(Arrays.asList(0,7,14,21,28,35,42,49,56,63,70,77,84,91,98))));
        allLineSegments.put("6up", new Vector<>(Arrays.asList(new Quartet<>(3, new HashMap<>(), 5, "21"), new Quartet<>(8, new HashMap<>(), 5, "22"),new Quartet<>(7, new HashMap<>(), 5, "23"))));
        allLines.put("6down", new Pair<>("23", new Vector<>(Arrays.asList(0,7,14,21,28,35,42,49,56,63,70,77,84,91,98))));
        allLineSegments.put("6down", new Vector<>(Arrays.asList(new Quartet<>(7, new HashMap<>(), 5, "22"), new Quartet<>(8, new HashMap<>(), 5, "21"),new Quartet<>(3, new HashMap<>(), 5, "20"))));
        allLines.put("7up", new Pair<>("21", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("7up", new Vector<>(Arrays.asList(new Quartet<>(8, new HashMap<>(), 5, "31"),new Quartet<>(4, new HashMap<>(), 5, "41"))));
        allLines.put("7down", new Pair<>("41", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("7down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "31"),new Quartet<>(8, new HashMap<>(), 5, "21"))));
        allLines.put("8up", new Pair<>("31", new Vector<>(Arrays.asList(0,10,20,30,40,50,60,70,80,90,100))));
        allLineSegments.put("8up", new Vector<>(Collections.singletonList(new Quartet<>(8, new HashMap<>(), 5, "32"))));
        allLines.put("8down", new Pair<>("32", new Vector<>(Arrays.asList(0,10,20,30,40,50,60,70,80,90,100))));
        allLineSegments.put("8down", new Vector<>(Collections.singletonList(new Quartet<>(8, new HashMap<>(), 5, "31"))));
        allLines.put("9up", new Pair<>("40", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("9up", new Vector<>(Arrays.asList(new Quartet<>(3, new HashMap<>(), 5, "41"), new Quartet<>(8, new HashMap<>(), 5, "42"), new Quartet<>(7, new HashMap<>(), 5, "43"),new Quartet<>(4, new HashMap<>(), 5, "44"))));
        allLines.put("9down", new Pair<>("44", new Vector<>(Arrays.asList(0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100))));
        allLineSegments.put("9down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "43"), new Quartet<>(7, new HashMap<>(), 5, "42"), new Quartet<>(8, new HashMap<>(), 5, "41"),new Quartet<>(3, new HashMap<>(), 5, "40"))));
        allLines.put("10up", new Pair<>("24", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("10up", new Vector<>(Arrays.asList(new Quartet<>(8, new HashMap<>(), 5, "34"),new Quartet<>(4, new HashMap<>(), 5, "44"))));
        allLines.put("10down", new Pair<>("44", new Vector<>(Arrays.asList(0,8,16,24,32,40,48,56,64,72,80,88,96))));
        allLineSegments.put("10down", new Vector<>(Arrays.asList(new Quartet<>(4, new HashMap<>(), 5, "34"),new Quartet<>(8, new HashMap<>(), 5, "24"))));
        StopsStore stopsStore = new StopsStore(allStops);
        Stops stops = new Stops(stopsStore);
        LinesStore linesStore = new LinesStore(allLines, allLineSegments, stops);
        Lines lines = new Lines(linesStore);


        ConnectionSearch connectionSearch = new ConnectionSearch(lines, stops);
        connectionSearch.search(new StopName("24"), new StopName("11"), new Time(0));
        //ostatne vykonane testy tras su vymazane
    }
}
