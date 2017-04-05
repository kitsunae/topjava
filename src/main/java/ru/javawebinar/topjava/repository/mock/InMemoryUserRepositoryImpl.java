package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> storage = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger(0);
    {
        storage.put(count.incrementAndGet(), new User(1, "TestUser1", "TestEmail@1", "TestPass1", Role.ROLE_USER));
        storage.put(count.incrementAndGet(), new User(2, "TestUser2", "TestEmail@2", "TestPass2", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return storage.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew())
            user.setId(count.incrementAndGet());
        return storage.put(user.getId(), user);
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return storage.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return storage.values().stream().sorted((o1, o2) -> {
            if(o1.getName().equals(o2.getName()))
                return o1.getEmail().compareTo(o2.getEmail());
            return o1.getName().compareTo(o2.getName());})
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return storage.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
