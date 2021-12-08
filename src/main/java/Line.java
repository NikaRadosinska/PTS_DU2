import java.util.*;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import javax.swing.text.Segment;

//Linka ako napr 83
public class Line {
    private LineName name;
    private Vector<Time> startingTimes; //Kedy odchadzaju busy z 1. zastavky
    private StopName firstStop;
    private ArrayList<LineSegment> lineSegments;

    private LineSegmentsStore lineSegmentsStore;

    public Line(LineName name, StopName firstStop,Vector<Time> startingTimes, LineSegmentsStore lineSegmentsStore) {
        this.name = new LineName(name);
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;
        this.lineSegmentsStore = lineSegmentsStore;
        lineSegments = new ArrayList<>();
    }

    public LineName getLineName(){
        return name;
    }

    public void updateReachable(StopName fromStopName, Time time){
        int numOfSeg = 0;
        Time fromTime;
        if (firstStop.equals(fromStopName)){
            for (Time startingTime:startingTimes) {
                if (time.getTime() <= startingTime.getTime()){
                    fromTime = startingTime;
                    updateReachableInStops(fromTime, 0);
                    return;
                }
            }
        }
        else {
            TimeDiff timeFromFirstStopToFromStopName = new TimeDiff(0);
            boolean foundStop = false;
            LineSegment ls = getLineSegment(0);
            while (ls != null){
                Pair<StopName, Time> nextStopAndTime = ls.nextStop(timeFromFirstStopToFromStopName);
                timeFromFirstStopToFromStopName.setTime(nextStopAndTime.getValue1().getTime());
                if (nextStopAndTime.getValue0().equals(fromStopName)){
                    foundStop = true;
                    break;
                }
                numOfSeg++;
                ls = getLineSegment(numOfSeg);
            }
            if (!foundStop){
                throw new StopNotFoundInLineException();
            }
            for (Time startingTime:startingTimes) {
                if (time.getTime() <= startingTime.getTime() + timeFromFirstStopToFromStopName.getTime()){
                    fromTime = new Time(startingTime.getTime() + timeFromFirstStopToFromStopName.getTime());
                    updateReachableInStops(fromTime, numOfSeg + 1);
                    return;
                }
            }
        }
    }

    private void updateReachableInStops(Time fromTime, int numOfSegment){
        LineSegment currentLineSegment = getLineSegment(numOfSegment);
        if (currentLineSegment == null){
            return;
        }
        Triplet<Time, StopName, Boolean> nextTimeStopAndCanUse = currentLineSegment.nextStopAndUpdateReachable(fromTime);
        if (nextTimeStopAndCanUse.getValue2())
            updateReachableInStops(nextTimeStopAndCanUse.getValue0(), numOfSegment + 1);
    }

    public StopName updateCapacityAndGetPreviousStop(StopName stop, Time time){
        if (stop.equals(firstStop))
            return null;
        for (int i = 0; i < lineSegments.size(); i++) {
            LineSegment lineSegment = getLineSegment(i);
            if(lineSegment.nextStop(time).getValue0().equals(stop)){
                lineSegment.incrementCapacity(time);
                return (i == 0) ? (firstStop) : (getLineSegment(i-1).nextStop(new Time(0)).getValue0());
            }
        }
        throw new NoSuchStopNameException();
    }

    private LineSegment getLineSegment(int index){
        if (lineSegments.size() <= index){
            for (int i = lineSegments.size(); i <= index; i++){
                LineSegment newLineSegment = lineSegmentsStore.getLineSegment(name, i);
                if (newLineSegment == null)
                    return null;
                lineSegments.add(newLineSegment);
            }
        }
        return lineSegments.get(index);
    }
}
