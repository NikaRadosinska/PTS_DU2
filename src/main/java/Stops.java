import java.util.*;
import org.javatuples.*;
public class Stops {

    private StopsStore stopsStore;
    private ArrayList<Stop> stops;

    public Stops(StopsStore stopsStore){
        this.stops = new ArrayList<>();
        this.stopsStore = stopsStore;
    }

    public Optional<Vector<Pair<StopName,Time>>> earliestReachableStopAfter(Time time){
        Optional<Vector<Pair<StopName,Time>>> res = Optional.of(new Vector<>());
        for (Stop stop: stops) {
            Pair<LineName, Time> stopInfo = stop.getReachableAt();
            if (stopInfo.getValue1() == null)
                continue;
            if (stopInfo.getValue1().getTime() > time.getTime())
                res.get().add(new Pair<>(stop.getStopName(), stopInfo.getValue1()));
        }
        if (res.get().size() == 0)
            return Optional.empty();
        else{
            res.get().sort(new Comparator<Pair<StopName, Time>>() {
                @Override
                public int compare(Pair<StopName, Time> o1, Pair<StopName, Time> o2) {
                    if (o1.getValue1().getTime() < o2.getValue1().getTime())
                        return -1;
                    else if (o1.getValue1().getTime() > o2.getValue1().getTime())
                        return 1;
                    else return 0;
                }
            });
            return res;
        }
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

    public Stop getStopByName(StopName stopName){
        for (Stop s: stops) {
            if (s.getStopName().equals(stopName)){
                return s;
            }
        }
        Stop retStop = stopsStore.getStopByName(stopName);
        stops.add(retStop);
        return retStop;
    }

    public void clear(){
        stops = new ArrayList<>();
    }
}
