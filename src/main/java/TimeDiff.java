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
        TimeDiff timeDiff1 = (TimeDiff) o;
        return timeDiff == timeDiff1.timeDiff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeDiff);
    }
}
