import java.util.*;

public class ConnectionData {
    ArrayList<Time> timesTheWay;
    ArrayList<Stop> stopsTheWay;

    public ConnectionData(){
        timesTheWay = new ArrayList<>();
        stopsTheWay = new ArrayList<>();
    }

    public void addStop(Stop stopName, Time time){
        stopsTheWay.add(stopName);
        timesTheWay.add(time);
    }

    public void removeLastStop(){
        stopsTheWay.remove(stopsTheWay.size() - 1);
        timesTheWay.remove(stopsTheWay.size() - 1);
    }

    public void saveTheWay(){

    }

    public void getShortest(){

    }
}
