import java.util.*;

public interface ILine {
    public void updateReachable(StopName stop, Time time);
    public void updateReachable(Vector<LineName> lines, StopName stop, Time time);
    public StopName updateCapacityAndGetPreviousStop(LineName line, StopName stop, Time time);
}
