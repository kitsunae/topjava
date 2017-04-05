package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.filters.MealsFilter;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public MealWithExceed get(int id){
        return service.get(id, AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    public MealWithExceed save(MealWithExceed meal){
        return service.save(meal, AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    public void edit(MealWithExceed mealWithExceed){
        service.update(mealWithExceed, AuthorizedUser.id());
    }

    public void delete(MealWithExceed mealWithExceed){
        delete(mealWithExceed.getId());
    }

    public void delete(int id){
        service.delete(id, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll(){
        return service.getAll(AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getFiltered(LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime){
        MealsFilter filter = new MealsFilter();
        filter.setDateFrom(fromDate);
        filter.setTimeFrom(fromTime);
        filter.setDateTo(toDate);
        filter.setTimeTo(toTime);
        return service.getFilteredByDateTime(filter, AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

    public List<MealWithExceed> getFiltered(String stringDateFrom, String stringTimeFrom, String stringDateTo, String stringTimeTo){
        LocalDate dateFrom = Objects.equals(stringDateFrom, "") ? null : LocalDate.parse(stringDateFrom);
        LocalDate dateTo = Objects.equals(stringDateTo, "") ? null : LocalDate.parse(stringDateTo);
        LocalTime timeFrom = Objects.equals(stringTimeFrom, "") ? null :  LocalTime.parse(stringTimeFrom);
        LocalTime timeTo = Objects.equals(stringTimeTo, "") ? null :  LocalTime.parse(stringTimeTo);
        return getFiltered(dateFrom, timeFrom, dateTo, timeTo);
    }
}