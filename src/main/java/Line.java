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

    private LinesStore linesStore;

    public Line(LineName name, StopName firstStop,Vector<Time> startingTimes, LinesStore linesStore) {
        this.name = new LineName(name);
        this.startingTimes = startingTimes;
        this.firstStop = firstStop;
        this.linesStore = linesStore;
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
                    break;
                }
            }
        }
        else {
            TimeDiff timeFromFirstStopToFromStopName = new TimeDiff(0);
            boolean foundStop = false;
            for (LineSegment ls : lineSegments){
                numOfSeg++;
                Pair<StopName, Time> nextStopAndTime = ls.nextStop(timeFromFirstStopToFromStopName);
                timeFromFirstStopToFromStopName.setTime(timeFromFirstStopToFromStopName.getTime() + nextStopAndTime.getValue1().getTime());
                if (nextStopAndTime.getValue0().equals(fromStopName)){
                    foundStop = true;
                    break;
                }
            }
            if (!foundStop){
                throw new StopNotFoundInLineException();
            }
            for (Time startingTime:startingTimes) {
                if (time.getTime() <= startingTime.getTime() + timeFromFirstStopToFromStopName.getTime()){
                    fromTime = new Time(startingTime.getTime() + timeFromFirstStopToFromStopName.getTime());
                    break;
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
        
    }

    private LineSegment getLineSegment(int index){
        if (lineSegments.size() <= index){
            for (int i = lineSegments.size(); i <= index; i++){
                lineSegments.add(linesStore.getLineSegment(name, i));
            }
        }
        return lineSegments.get(index);
    }
}
