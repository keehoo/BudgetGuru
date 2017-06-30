package com.kree.keehoo.budgetguru.Servlets;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        budgetEntryDao.saveDummyDataToDatabase();
        List<BudgetEntry> budgetEntries = budgetEntryDao.budgetItemList();
        if (budgetEntries != null) {
            System.out.println("BUDGET ENTRIES ARENT NULL");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        String userSurname = budgetEntries.get(0).getUser().getLastName();
        req.setAttribute("surname", userSurname);
        resp.sendRedirect("/");

        dispatcher.forward(req, resp);

    }

    @Inject
    BudgetEntryDao budgetEntryDao;
}
