package com.kree.keehoo.budgetguru.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Krzysiek on 2017-06-29.
 */
@WebServlet(urlPatterns = "/createBE")
public class BudgetEntryCreatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String surname = req.getParameter("username");
        Long cost = Long.valueOf(req.getParameter("cost"));
        System.out.println(surname + "   "+cost);

    }
}
