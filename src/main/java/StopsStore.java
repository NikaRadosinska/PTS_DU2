import java.util.ArrayList;

public class StopsStore {
    ArrayList<Stop> allStops;

    public StopsStore(){

    }

    public Stop getStopByName(StopName stopName){
        for (Stop s : allStops){
            if (s.getStopName().equals(stopName))
                return s;
        }
        throw new NoSuchStopNameException();
    }
}
