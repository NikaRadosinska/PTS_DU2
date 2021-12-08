import org.javatuples.Quartet;
import org.javatuples.Triplet;
import java.util.*;

public class LineSegmentsStore {
    Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>> linesSegmentsInfo;
    Stops stops;

    public LineSegmentsStore(Vector<Quartet<Integer, Map<Integer,Integer>, Integer, String>> linesSegmentsInfo, Stops stops){
        this.linesSegmentsInfo = linesSegmentsInfo;
        this.stops = stops;
    }

    public LineSegment getLineSegment(LineName lineName, int index){
        TimeDiff timeToNextStop = new TimeDiff(linesSegmentsInfo.get(index).getValue0());
        HashMap<Time, Integer> numberOfPassengers = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry :linesSegmentsInfo.get(index).getValue1().entrySet()) {
            numberOfPassengers.put(new Time(entry.getKey()), entry.getValue());
        }
        int capacity = linesSegmentsInfo.get(index).getValue2();
        Stop stop = stops.getStopByName(new StopName(linesSegmentsInfo.get(index).getValue3()));
        if (stop == null)
            throw new NoSuchStopNameException();
        return new LineSegment(stop, timeToNextStop, numberOfPassengers, capacity, lineName);
    }
}
