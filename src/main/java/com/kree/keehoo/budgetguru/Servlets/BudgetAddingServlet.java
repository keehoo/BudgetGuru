package com.kree.keehoo.budgetguru.Servlets;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
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
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/")
public class BudgetAddingServlet extends HttpServlet {

    @Inject
    UserDao userDao;

    @Inject
    BudgetEntryDao budgetEntryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        addDummyUser();

        User keehoo = userDao.getUserByLogin("keehoo");

        BudgetEntry budgetEntry = new BudgetEntry(new BudgetItem(new BigDecimal(500)));
        budgetEntry.setUser(userDao.getUserByLogin("keehoo").getId());
        budgetEntryDao.addBudgetEntry(budgetEntry);

//
//        budgetEntryDao.updateBudgetEntry(budgetEntry);
//
//        //req.setAttribute("user", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);
    }

    private void addDummyUser() {
        User user = new User();
        user.setLastName("LastName");
        user.setName("Name");
        user.setLogin("keehoo");
        user.setEmail("whatevs@gmail.com");
        user.setPassword("password");
        userDao.addUser(user);

    }
}
