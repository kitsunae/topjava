package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealsRepository;
import ru.javawebinar.topjava.dao.MealsRepositoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by lashi on 28.03.2017.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealsRepository repository = MealsRepositoryImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("processing get for all meals");
        List<Meal> allMeals = repository.getAllMeals();
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(allMeals, LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", filteredWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("saving meal");
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime time = LocalDateTime.parse(req.getParameter("dateTime"));
        Meal meal = new Meal(time, description, calories);
        repository.saveMeal(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("deleting meal");
        Long id = Long.valueOf(req.getParameter("id"));
        repository.removeMeal(id);
    }
}
