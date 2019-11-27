package ru.home.phoneBaseApp.repository;

import ru.home.phoneBaseApp.model.User;

import java.util.List;

public interface UserRepos {
    List<User> getAll();
    User save(User user);
    boolean delete(int id);
    User getById(int id);
    User getByName(String name);
}
