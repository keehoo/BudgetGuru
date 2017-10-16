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
@CDIUI("")
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

        User userToBeCreated = new User(login.getValue(), pswd.getValue(), name.getValue(), lastName.getValue(), email.getValue());
        Button createUserButton = new Button("Create user");
        createUserButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                userDao.addUser(userToBeCreated);
            }
        });

        layout.addComponents(name, lastName, login, email, pswd, createUserButton);
        setContent(layout);
    }

    @WebServlet(
            value = {"/*", "/VAADIN/*"}, asyncSupported = true, name = "MyUIServlet"
    )
    // @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Vaadin.class, productionMode = false)
    public static class MyUIServlet extends VaadinCDIServlet {




    }

}
