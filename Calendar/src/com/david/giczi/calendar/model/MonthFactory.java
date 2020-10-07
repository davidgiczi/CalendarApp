package com.david.giczi.calendar.model;

import java.util.Arrays;

import com.david.giczi.calendar.exceptions.NoSuchMonthException;
import com.david.giczi.calendar.interfaces.*;
import com.david.giczi.calendar.utils.MonthName;



/**
 *
 * @author GicziD
 */
public class MonthFactory implements Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec {

    public static Month createMonth(MonthName month, int year) {

        switch (month) {

            case JAN:

                return new Month(month, Arrays.asList(Jan.NAMES), year,
                        Arrays.asList(Jan.HOLIDAYS), Arrays.asList(Jan.HOLIDATES));

            case FEB:

                return new Month(month, Arrays.asList(Feb.NAMES), year,
                        Arrays.asList(Feb.HOLIDAYS), Arrays.asList(Feb.HOLIDATES));

            case MAR:

                return new Month(month, Arrays.asList(Mar.NAMES), year,
                        Arrays.asList(Mar.HOLIDAYS), Arrays.asList(Mar.HOLIDATES));

            case APR:

                return new Month(month, Arrays.asList(Apr.NAMES), year,
                        Arrays.asList(Apr.HOLIDAYS), Arrays.asList(Apr.HOLIDATES));

            case MAY:

                return new Month(month, Arrays.asList(May.NAMES), year,
                        Arrays.asList(May.HOLIDAYS), Arrays.asList(May.HOLIDATES));

            case JUN:

                return new Month(month, Arrays.asList(Jun.NAMES), year,
                        Arrays.asList(Jun.HOLIDAYS), Arrays.asList(Jun.HOLIDATES));

            case JUL:

                return new Month(month, Arrays.asList(Jul.NAMES), year,
                        Arrays.asList(Jul.HOLIDAYS), Arrays.asList(Jul.HOLIDATES));

            case AUG:

                return new Month(month, Arrays.asList(Aug.NAMES), year,
                        Arrays.asList(Aug.HOLIDAYS), Arrays.asList(Aug.HOLIDATES));

            case SEP:

                return new Month(month, Arrays.asList(Sep.NAMES), year,
                        Arrays.asList(Sep.HOLIDAYS), Arrays.asList(Sep.HOLIDATES));

            case OCT:

                return new Month(month, Arrays.asList(Oct.NAMES), year,
                        Arrays.asList(Oct.HOLIDAYS), Arrays.asList(Oct.HOLIDATES));

            case NOV:

                return new Month(month, Arrays.asList(Nov.NAMES), year,
                        Arrays.asList(Nov.HOLIDAYS), Arrays.asList(Nov.HOLIDATES));

            case DEC:

                return new Month(month, Arrays.asList(Dec.NAMES), year,
                        Arrays.asList(Dec.HOLIDAYS), Arrays.asList(Dec.HOLIDATES));

            default:
                throw new NoSuchMonthException();
        }

    }

}
