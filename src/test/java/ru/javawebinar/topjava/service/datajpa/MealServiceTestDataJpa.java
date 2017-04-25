package ru.javawebinar.topjava.service.datajpa;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {Profiles.HSQL_DB, Profiles.DATA_JPA}, inheritProfiles = false)
public class MealServiceTestDataJpa extends MealServiceTest {
}
