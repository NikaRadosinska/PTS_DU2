import java.util.*;
import org.javatuples.*;

public class ConnectionSearch {

    private Lines lines;
    private Stops stops;

    public ConnectionSearch(){
        lines = new Lines();
        stops = new Stops();
    }

    public ConnectionData search(StopName from, StopName to, Time time){
        if (!stops.setStartingStop(from, time)){
            return null;
        }

        Vector<LineName> stopLines = stops.getLines(from);
        lines.updateReachable(stopLines, from, time);
        Optional<Pair<StopName, Time>> pairStopNameTime = stops.earliestReachableStopAfter(time);
        if (pairStopNameTime.isPresent()){
            Vector<LineName> lineNameVector = stops.getLines(pairStopNameTime.get().getValue0());
            lines.updateReachable(lineNameVector, pairStopNameTime.get().getValue0(), pairStopNameTime.get().getValue1());
        }
        //TODO connectionSearch finds how "stopB" was reached using stops.getReachableAt("StopB"). result is (28, "3")
    }
}
