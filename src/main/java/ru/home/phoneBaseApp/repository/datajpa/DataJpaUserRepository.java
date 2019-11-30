package ru.home.phoneBaseApp.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.repository.UserRepos;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepos {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User getById(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByName(String name) {
        return crudRepository.getByName(name);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }
}
