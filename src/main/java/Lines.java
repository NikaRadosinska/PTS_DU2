import java.util.*;

public class Lines{

    LinesStore linesStore;
    ArrayList<Line> lines;

    public Lines(LinesStore linesStore){
        this.linesStore = linesStore;
        lines = new ArrayList<>();
    }

    public void updateReachable(Vector<LineName> lines, StopName stop, Time time){
        for (LineName name:lines) {
            getLineByName(name).updateReachable(stop, time);
        }
    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){
        return getLineByName(line).updateCapacityAndGetPreviousStop(stop, time);
    }

    private Line getLineByName(LineName lineName){
        for (Line l: lines) {
            if (l.getLineName().equals(lineName)){
                return l;
            }
        }
        Line retLine = linesStore.getLineByName(lineName);
        lines.add(retLine);
        return retLine;
    }
}
