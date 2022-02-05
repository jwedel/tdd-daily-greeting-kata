package de.co.ret;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
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
}
