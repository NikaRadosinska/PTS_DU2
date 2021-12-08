import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;

public class LineSegment {
    private TimeDiff timeToNextStop;
    private Map<Time, Integer> numberOfPassengers;
    private int capacity;
    private LineName lineName;

    private Stop nextStop;

    public LineSegment(Stop nextStop, TimeDiff timeToNextStop, Map<Time, Integer> numberOfPassengers, int capacity, LineName lineName){
        this.nextStop = nextStop;
        this.timeToNextStop = timeToNextStop;
        this. numberOfPassengers = numberOfPassengers;
        this.capacity = capacity;
        this.lineName = lineName;
    }

    public Pair<StopName, Time> nextStop(Time startTime){
        return new Pair<>(nextStop.getStopName(), new Time(startTime.getTime() + timeToNextStop.getTime()));
    }

    public Triplet<Time, StopName, Boolean> nextStopAndUpdateReachable(Time startTime){
        updateNumberOfPassengers(startTime);
        Triplet<Time, StopName, Boolean> res = new Triplet<>(new Time(startTime.getTime() + timeToNextStop.getTime()),nextStop.getStopName(),numberOfPassengers.get(startTime) < capacity && controlNextStopReachable(startTime)) ;
        if (res.getValue2()){
            nextStop.updateReachableAt(res.getValue0(), Optional.ofNullable(lineName));
        }
        return res;
    }

    public void incrementCapacity(Time time){
        Time startTime = new Time(time.getTime() - timeToNextStop.getTime());
        updateNumberOfPassengers(startTime);
        numberOfPassengers.put(startTime, numberOfPassengers.get(startTime) + 1);
    }

    private void updateNumberOfPassengers(Time key){
        if (!numberOfPassengers.containsKey(key)){
            numberOfPassengers.put(key, 0);
        }
    }

    private boolean controlNextStopReachable(Time time){
        Pair<LineName, Time> reachableAt = nextStop.getReachableAt();
        return reachableAt.getValue1() == null || (reachableAt.getValue1().getTime() > time.getTime());
    }
}
