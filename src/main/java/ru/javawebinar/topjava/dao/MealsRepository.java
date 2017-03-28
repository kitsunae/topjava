package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by lashi on 28.03.2017.
 */
public interface MealsRepository {
    Meal getMeal(long id);
    List<Meal> getAllMeals();
    void saveMeal(Meal meal);
    void removeMeal(long id);
}
