package com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI;

import com.kree.keehoo.budgetguru.Servlets.VaadinUi.LoginUi;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.Views.LoggedView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractUI extends UI{

    protected VerticalLayout layout;
    boolean isLogged;

    @Override
    protected void init(VaadinRequest request) {

        if (VaadinSession.getCurrent().getAttribute(LoginUi.IS_LOGGED) == null) {
            isLogged = false;
            getPage().setLocation("/login/");
        }
        layout = new VerticalLayout();
        addIsLoggedView(layout);
    }

    private void addIsLoggedView(Layout layout1) {

        LoggedView loggedView = new LoggedView();
        layout1.addComponent(loggedView);

    }

}
