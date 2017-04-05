package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.filters.MealsFilter;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    MealWithExceed save(MealWithExceed meal, int userId, int caloriesPerDay);
    boolean delete(int id, int userId) throws NotFoundException;
    MealWithExceed get(int id, int userId, int caloriesPerDay) throws NotFoundException;
    List<MealWithExceed> getAll(int userId, int caloriesPerDay);
    void update(MealWithExceed meal, int userId);
    List<MealWithExceed> getFilteredByDateTime(MealsFilter filter, int userId, int caloriesPerDay);
}