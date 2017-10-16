package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Users.User;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.server.VaadinCDIServlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;


@Theme("mytheme")
@CDIUI("")
@SuppressWarnings("serial")
public class Vaadin extends UI {

    @Inject
    UserDao userDao;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        userDao.addUser(new User("login", "pswd", "Krzysztof", "Kubicki", "kkubicki2@gmail.com"));

  /*      final Label title = new Label();
        title.setValue("Please log in");
        title.addStyleName(" h1 ");

        final TextField name = new TextField();
        name.setCaption("email");

        final TextField password = new PasswordField();
        password.setCaption("password");

        Button button = new Button("Log in");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue().split("@")[0]
                    + ", you're dummy logged in!"));

        });


        layout.addComponents(title, name, password, button);*/

//  List<User> users = new ArrayList<>();
//  users.add(new User("asdasd", "asdasdasd", "asdasdasd", "asdasdasd", "Asdasdasd"));


        Grid<User> grid = new Grid<>();
        grid.setCaption("My Grid");
        grid.setItems(userDao.getAllUsers());
        grid.setSizeFull();
        grid.addColumn(User::getName).setCaption("Name");
        grid.addColumn(User::getEmail).setCaption("Email");
        layout.addComponent(grid);
        layout.setExpandRatio(grid, 1); // Expand to fill

        setContent(layout);
    }

    @WebServlet(
            value = {"/myui/*", "/VAADIN/*"}, asyncSupported = true, name = "MyUIServlet"
    )
   // @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Vaadin.class, productionMode = false)
    public static class MyUIServlet extends VaadinCDIServlet {




    }
}

