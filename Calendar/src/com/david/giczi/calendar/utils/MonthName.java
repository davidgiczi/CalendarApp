package com.david.giczi.calendar.utils;

/**
 *
 * @author GicziD
 */
public enum MonthName {

    JAN(0),
    FEB(1),
    MAR(2),
    APR(3),
    MAY(4),
    JUN(5),
    JUL(6),
    AUG(7),
    SEP(8),
    OCT(9),
    NOV(10),
    DEC(11);

    private final int index;
    private static final MonthName[] months = MonthName.values();

    MonthName(int index) {
        this.index = index;

    }

    public static int getMonthIndexByName(MonthName name) {

        return name.index;
    }

    public static MonthName getMonthNameByIndex(int index) {

        return months[index];
    }

}
