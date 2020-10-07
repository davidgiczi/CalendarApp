package com.david.giczi.calendar.utils;

/**
 *
 * @author GicziD
 */
public enum DayName {

    SUN(1),
    MON(2),
    TUE(3),
    WED(4),
    THU(5),
    FRI(6),
    SAT(7);

    private final int index;
    private static final DayName[] days = DayName.values();

    private DayName(int index) {
        this.index = index;
    }

    public static int getDayIndexByName(DayName name) {

        return name.index;
    }

    public static DayName getDayNameByIndex(int index) {

        return days[index - 1];
    }

}
