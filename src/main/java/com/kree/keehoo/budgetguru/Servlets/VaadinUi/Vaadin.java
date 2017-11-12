package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI.AbstractUI;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.Views.LoggedView;
import com.kree.keehoo.budgetguru.Users.User;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.server.VaadinCDIServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;


@Theme("mytheme")
@CDIUI("users")
@SuppressWarnings("serial")
public class Vaadin extends AbstractUI {

    @Inject
    UserDao userDao;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);
        VaadinSession currentSession = VaadinSession.getCurrent();

        if (currentSession.getAttribute("isLogged").equals(true)) {
            LoggedView loggedView = new LoggedView();
            Grid<User> grid = new Grid<>();
            grid.setCaption("My Grid");
            grid.setItems(userDao.getAllUsers());
            grid.setSizeFull();
            grid.addColumn(User::getName).setCaption("Name");
            grid.addColumn(User::getLastName).setCaption("Last Name");
            grid.addColumn(User::getEmail).setCaption("Email");
            grid.addColumn(User::getLogin).setCaption("login");
            layout.addComponents(grid, loggedView);
            layout.setExpandRatio(grid, 1); // Expand to fill
            setContent(layout);
        }
    }
}

