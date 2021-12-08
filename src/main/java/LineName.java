import java.util.Objects;

public class LineName {
    private String name;

    public LineName(String name){
        this.name = name;
    }
    public LineName(LineName lineName){
        this.name = lineName.name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineName stopName = (LineName) o;
        return name.equals(stopName.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
