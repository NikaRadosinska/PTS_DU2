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

    public StopNameAndTime nextStop(Time startTime){
        StopNameAndTime res = new StopNameAndTime();
        res.stopName = nextStop.getName();
        res.time = new Time(startTime.time + timeToNextStop.timeDiff);
        return res;
    }

    public TimeStopNameAndBool nextStopAndUpdateReachable(Time startTime){
        TimeStopNameAndBool res = new TimeStopNameAndBool();
        res.time = new Time(startTime.time + timeToNextStop.timeDiff);
        res.stopName = nextStop.getName();
        updateNumberOfPassengers(startTime);
        res.bool = numberOfPassengers.get(startTime) < capacity;
        nextStop.updateReachableAt(startTime, Optional.ofNullable(name));
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
