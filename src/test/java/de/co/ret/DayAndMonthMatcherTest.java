package de.co.ret;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DayAndMonthMatcherTest {
    @Test
    void should_match_first_of_January_when_correct_date_is_given() {
        DayAndMonthMatcher matcher = new DayAndMonthMatcher(1, Month.JANUARY);
        assertThat(matcher.matches(LocalDate.parse("2022-01-01"))).isEqualTo(true);
    }

    @Test
    void should_not_match_first_of_January_when_other_date_given() {
        DayAndMonthMatcher matcher = new DayAndMonthMatcher(1, Month.JANUARY);
        assertThat(matcher.matches(LocalDate.parse("2022-01-02"))).isEqualTo(false);
    }

}