package com.datnek.elearning.lib.common.helper;

import java.util.Date;

public class DateHelper {
    /**
     * check if the first date is before the second date
     * @param dateBefore date before
     * @param dateAfter date after
     * @return true if dateBefore is before false otherwise
     */
    public static boolean isDateCorrect(Date dateBefore, Date dateAfter){
        if (dateBefore == null || dateAfter == null) return false;
        return dateBefore.before(dateAfter);
    }
}
