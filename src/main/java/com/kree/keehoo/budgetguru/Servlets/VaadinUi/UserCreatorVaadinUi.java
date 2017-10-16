package com.kree.keehoo.budgetguru.Servlets.VaadinUi;


import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Users.User;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.server.VaadinCDIServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@CDIUI("create")
@SuppressWarnings("serial")
public class UserCreatorVaadinUi extends UI {

    @Inject
    UserDao userDao;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        TextField name = new TextField("Name");
        TextField lastName = new TextField("Last Name");
        TextField login = new TextField("login");
        TextField email = new TextField("email");
        TextField pswd = new PasswordField("password");

        Button createUserButton = new Button("Create user");

        createUserButton.addClickListener((Button.ClickListener) clickEvent -> {
                User userToBeCreated = new User(login.getValue(), pswd.getValue(), name.getValue(), lastName.getValue(), email.getValue());
        userDao.addUser(userToBeCreated);
        });

        layout.addComponents(name, lastName, login, email, pswd, createUserButton);
        setContent(layout);
    }

    @WebServlet(value = {"/create/*", "/VAADIN/*"}
            , asyncSupported = true, name = "UserCreateServlet"
    )
    @VaadinServletConfiguration(ui = UserCreatorVaadinUi.class, productionMode = false)
    public static class UserCreatorServlet extends VaadinCDIServlet {




    }

}
