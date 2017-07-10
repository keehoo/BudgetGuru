package com.kree.keehoo.budgetguru.Servlets;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Session.SessionData;
import com.kree.keehoo.budgetguru.Users.User;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/")
public class BudgetAddingServlet extends HttpServlet {

    @Inject
    UserDao userDao;

    @Inject
    BudgetEntryDao budgetEntryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        user.setLastName("Kubicki");
        user.setName("Krzysztof");
        user.setEmail("kkubicki2@gmail.com");
        user.setPassword("password");
        userDao.addUser(user);


        BudgetEntry budgetEntry = new BudgetEntry();
        budgetEntry.setBudgetItem(new BudgetItem(new BigDecimal(500)));
        budgetEntryDao.addBudgetEntry(budgetEntry);

        budgetEntry.setUser(user);

        budgetEntryDao.updateBudgetEntry(budgetEntry);

        //req.setAttribute("user", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);
    }
}
