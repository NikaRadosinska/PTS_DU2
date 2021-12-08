import org.javatuples.*;

import java.util.*;

public class ConnectionData {
    public Time startTime;
    public TimeDiff duration;
    public Vector<Quartet<LineName, StopName, StopName, Time>> partLineFromStopTillTime;

    public ConnectionData(Time startTime, Vector<Quartet<LineName, StopName, StopName, Time>> partLineFromStopTillTime){
        this.startTime = startTime;
        this.partLineFromStopTillTime = partLineFromStopTillTime;
        duration = new TimeDiff(partLineFromStopTillTime.get(partLineFromStopTillTime.size()-1).getValue3().getTime() - startTime.getTime());
    }
}
