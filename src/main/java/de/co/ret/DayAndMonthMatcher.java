package de.co.ret;

import java.time.LocalDate;
import java.time.Month;

public class DayAndMonthMatcher implements LocalDateMatcher {
    private int dayOfMonth;
    private Month month;

    public DayAndMonthMatcher(int dayOfMonth, Month month) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
    }

    public boolean matches(LocalDate currentDate) {
        return this.month.equals(currentDate.getMonth())
                && this.dayOfMonth == currentDate.getDayOfMonth();
    }
}
