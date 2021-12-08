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
        updatedLines = new ArrayList<>();
    }

    public ConnectionData search(StopName from, StopName to, Time time){
        stops.setStartingStop(from, time);
        subSearch(null,from, to ,time);
        if (connectionData == null)
            throw new NoSuchWayException();
        clear();
        return connectionData;
    }

    private void subSearch(LineName throughLine, StopName from, StopName to, Time time){
        if (connectionData != null)
            return;


        Vector<LineName> updateLines = stops.getLines(from);
        updateLines.removeAll(updatedLines);
        if (throughLine != null)
            updateLines.add(throughLine);
        updatedLines.addAll(updateLines);
        lines.updateReachable(updateLines, from, time);

        if (stops.getReachableAt(to).getValue1() != null){
            addStop(from, stops.getReachableAt(from).getValue1());
            addStop(to, stops.getReachableAt(to).getValue1());
            saveTheWay();
            return;
        }

        Optional<Vector<Pair<StopName, Time>>> earliestsStops = stops.earliestReachableStopAfter(time);
        if (earliestsStops.isEmpty())
            return;

        for (LineName lineName:updateLines) {
            for (Pair<StopName,Time> nextStopInfo:earliestsStops.get()) {
                Pair<LineName, Time> nextStopReachableInfo = stops.getReachableAt(nextStopInfo.getValue0());
                if (nextStopReachableInfo.getValue0().equals(lineName)){
                    addStop(from, stops.getReachableAt(from).getValue1());
                    subSearch(lineName, nextStopInfo.getValue0(), to, nextStopReachableInfo.getValue1());
                    removeLastStop();
                    break;
                }
            }
        }
    }

    public void clear(){
        stops.clear();
    }

    ArrayList<Time> timesAtStopsTheWay;
    ArrayList<StopName> stopNamesTheWay;
    ArrayList<LineName> updatedLines;

    public void addStop(StopName stopName, Time time){
        stopNamesTheWay.add(stopName);
        timesAtStopsTheWay.add(time);
    }

    public void removeLastStop(){
        stopNamesTheWay.remove(stopNamesTheWay.size() - 1);
        timesAtStopsTheWay.remove(timesAtStopsTheWay.size() - 1);
    }

    public void saveTheWay(){
        Vector<Quartet<LineName, StopName, StopName, Time>> partLineFromStopTillTime = new Vector<>();
        LineName currentLine = stops.getReachableAt(stopNamesTheWay.get(1)).getValue0();
        StopName fromStop = stopNamesTheWay.get(0);
        for (int i = 1; i < stopNamesTheWay.size(); i++){
            if (!stops.getReachableAt(stopNamesTheWay.get(i)).getValue0().equals(currentLine)){
                partLineFromStopTillTime.add(new Quartet<>(currentLine, fromStop, stopNamesTheWay.get(i-1),timesAtStopsTheWay.get(i-1)));
                currentLine = stops.getReachableAt(stopNamesTheWay.get(i)).getValue0();
                fromStop = stopNamesTheWay.get(i - 1);
            }
        }
        partLineFromStopTillTime.add(new Quartet<>(currentLine, fromStop, stopNamesTheWay.get(stopNamesTheWay.size()-1), timesAtStopsTheWay.get(timesAtStopsTheWay.size()-1)));

        for (int i = partLineFromStopTillTime.size() - 1; i >= 0; i--){
            StopName prevStop = partLineFromStopTillTime.get(i).getValue2();
            while (!prevStop.equals(partLineFromStopTillTime.get(i).getValue1())){
                prevStop = lines.updateCapacityAndGetPreviousStop(partLineFromStopTillTime.get(i).getValue0(), prevStop, stops.getReachableAt(prevStop).getValue1());
            }
        }

        connectionData = new ConnectionData(timesAtStopsTheWay.get(0), partLineFromStopTillTime);
    }
}
