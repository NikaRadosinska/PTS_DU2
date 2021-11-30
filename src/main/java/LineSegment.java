import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;

public class LineSegment {
    private TimeDiff timeToNextStop;
    private Map<Time, Integer> numberOfPassengers;
    private int capacity;
    private LineName name;

    private Stop nextStop;

    public LineSegment(Stop nextStop){
        this.nextStop = nextStop;
    }

    public Pair<StopName, Time> nextStop(Time startTime){
        return new Pair<>(nextStop.getName(), new Time(startTime.time + timeToNextStop.timeDiff));
    }

    public Triplet<Time, StopName, Boolean> nextStopAndUpdateReachable(Time startTime){
        Triplet<Time, StopName, Boolean> res = new Triplet<>(new Time(startTime.time + timeToNextStop.timeDiff),nextStop.getName(),numberOfPassengers.get(startTime) < capacity) ;
        if (res.getValue2()){
            nextStop.updateReachableAt(startTime, Optional.ofNullable(name));
        }
        return res;
    }

    public void incrementCapacity(Time startTime){
        updateNumberOfPassengers(startTime);
        numberOfPassengers.put(startTime, numberOfPassengers.get(startTime) + 1);
    }

    private void updateNumberOfPassengers(Time key){
        if (!numberOfPassengers.containsKey(key)){
            numberOfPassengers.put(key, 0);
        }
    }
}
