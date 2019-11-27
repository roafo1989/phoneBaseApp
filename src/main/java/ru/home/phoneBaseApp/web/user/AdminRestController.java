package ru.home.phoneBaseApp.web.user;

import org.springframework.stereotype.Controller;
import ru.home.phoneBaseApp.model.User;

import java.util.List;
@Controller
public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }
    @Override
    public void update(User user, int id) {
        super.update(user, id);}

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public User getById(int id) {
        return super.getById(id);
    }

    @Override
    public User getByName(String name) {
        return super.getByName(name);
    }
}
