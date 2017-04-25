package ru.javawebinar.topjava.service.jdbc;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles(profiles = {Profiles.HSQL_DB, Profiles.JDBC}, inheritProfiles = false)
public class UserServiceTestJdbc extends UserServiceTest {
}
