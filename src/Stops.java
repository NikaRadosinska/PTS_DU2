import java.util.*;

public class Stops {

    private ArrayList<Stop> stops;
    private Stop startingStop;

    public Stops(){
        stops = new ArrayList<>();
        startingStop = new Stop("");
    }

    public Optional<StopNameAndTime> earliestReachableStopAfter(Time time){

    }

    public boolean setStartingStop(StopName stop, Time time){
        if (!stops.contains(new Stop(stop))){
            stops.add(new Stop(stop));
        }

        startingStop = getStopByName(stop);
        startingStop.updateReachableAt(time, Optional.empty());
        return true;
    }

    public Vector<LineName> getLines(StopName stop){
        return getStopByName(stop).getLines();
    }

    public LineNameAndTime getReachableAt(StopName stop){

    }

    private Stop getStopByName(StopName stop){
        for (Stop s: stops) {
            if (s.equals(new Stop(stop))){
                return s;
            }
        }
        return new Stop("");
    }
}
