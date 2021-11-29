import java.util.Vector;

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
    }
}
