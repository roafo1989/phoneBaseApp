package ru.home.phoneBaseApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.home.phoneBaseApp.service.NoteService;
import ru.home.phoneBaseApp.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:notes";
    }

    @GetMapping("/notes")
    public String getNotes(Model model){
        model.addAttribute("notes",noteService.getAll(SecurityUtil.authUserId()));
        return "notes";
    }

}
