package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.filters.MealsFilter;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal);

    boolean delete(int id, Integer userId);

    Meal get(int id, Integer userId);

    List<Meal> getAll(Integer userId);

    List<Meal> getByDate(LocalDate from, LocalDate to, Integer userId);

    List<Meal> getByTime(LocalTime from, LocalTime to, Integer userId);

    List<Meal> getFiltered(MealsFilter filter, int userId);
}
