package ru.javawebinar.topjava.service.jpa;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles(profiles = {Profiles.HSQL_DB, Profiles.JPA}, inheritProfiles = false)
public class UserServiceTestJpa extends UserServiceTest {
}
