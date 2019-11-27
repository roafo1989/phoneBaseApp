package ru.home.phoneBaseApp.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.repository.UserRepos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class UserReposImpl implements UserRepos {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
/*      User ref = em.getReference(User.class, id);
        em.remove(ref);

        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
*/
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        List<User> users = em.createNamedQuery(User.BY_NAME, User.class)
                .setParameter(1, name)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }
}
