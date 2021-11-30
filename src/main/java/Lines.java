import java.util.*;

public class Lines{

    ArrayList<Line> lines;

    public Lines(){
        lines = new ArrayList<>();
    }

    public void updateReachable(Vector<LineName> lines, StopName stop, Time time){
        for (LineName l :lines) {
            if (!this.lines.contains(new Line(l)))
                this.lines.add(new Line(l));
        }
        for (Line l:this.lines) {
            l.updateReachable(stop, time);
        }

    }

    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time){

    }

    private Line getLineByName(LineName name){
        for (Line l: lines) {
            if (l.equals(new Line(name))){
                return l;
            }
        }
        return new Line("");
    }
}
