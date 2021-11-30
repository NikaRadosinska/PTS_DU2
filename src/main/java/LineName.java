import java.util.Objects;

public class LineName {
    public String name;

    public LineName(String name){
        this.name = name;
    }
    public LineName(LineName lineName){
        this.name = lineName.name;
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
