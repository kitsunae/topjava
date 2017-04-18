package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()){
            User user = entityManager.getReference(User.class, userId);
            meal.setUser(user);
            entityManager.persist(meal);
            return meal;
        }
        int i = entityManager.createNamedQuery(Meal.UPDATE).setParameter("datetime", meal.getDateTime())
                .setParameter("calories", meal.getCalories())
                .setParameter("description", meal.getDescription())
                .setParameter("id", meal.getId())
                .setParameter("userId", userId)
                .executeUpdate();
        if (i==0)
            return null;
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("userId", userId).executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> resultList = entityManager.createNamedQuery(Meal.GET, Meal.class).setParameter("id", id).setParameter("userId", userId).getResultList();
        return DataAccessUtils.singleResult(resultList);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return entityManager.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return entityManager.createNamedQuery(Meal.GET_BETWEEN, Meal.class).setParameter("userId", userId).setParameter("start", startDate)
                .setParameter("end", endDate).getResultList();
    }
}