
package com.kree.keehoo.budgetguru.Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/")
public class BudgetAddingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 /*       RequestDispatcher dispatcher = req.getRequestDispatcher("create");
        dispatcher.forward(req, resp);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/create/");

       /* RequestDispatcher dispatcher = req.getRequestDispatcher("create");
        dispatcher.forward(req, resp);*/
    }
}

