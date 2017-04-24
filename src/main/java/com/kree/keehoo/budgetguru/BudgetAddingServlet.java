package com.kree.keehoo.budgetguru;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class BudgetAddingServlet extends HttpServlet {

    @Inject
    BudgetEntryDao budgetEntryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Servlet entry point - GET");
        budgetEntryDao.saveDummyDataToDatabase();


    }
}
