package de.co.ret;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DailyGreetingTest
{

    private DailyGreeting instance;
    private Clock clockMock;

    @BeforeEach
    void setUp() {
        clockMock = mock(Clock.class);
        instance = new DailyGreeting(clockMock, ZoneId.of("CET"));
    }

    @Test
    public void should_greet_on_week_day()
    {
        when(clockMock.instant()).thenReturn(Instant.parse("2022-02-03T00:00:00Z"));
        assertThat(instance.getGreeting()).isEqualTo("Good day, sir!");
    }

    @Test
    void should_greet_for_the_weekend_on_friday() {
        when(clockMock.instant()).thenReturn(Instant.parse("2022-02-04T00:00:00Z"));
        assertThat(instance.getGreeting()).isEqualTo("Weekend is coming, yay!");
    }

    @Test
    void should_greet_merry_christmas_on_24th_of_december() {
        when(clockMock.instant()).thenReturn(Instant.parse("2021-12-24T00:00:00Z"));
        assertThat(instance.getGreeting()).isEqualTo("Merry Christmas!");
    }

    @Test
    void should_throw_exception_whan_adding_null_as_matcher() {
        assertThatCode(() -> instance.addDateMatcher(null, "Hello"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_throw_exception_whan_adding_null_as_greeting() {
        assertThatCode(() -> instance.addDateMatcher(new DayAndMonthMatcher(8, Month.SEPTEMBER), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_allow_adding_a_birthday() {
        instance.addDateMatcher(new DayAndMonthMatcher(8, Month.SEPTEMBER), "Happy Birthday, Jan!");
        when(clockMock.instant()).thenReturn(Instant.parse("2022-09-08T00:00:00Z"));

        assertThat(instance.getGreeting()).isEqualTo("Happy Birthday, Jan!");
    }

    @Test
    void should_allow_adding_two_birthdays_and_match_first() {
        instance.addDateMatcher(new DayAndMonthMatcher(8, Month.OCTOBER), "Happy Birthday, Nora!");
        instance.addDateMatcher(new DayAndMonthMatcher(8, Month.SEPTEMBER), "Happy Birthday, Jan!");
        when(clockMock.instant()).thenReturn(Instant.parse("2022-09-08T00:00:00Z"));

        assertThat(instance.getGreeting()).isEqualTo("Happy Birthday, Jan!");
    }

    @Test
    void should_allow_adding_two_birthdays_and_match_second() {
        instance.addDateMatcher(new DayAndMonthMatcher(8, Month.OCTOBER), "Happy Birthday, Nora!");
        instance.addDateMatcher(new DayAndMonthMatcher(8, Month.SEPTEMBER), "Happy Birthday, Jan!");
        when(clockMock.instant()).thenReturn(Instant.parse("2022-10-08T00:00:00Z"));

        assertThat(instance.getGreeting()).isEqualTo("Happy Birthday, Nora!");
    }

}
