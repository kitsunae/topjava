package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.filters.MealsFilter;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        LOG.debug("saving meal");
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        LOG.debug("deleting meal");
        Meal meal = repository.get(id);
        if (!(meal == null || (meal.getUserId() != null && !meal.getUserId().equals(userId))))
            if (repository.remove(id) != null)
                return true;
        LOG.debug("invalid user");
        return false;
    }

    @Override
    public Meal get(int id, Integer userId) {
        LOG.debug("get meal " + id + " from user "+userId);
        Meal meal = repository.get(id);
        if (!meal.getUserId().equals(userId)) {
            LOG.debug("invalid user");
            return null;
        }
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getByDate(LocalDate from, LocalDate to, Integer userId) {
        return repository.values().stream().filter(meal -> DateTimeUtil.isBetween(meal.getDate(), from, to) && meal.getUserId().equals(userId))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getByTime(LocalTime from, LocalTime to, Integer userId) {
        return repository.values().stream()
                .filter(m-> DateTimeUtil.isBetween(m.getTime(), from, to))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFiltered(MealsFilter filter, int userId) {
        return repository.values().stream()
                .filter(filter)
                .filter(meal -> meal.getUserId().equals(userId))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }
}

