package com.david.giczi.calendar.model;

/**
 *
 * @author GicziD
 */
public class Day {

    private String names;
    private int dayOfWeek;
    private String holidayName = "";

    public Day(String names) {

        this.names = names;

    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {

        this.holidayName = holidayName;

    }

    @Override
    public String toString() {
        return "Day{" + "names=" + names + ", dayOfWeek=" + dayOfWeek
                + ", holidayName=" + holidayName + '}';
    }

}
