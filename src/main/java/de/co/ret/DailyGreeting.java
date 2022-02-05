package de.co.ret;

import java.time.*;

import static java.time.DayOfWeek.FRIDAY;

public class DailyGreeting {
    private Clock clock;
    private ZoneId zoneId;

    public DailyGreeting(Clock clock, ZoneId zoneId) {
        this.clock = clock;
        this.zoneId = zoneId;
    }

    public String getGreeting() {
        LocalDate currentDate = getCurrentDate();

        if (Month.DECEMBER.equals(currentDate.getMonth()) && currentDate.getDayOfMonth() == 24) {
            return "Merry Christmas!";
        } else if (FRIDAY.equals(currentDate.getDayOfWeek())) {
            return "Weekend is coming, yay!";
        } else {
            return "Good day, sir!";
        }
    }

    private LocalDate getCurrentDate() {
        return this.clock
                .instant()
                .atZone(this.zoneId)
                .toLocalDate();
    }
}
