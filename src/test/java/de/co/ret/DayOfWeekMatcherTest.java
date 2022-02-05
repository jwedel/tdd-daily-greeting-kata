package de.co.ret;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DayOfWeekMatcherTest {
    @Test
    void should_return_true_when_weekday_matches() {
        DayOfWeekMatcher dayOfWeekMatcher = new DayOfWeekMatcher(DayOfWeek.FRIDAY);

        assertThat(dayOfWeekMatcher.matches(LocalDate.parse("2022-02-04"))).isEqualTo(true);
    }

    @Test
    void should_return_false_when_weekday_does_not_match() {
        DayOfWeekMatcher dayOfWeekMatcher = new DayOfWeekMatcher(DayOfWeek.SATURDAY);

        assertThat(dayOfWeekMatcher.matches(LocalDate.parse("2022-02-04"))).isEqualTo(false);
    }
}