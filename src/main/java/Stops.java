import java.util.*;
import org.javatuples.*;
public class Stops {

    private StopsStore stopsStore;
    private ArrayList<Stop> stops;

    public Stops(StopsStore stopsStore){
        this.stops = new ArrayList<>();
        this.stopsStore = new StopsStore();
    }

    public Optional<Pair<StopName,Time>> earliestReachableStopAfter(Time time){

    }

    public boolean setStartingStop(StopName stop, Time time){
        Stop startingStop = getStopByName(stop);
        startingStop.updateReachableAt(time, Optional.empty());
        return true;
    }

    public Vector<LineName> getLines(StopName stop){
        return getStopByName(stop).getLines();
    }

    public Pair<LineName, Time> getReachableAt(StopName stop){
        return getStopByName(stop).getReachableAt();
    }

    private Stop getStopByName(StopName stopName){
        for (Stop s: stops) {
            if (s.getStopName().equals(stopName)){
                return s;
            }
        }
        Stop retStop = stopsStore.getStopByName(stopName);
        stops.add(retStop);
        return retStop;
    }
}
