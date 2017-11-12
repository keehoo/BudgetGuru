package com.kree.keehoo.budgetguru.Servlets.VaadinUi.Views;

import com.kree.keehoo.budgetguru.Utils.SessionDataUtils;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

public class LoggedView extends Label {



    private static final String ERROR = "error";
    private static final String SMALL = "small";
    private static final String LOGGED_IN_AS = "Logged in as ";
    private static final String NOT_LOGGED_IN_PLEASE_LOG_IN_OR_CREATE_A_NEW_USER = "Not logged in. Please log in or create a new user";

    public LoggedView() {


        try {
            addStyleName(SMALL);
            String currentUser = (String) VaadinSession.getCurrent().getAttribute(SessionDataUtils.CURRENT_USER);
            boolean isLogged = (boolean) VaadinSession.getCurrent().getAttribute(SessionDataUtils.IS_LOGGED);
            if (isLogged) {
                setValue(LOGGED_IN_AS + currentUser);
            }

        } catch (NullPointerException e) {
            System.out.println("Null pointer while setting up user "+e.getMessage());
            addStyleName(ERROR);
            setValue(NOT_LOGGED_IN_PLEASE_LOG_IN_OR_CREATE_A_NEW_USER);
        }
    }
}
