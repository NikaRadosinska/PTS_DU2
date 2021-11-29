import java.util.*;

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
            StopNameAndTime snt = lineSegments.get(0).nextStop(new Time(10));
            TimeStopNameAndBool tsnb = lineSegments.get(1).nextStopAndUpdateReachable(snt.time);
            // TODO tu pokracovat s krokom cislo 20
        }
    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){

    }
}
