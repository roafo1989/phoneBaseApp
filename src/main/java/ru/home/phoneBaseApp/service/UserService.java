package ru.home.phoneBaseApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<User> getAll() {
        return repository.getAll();
    }

    public User create(User user) {
        Assert.notNull(user,"user must be not null");
        return repository.save(user);
    }
    public void update(User user) {
        Assert.notNull(user,"user must be not null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

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
}
