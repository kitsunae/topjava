package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.filters.MealsFilter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    private MealWithExceed convert(Meal meal, int caloriesPerDay){
        List<Meal> all = repository.getByDate(meal.getDate(), meal.getDate(), meal.getUserId());
        List<MealWithExceed> withExceeded = MealsUtil.getWithExceeded(all, caloriesPerDay);
        return withExceeded.stream().filter(m-> m.getId().equals(meal.getId())).findFirst().orElse(null);
    }

    @Override
    public MealWithExceed save(MealWithExceed meal, int userId, int caloriesPerDay) {
        Meal entity = new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId);
        Meal saved = repository.save(entity);
        return convert(saved, caloriesPerDay);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        boolean delete = repository.delete(id, userId);
        ValidationUtil.checkNotFoundWithId(delete, id);
        return true;
    }

    @Override
    public MealWithExceed get(int id, int userId, int caloriesPerDay) throws NotFoundException {
        Meal meal = ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
        return convert(meal, caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int caloriesPerDay) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), caloriesPerDay);
    }

    @Override
    public void update(MealWithExceed meal, int userId) {
        Meal edited = new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId);
        repository.save(edited);
    }

    public List<MealWithExceed> getFilteredByDateTime(MealsFilter filter, int userId, int caloriesPerDay){
        return MealsUtil.getWithExceeded(repository.getFiltered(filter, userId), caloriesPerDay);
    }
}