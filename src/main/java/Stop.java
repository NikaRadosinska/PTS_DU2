import java.util.*;
import org.javatuples.*;

public class Stop {
    private StopName name;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public Stop(StopName name, Vector<LineName> lines){
        this.name = new StopName(name);
        this.lines = lines;
    }

    public Stop(String name, Vector<LineName> lines){
        this.name = new StopName(name);
    }

    public StopName getStopName(){
        return name;
    }

    public void updateReachableAt(Time time, Optional<LineName> line){
        reachableAt = Optional.of(new Time(time));
        reachableVia = line;
    }

    public Pair<LineName, Time> getReachableAt() {
        return new Pair<>(reachableVia.orElse(null), reachableAt.orElse(null));
    }

    public Vector<LineName> getLines() {
        return lines;
    }
}
