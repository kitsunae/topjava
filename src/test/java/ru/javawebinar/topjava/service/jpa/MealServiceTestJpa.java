package ru.javawebinar.topjava.service.jpa;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles(profiles = {Profiles.HSQL_DB, Profiles.JPA}, inheritProfiles = false)
public class MealServiceTestJpa extends MealServiceTest{
}
