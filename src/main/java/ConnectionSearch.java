import java.util.*;
import org.javatuples.*;

public class ConnectionSearch {

    private Lines lines;
    private Stops stops;

    private ArrayList<ConnectionData> potentionalWays;

    public ConnectionSearch(Lines lines, Stops stops){
        this.lines = lines;
        this.stops = stops;
        timesTheWay = new ArrayList<>();
        stopNamesTheWay = new ArrayList<>();
        potentionalWays = new ArrayList<>();
    }

    public ConnectionData search(StopName from, StopName to, Time time){
        subSearch(from, to ,time);

    }

    private void subSearch(StopName from, StopName to, Time time){
        if (from.equals(to)){
            saveTheWay();
            return;
        }

        setStops(from, time);
        Optional<Vector<Pair<StopName, Time>>> earliestsStops = stops.earliestReachableStopAfter(time);
        if (!earliestsStops.isPresent())
            return;

        for (LineName lineName:stops.getLines(from)) {
            for (Pair<StopName,Time> nextStopInfo:earliestsStops.get()) {
                Pair<LineName, Time> nextStopReachableInfo = stops.getReachableAt(nextStopInfo.getValue0());
                if (nextStopReachableInfo.getValue0().equals(lineName)){
                    addStop(from, nextStopReachableInfo.getValue1());
                    subSearch(nextStopInfo.getValue0(), to, nextStopReachableInfo.getValue1());
                    removeLastStop();
                    break;
                }
            }
        }
    }

    private void setStops(StopName from, Time time){
        stops.setStartingStop(from, time);
        lines.updateReachable(stops.getLines(from), from, time);
    }



    ArrayList<Time> timesTheWay;
    ArrayList<StopName> stopNamesTheWay;

    public void addStop(StopName stopName, Time time){
        stopNamesTheWay.add(stopName);
        timesTheWay.add(time);
    }

    public void removeLastStop(){
        stopNamesTheWay.remove(stopNamesTheWay.size() - 1);
        timesTheWay.remove(stopNamesTheWay.size() - 1);
    }

    public void saveTheWay(){

    }

    public void getShortest(){

    }
}
