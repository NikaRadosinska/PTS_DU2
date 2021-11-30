import java.util.ArrayList;

public class LinesStore {
    ArrayList<Line> allLines;

    public LinesStore(){

    }

    public Line getLineByName(LineName lineName){
        for (Line l : allLines){
            if (l.getLineName().equals(lineName))
                return l;
        }
        throw new NoSuchLineNameException();
    }
}
