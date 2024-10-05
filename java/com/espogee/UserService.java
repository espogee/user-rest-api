package com.espogee;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public List<Users> getAllUsers() {
       return em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }

    public Users getUserById(Long id) {
        return em.find(Users.class, id);
    }


    public Users addUser(Users user) {
        em.persist(user);
        return user;
    }

    public Users updateUser(Long id, Users user) {
        Users exUser = getUserById(id);
        if (exUser != null) {
            exUser.setFirstName(user.getFirstName());
            exUser.setLastName(user.getLastName());
            exUser.setEmail(user.getEmail());
            exUser.setPassword(user.getPassword());
            exUser.setBirthday(user.getBirthday());
            return em.merge(exUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        Users deletedUser = em.find(Users.class, id);
        if (deletedUser != null) {
            em.remove(deletedUser);
        }
    }

}
