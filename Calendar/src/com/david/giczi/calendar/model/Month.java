package com.david.giczi.calendar.model;

import com.david.giczi.calendar.utils.MonthName;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author GicziD
 */
public class Month {

    private List<Day> days;
    private final MonthName monthName;
    private final int year;
    private int dayCounter = 0;
    private final int daysNumberOfMonth;
    private final List<String> holidays;
    private final List<Integer> holidates;

    public Month(MonthName monthName, List<String> names, int year,
            List<String> holidays, List<Integer> holidates) {

        this.monthName = monthName;
        this.days = new ArrayList<>();
        this.year = year;
        this.holidays = holidays;
        this.holidates = holidates;

        this.days = names.stream().map(x -> new Day(x)).collect(Collectors.toList());

        addLeapYearCorrection();

        this.days.forEach(x -> x.setDayOfWeek(createDayOfWeekValue()));

        addHolidays();

        daysNumberOfMonth = getDaysNumberOfMonth(days);

    }

    public List<Day> getDays() {
        return days;
    }

    public MonthName getMonthName() {
        return monthName;
    }

    public int getYear() {
        return year;
    }

    public int getDaysNumberOfMonth() {
        return daysNumberOfMonth;
    }

    public List<String> getHolidays() {
        return holidays;
    }

    public List<Integer> getHolidates() {
        return holidates;
    }

    private boolean isLeapYear(int year) {

        return new GregorianCalendar().isLeapYear(year);

    }

    private void addLeapYearCorrection() {

        if (isLeapYear(year) && monthName == MonthName.FEB) {

            days.set(23, new Day("Szökőnap"));
            days.set(24, new Day("Mátyás, Jázmin"));
            days.set(25, new Day("Géza,  Cézár, Vanda"));
            days.set(26, new Day("Edina, Viktor, Győző"));
            days.set(27, new Day("Ákos, Bátor, Gábor"));
            days.add(new Day("Elemér, Oszvald, Román"));
        }

    }

    private int createDayOfWeekValue() {

        dayCounter++;
        return new GregorianCalendar(year, MonthName.getMonthIndexByName(monthName),
                dayCounter).get(Calendar.DAY_OF_WEEK);
    }

    private int getDaysNumberOfMonth(List<Day> month) {

        return month.size();
    }

    private void addHolidays() {

        int sundayCounter = 0;
        boolean mothersDay = true;
        int childDayIndex = 0;
        boolean fathersDay = true;

        for (int i = 0; i < days.size(); i++) {

            if (days.get(i).getDayOfWeek() == 1) {

                sundayCounter++;
                childDayIndex = i;

            }

            if (sundayCounter == 1 && monthName == MonthName.MAY && mothersDay) {

                days.get(i).setHolidayName("Anyák napja");
                mothersDay = false;
            }

            if (sundayCounter == 3 && monthName == MonthName.JUN && fathersDay) {

                days.get(i).setHolidayName("Apák napja");
                fathersDay = false;
            }

            for (int j = 0; j < holidates.size(); j++) {

                if (i + 1 == holidates.get(j)) {

                    days.get(i).setHolidayName(holidays.get(j));
                }

            }

        }

        if (monthName == MonthName.MAY) {

            days.get(childDayIndex).setHolidayName("Gyermeknap");

        }

    }

}
