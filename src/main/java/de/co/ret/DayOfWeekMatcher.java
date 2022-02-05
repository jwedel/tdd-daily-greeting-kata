package de.co.ret;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekMatcher implements LocalDateMatcher {
    private DayOfWeek dayOfWeek;

    public DayOfWeekMatcher(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean matches(LocalDate currentDate) {
        return this.dayOfWeek.equals(currentDate.getDayOfWeek());
    }
}
