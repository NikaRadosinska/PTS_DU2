import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.util.*;

public class LinesStore {
    HashMap<String, Pair<String ,Vector<Integer>>> allLines;
    HashMap<String, Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>>> allLineSegments;
    Stops stops;

    public LinesStore(HashMap<String, Pair<String ,Vector<Integer>>> allLines, HashMap<String, Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>>> allLineSegments, Stops stops){
        this.allLines = allLines;
        this.allLineSegments = allLineSegments;
        this.stops = stops;
    }

    public Line getLineByName(LineName lineName){
        String lineNameString = lineName.getName();

        if (!allLines.containsKey(lineNameString))
            throw new NoSuchLineNameException();

        StopName firstStop = new StopName(allLines.get(lineNameString).getValue0());
        Vector<Time> startingTimes = new Vector<>();
        for (int time:allLines.get(lineNameString).getValue1()) {
            startingTimes.add(new Time(time));
        }
        return new Line(lineName, firstStop, startingTimes, new LineSegmentsStore(allLineSegments.get(lineNameString), stops));
    }
}
