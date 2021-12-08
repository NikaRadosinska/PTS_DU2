import java.util.*;
import org.javatuples.*;

public class ConnectionSearch {

    private Lines lines;
    private Stops stops;

    private ConnectionData connectionData;

    public ConnectionSearch(Lines lines, Stops stops){
        this.lines = lines;
        this.stops = stops;
        timesAtStopsTheWay = new ArrayList<>();
        stopNamesTheWay = new ArrayList<>();
    }

    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        subSearch(from, to ,time);
        if (connectionData == null)
            throw new NoSuchWayException();
        clear();
        return connectionData;
    }

    private void subSearch(StopName from, StopName to, Time time){
        if (connectionData != null)
            return;

        if (stops.getReachableAt(to).getValue1() != null){
            addStop(from, time);
            saveTheWay();
            return;
        }

        lines.updateReachable(stops.getLines(from), from, time);
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

    public void clear(){

    }

    ArrayList<Time> timesAtStopsTheWay;
    ArrayList<StopName> stopNamesTheWay;

    public void addStop(StopName stopName, Time time){
        stopNamesTheWay.add(stopName);
        timesAtStopsTheWay.add(time);
    }

    public void removeLastStop(){
        stopNamesTheWay.remove(stopNamesTheWay.size() - 1);
        timesAtStopsTheWay.remove(stopNamesTheWay.size() - 1);
    }

    public void saveTheWay(){
        Vector<Quartet<LineName, StopName, StopName, Time>> partLineFromStopTillTime = new Vector<>();
        LineName currentLine = stops.getReachableAt(stopNamesTheWay.get(1)).getValue0();
        StopName fromStop = stopNamesTheWay.get(0);
        for (int i = 1; i < stopNamesTheWay.size(); i++){
            if (!stops.getReachableAt(stopNamesTheWay.get(1)).getValue0().equals(currentLine)){
                partLineFromStopTillTime.add(new Quartet<>(currentLine, fromStop, stopNamesTheWay.get(i),timesAtStopsTheWay.get(i)));
                currentLine = stops.getReachableAt(stopNamesTheWay.get(i)).getValue0();
                fromStop = stopNamesTheWay.get(i - 1);
            }
        }
        if (partLineFromStopTillTime.size() == 0){
            partLineFromStopTillTime.add(new Quartet<>(currentLine, fromStop, stopNamesTheWay.get(1), timesAtStopsTheWay.get(1)));
        }
        connectionData = new ConnectionData(timesAtStopsTheWay.get(0), partLineFromStopTillTime);
    }
}
