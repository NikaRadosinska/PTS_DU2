import java.util.*;

public class StopsStore {
    HashMap<String, Vector<String>> allStops;

    public StopsStore(HashMap<String, Vector<String>> allStops){
        this.allStops = allStops;
    }

    public Stop getStopByName(StopName stopName){
        String stopNameString = stopName.getName();

        if (!allStops.containsKey(stopNameString))
            throw new NoSuchStopNameException();

        Vector<LineName> lineNames = new Vector<>();
        for (String lineName : allStops.get(stopNameString)) {
            lineNames.add(new LineName(lineName));
        }
        return new Stop(stopName, lineNames);

    }
}
