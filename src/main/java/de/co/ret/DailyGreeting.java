package de.co.ret;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.*;
import java.util.*;

import static java.time.DayOfWeek.FRIDAY;

public class DailyGreeting {
    private final Clock clock;
    private final ZoneId zoneId;
    private final List<GreetingMatcher> matchers;

    public DailyGreeting(Clock clock, ZoneId zoneId) {
        this.clock = clock;
        this.zoneId = zoneId;
        this.matchers = getDefaultMatchers();
    }

    public String getGreeting() {
        LocalDate currentDate = getCurrentDate();

        return matchers.stream()
                .filter(greetingMatcher -> greetingMatcher.matcher.matches(currentDate))
                .findFirst()
                .map(GreetingMatcher::getGreeting)
                .orElse("Good day, sir!");
    }

    private LocalDate getCurrentDate() {
        return this.clock
                .instant()
                .atZone(this.zoneId)
                .toLocalDate();
    }

    public void addDateMatcher(DayAndMonthMatcher matcher, String greeting) {
        if(null == matcher || null == greeting) {
            throw new IllegalArgumentException();
        }
        this.matchers.add(new GreetingMatcher(matcher, greeting));
    }

    private ArrayList<GreetingMatcher> getDefaultMatchers() {
        ArrayList<GreetingMatcher> greetingMatchers = new ArrayList<>();
        greetingMatchers.add(new GreetingMatcher(new DayAndMonthMatcher(24, Month.DECEMBER), "Merry Christmas!"));
        greetingMatchers.add(new GreetingMatcher(new DayOfWeekMatcher(FRIDAY), "Weekend is coming, yay!"));
        return greetingMatchers;
    }

    @Getter
    @RequiredArgsConstructor
    private static class GreetingMatcher {
        private final LocalDateMatcher matcher;
        private final String greeting;
    }
}
