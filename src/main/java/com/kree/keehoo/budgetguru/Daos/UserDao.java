package com.kree.keehoo.budgetguru.Daos;

import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by Krzysiek on 2017-06-30.
 */
@Stateless
public class UserDao {

    @PersistenceContext
    public EntityManager entityManager;

    public Set<User> getAllUsers() {
        List<User> usersList = entityManager.createNamedQuery(User.GET_ALL_USERS).getResultList();
        Set<User> set = new HashSet<>(usersList);
        if (usersList.size() != 0) {
            return set;
        }
        System.out.println("Returning empty users list");
        return Collections.emptySet();
    }

    public User getUserByLogin(String login) {
        try {
            return entityManager.createNamedQuery(User.GET_USER, User.class).setParameter("login", login).getSingleResult();
        } catch (Throwable e) {
            System.out.println("NOT FOUND 404");
            return null;
        }
    }

    public User getUserById(long id) {
        try {
            return entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        } catch (Throwable e) {
            System.out.println("NOT FOUND 404");
            return null;
        }
    }

    public void addUser(User u) {
        String login = u.getLogin();
        Set<User> users = getAllUsers();
        Set<String> logins = new HashSet<>();
        for (User user : users) {
            logins.add(user.getLogin());
            System.out.println("added " + user.getLogin());
        }
        if (!logins.contains(login)) {
            entityManager.persist(u);
        } else {
            System.out.println("\n Cannot add user : " + u.toString() + " " +
                    "\n[ERROR] Already a user with login " + login);
        }
    }
}