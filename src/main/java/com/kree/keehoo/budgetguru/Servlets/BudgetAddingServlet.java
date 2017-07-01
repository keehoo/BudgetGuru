package com.kree.keehoo.budgetguru.Servlets;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
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
import java.util.List;

@WebServlet(urlPatterns = "/")
public class BudgetAddingServlet extends HttpServlet {

    @Inject
    UserDao userDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDao.addUser(new User("keehoo", "password", "Krzysztof", "Kubicki", "kkubicki2@gmail.com"));
        userDao.addUser(new User("magda", "password", "Magdalena", "Kubicka", "magdalenakubicka@gmail.com"));

        List<User> users = userDao.getAllUsers();

        for (User user : users) {
            System.out.println(user.getEmail());
        }
        req.setAttribute("user", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);

    }

    @Inject
    BudgetEntryDao budgetEntryDao;
}