import java.sql.Time;

public class TimeSpan {
    private int hours;
    private int minutes;

    public TimeSpan() {
        this.hours = 0;
        this.minutes = 0;
    }

    public TimeSpan(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public void add(int hours, int minutes) {
        this.hours += hours;
        this.minutes += minutes;
        if (this.minutes > 60) {
            int additionalHours = minutes / 60;
            this.hours = additionalHours;
            this.minutes = this.minutes % 60;
            
        }
    }

    public void add (TimeSpan span) {
        this.add(span.getHours(), span.getMinutes());
    }

    public void subtract(TimeSpan span) {
        this.hours -= span.getHours();
        this.minutes -= span.getMinutes();
        while (minutes < 0) {
            this.hours--;
            this.minutes += 60;
        }
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
