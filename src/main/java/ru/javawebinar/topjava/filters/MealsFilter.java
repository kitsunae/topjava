package ru.javawebinar.topjava.filters;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

public class MealsFilter implements Predicate<Meal>{
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public boolean test(Meal meal) {
        if (timeFrom == null || meal.getTime().isAfter(timeFrom))
            if (timeTo == null || meal.getTime().isBefore(timeTo))
                if (dateFrom == null || meal.getDate().isAfter(dateFrom))
                    if (dateTo == null || meal.getDate().isBefore(dateTo))
                        return true;
        return false;
    }
}
