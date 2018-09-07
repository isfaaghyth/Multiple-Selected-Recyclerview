package isfaaghyth.app.multipleselected.bean;

import java.util.List;

/**
 * Created by isfaaghyth on 9/8/18.
 * github: @isfaaghyth
 */

public class Time {
    private String dayName;
    private List<Integer> hours;

    public Time(String dayName, List<Integer> hours) {
        this.dayName = dayName;
        this.hours = hours;
    }

    public String getDayName() {
        return dayName;
    }

    public List<Integer> getHours() {
        return hours;
    }
}
