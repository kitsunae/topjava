package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL+"/";
    private static final ModelMatcher<Meal> MATCHER = MealTestData.MATCHER;
    private static final ModelMatcher<MealWithExceed> MEAL_WITH_EXCEED_MATCHER = ModelMatcher.of(MealWithExceed.class);

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get(REST_URL+ MealTestData.MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MealTestData.MEAL1));
    }

    @Test(expected = NotFoundException.class)
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(REST_URL+MealTestData.MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
        mealService.get(MealTestData.MEAL1_ID, UserTestData.USER_ID);
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_WITH_EXCEED_MATCHER.contentListMatcher(MealsUtil.getWithExceeded(MealTestData.MEALS, MealsUtil.DEFAULT_CALORIES_PER_DAY)));
    }

    @Test
    public void createWithLocation() throws Exception {

    }

    @Test
    public void updateTest() throws Exception {

    }

    @Test
    public void getBetween() throws Exception {
    }

}