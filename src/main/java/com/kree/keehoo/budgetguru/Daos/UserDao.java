package com.kree.keehoo.budgetguru.Daos;

import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Krzysiek on 2017-06-30.
 */
@Stateless
public class UserDao {

    @PersistenceContext
    public EntityManager entityManager;

    public List<User> getAllUsers() {
        List<User> usersList = entityManager.createNamedQuery(User.GET_ALL_USERS).getResultList();
        if (usersList != null && usersList.size() != 0) {
            return usersList;
        }
        System.out.println("Returning empty users list");
        return Collections.emptyList();
    }

    public User getUserByLogin(String login) {
        try {
            return entityManager.createNamedQuery(User.GET_USER, User.class).setParameter("login", login).getSingleResult();
        } catch (Throwable e) {
            System.out.println("NOT FOUND 404");
            return null;
        }
    }

    public int addUser(User u) {
        String login = u.getLogin();
        List<User> users = getAllUsers();
        List<String> logins = new ArrayList<>();
        for (User user : users) {
            logins.add(user.getLogin());
            System.out.println("added " + user.getLogin());
            //TODO: convert this ugly code to stream
        }
        if (!logins.contains(login)) {
            entityManager.persist(u);
            return 202;
        } else {
            System.out.println("\n Cannot add user : " + u.toString() + " " +
                    "\n[ERROR] Already a user with login " + login);
            return 0;
        }
    }
}