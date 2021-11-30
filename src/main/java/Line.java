import java.util.*;
import org.javatuples.Pair;
import org.javatuples.Triplet;

//Linka ako napr 83
public class Line {
    private LineName name;
    private Vector<Time> startingTimes; //Kedy odchadzaju busy z 1. zastavky
    private StopName firstStop;

    ArrayList<LineSegment> lineSegments;

    public Line(LineName name) {
        this.name = new LineName(name);
        startingTimes = new Vector<>();
        lineSegments = new ArrayList<>();
    }

    public Line(String s) {
        name = new LineName(s);
    }

    public void updateReachable(StopName stop, Time time){
        if (firstStop != stop){
            Pair<StopName, Time> pairStopNameAndTime = lineSegments.get(0).nextStop(new Time(10));
            Triplet<Time, StopName, Boolean> tripletTimeStopNameBool = lineSegments.get(1).nextStopAndUpdateReachable(pairStopNameAndTime.getValue1());
            //TODO no more line segment find out
            //TODO line1 tries if a bus that starts earlier could be used, but finds out there is no such bus and returns
        }
    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){

    }
}
