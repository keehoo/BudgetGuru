package com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI;

import com.kree.keehoo.budgetguru.Servlets.VaadinUi.LoginUi;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.Views.LoggedView;
import com.kree.keehoo.budgetguru.Utils.SessionDataUtils;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

public abstract class AbstractUI extends UI{

    public static final String REFERER = "referer";
    protected VerticalLayout layout;
    boolean isLogged;

    @Inject
    SessionDataUtils sessionDataUtils;

    @Override
    protected void init(VaadinRequest request) {

        if ( sessionDataUtils.getCurrentUser() == null) {
            isLogged = false;
            sessionDataUtils.setReferer(request);
            gotoLoginPage();
            return;
        }
        layout = new VerticalLayout();
        addIsLoggedView();
    }

    private void gotoLoginPage() {
        getPage().setLocation("/login/");
    }

    private void addIsLoggedView() {

        LoggedView loggedView = new LoggedView();
        layout.addComponent(loggedView);

    }

}
