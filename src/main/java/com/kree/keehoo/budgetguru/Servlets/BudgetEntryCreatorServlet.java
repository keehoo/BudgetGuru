package com.kree.keehoo.budgetguru.Servlets;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Users.User;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Krzysiek on 2017-06-29.
 */
@WebServlet(urlPatterns = "/createUser")
public class BudgetEntryCreatorServlet extends HttpServlet {

    @Inject
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(login,password, firstName, lastName, email);
        System.out.println(user.toString());

        userDao.addUser(user);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);
    }
}
