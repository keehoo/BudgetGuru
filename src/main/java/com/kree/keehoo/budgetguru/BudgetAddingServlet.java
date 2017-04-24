package com.kree.keehoo.budgetguru;

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
        req.setAttribute("budgetentries",budgetEntries);
        for (BudgetEntry b : budgetEntries) System.out.println(b.getUser().getLastName());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);

    }
    @Inject
    BudgetEntryDao budgetEntryDao;
}
