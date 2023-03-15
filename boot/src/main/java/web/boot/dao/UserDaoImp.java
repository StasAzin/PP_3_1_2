package web.boot.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.boot.models.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(int id, User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(int id) {
        return em.createQuery("from User user where user.id = " + id, User.class).getSingleResult();
        /*TypedQuery<User> q =  em.createQuery(
                "select u from User u where u.id = :id", User.class
        );
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);*/
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }
}
