import java.util.Objects;

public class Time {
    private int time;

    public Time(Time time){
        this.time = time.time;
    }

    public Time(int time){
        this.time = time;
    }

    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time1 = (Time) o;
        return time == time1.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
