import java.util.Objects;

public class TimeDiff {
    public int timeDiff;

    public TimeDiff(int timeDiff){
        this.timeDiff = timeDiff;
    }

    public TimeDiff(TimeDiff timeDiff){
        this.timeDiff = timeDiff.timeDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        int timeDiff1 = (Integer) o;
        return timeDiff == timeDiff1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeDiff);
    }
}
