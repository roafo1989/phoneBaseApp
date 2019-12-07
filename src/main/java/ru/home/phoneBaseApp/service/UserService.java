package ru.home.phoneBaseApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.repository.UserRepos;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFound;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {
    private UserRepos repository;
    @Autowired
    public void setRepository(UserRepos repository) {
        this.repository = repository;
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user,"user must be not null");
        return repository.save(user);
    }
    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user,"user must be not null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User getById(int id) {
        return checkNotFoundWithId(repository.getById(id), id);
    }

    public User getByName(String name) {
        Assert.notNull(name,"name must be not null");
        return checkNotFound(repository.getByName(name),"name is " + name);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = getById(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }
    public User getWithNotes(int id){
        return checkNotFoundWithId(repository.getWithNotes(id), id);
    }


}
