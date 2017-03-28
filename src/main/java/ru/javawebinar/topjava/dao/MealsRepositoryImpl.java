package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lashi on 28.03.2017.
 */
public class MealsRepositoryImpl implements MealsRepository {

    private static AtomicLong counter = new AtomicLong(6);
    private static MealsRepositoryImpl instance = new MealsRepositoryImpl();

    private Map<Long, Meal> meals = new ConcurrentHashMap<>();

    private MealsRepositoryImpl() {
        meals.put(1L, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(2L, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(3L, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(4L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(5L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(6L, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public static MealsRepository getInstance(){
        return instance;
    }

    @Override
    public Meal getMeal(long id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void saveMeal(Meal meal) {
        meal.setId(counter.incrementAndGet());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void removeMeal(long id) {
        meals.remove(id);
    }
}
