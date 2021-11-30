import java.util.Objects;

public class Time {
    public int time;

    public Time(Time time){
        this.time = time.time;
    }

    public Time(int time){
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        int time1 = (Integer) o;
        return time == time1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
