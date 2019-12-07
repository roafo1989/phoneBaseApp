package ru.home.phoneBaseApp.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.service.UserService;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.assureIdConsistent;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNew;

public class AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserController.class);
    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }
    public void update(User user, int id) {
        log.info("update {} with id={}", user,id);
        assureIdConsistent(user,id);
        service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public User getById(int id) {
        log.info("get {}", id);
        return service.getById(id);
    }

    public User getByName(String name) {
        log.info("getByName {}", name);
        return service.getByName(name);
    }
    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }
}
