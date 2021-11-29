import java.util.Objects;
import java.util.Optional;
import java.util.Vector;

public class Stop {
    private StopName name;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public Stop(StopName name){
        this.name = new StopName(name);
    }

    public Stop(String name){
        this.name = new StopName(name);
    }

    public StopName getName(){
        return name;
    }

    public void updateReachableAt(Time time, Optional<LineName> line){
        reachableAt = Optional.of(new Time(time));
        line.ifPresent(lineName -> lines.add(lineName));

    }

    public LineNameAndTime getReachableAt() {

    }

    public Vector<LineName> getLines() {
        return lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return name.equals(stop.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, reachableAt, reachableVia, lines);
    }
}
