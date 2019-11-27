package ru.home.phoneBaseApp.web.user;

import org.springframework.stereotype.Controller;
import ru.home.phoneBaseApp.model.User;
import static ru.home.phoneBaseApp.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User getById() {
        return super.getById(authUserId());
    }

    public void update(User user) {
        super.update(user,authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }
}
