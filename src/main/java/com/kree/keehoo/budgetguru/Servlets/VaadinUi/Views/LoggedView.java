package com.kree.keehoo.budgetguru.Servlets.VaadinUi.Views;

import com.kree.keehoo.budgetguru.Servlets.VaadinUi.LoginUi;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import javax.validation.constraints.Null;

public class LoggedView extends Label {

    VaadinSession session = VaadinSession.getCurrent();
    String currentUser;
    boolean isLogged;


    public LoggedView() {

            try {
            currentUser = (String) session.getAttribute(LoginUi.CURRENT_USER);
            isLogged = (boolean) session.getAttribute(LoginUi.IS_LOGGED);
            setValue("Logged in as "+currentUser);
            }
            catch (NullPointerException e) {
                setValue("Not logged in, please log in");
            }
    }
}
