import java.util.Objects;

public class StopName {
    public String name;

    public StopName(String name){
        this.name = name;
    }
    public StopName(StopName stopName){
        this.name = stopName.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        String stopName = (String) o;
        return name.equals(stopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
