package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected.toString().equals(actual.toString()));

    public static final Meal FIRST = new Meal(1, LocalDateTime.parse("2015-07-12T12:00:00"), "Test meal", 1200);
    public static final Meal SECOND = new Meal(2, LocalDateTime.parse("2015-07-12T13:05:00"), "Test meal 2", 200);
    public static final Meal THIRD = new Meal(3, LocalDateTime.parse("2015-08-12T18:00:00"), "Test meal 3", 1500);
    public static final Meal FORTH = new Meal(4, LocalDateTime.parse("2017-01-01T18:00:00"), "Test meal now", 3000);
}
