package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbCleaner;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by lashi on 09.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private MealService service;
    @Autowired
    private DbPopulator populator;
    @Autowired
    private DbCleaner cleaner;

    @Before
    public void setUp() throws Exception {
        populator.execute();
    }

    @After
    public void clean(){
        cleaner.execute();
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(FIRST, service.get(1, 100000));
    }

    @Test(expected = NotFoundException.class)
    public void getFailedByWrongUserId(){
        service.get(1, 0);
    }

    @Test
    public void delete() throws Exception {
        service.delete(1, 100000);
    }
    @Test(expected = NotFoundException.class)
    public void deleteFailedByWrongUserId(){
        service.delete(1, 0);
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST, SECOND, THIRD), service.getBetweenDates(LocalDate.of(2015, 1, 1), LocalDate.of(2015, 12, 31), 100000));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST, SECOND, THIRD), service.getBetweenDateTimes(LocalDateTime.of(2015, 1, 1, 0, 0), LocalDateTime.of(2015, 12, 31, 0 ,0), 100000));
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(FIRST, SECOND, THIRD, FORTH), service.getAll(100000));
    }

    @Test
    public void update() throws Exception {
        FIRST.setDescription("New first test meal");
        Meal meal = service.update(FIRST, 100000);
        MATCHER.assertEquals(FIRST, meal);
    }

    @Test(expected = NotFoundException.class)
    public void updateFailedByWrongUserId(){
        service.update(FIRST, 0);
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(), "Current meal", 1200);
        Meal saved = service.save(meal, 100000);
        MATCHER.assertEquals(meal, saved);
    }

}