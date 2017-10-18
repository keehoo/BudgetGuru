package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Session.SessionData;
import com.kree.keehoo.budgetguru.Users.User;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import javax.inject.Inject;

@Theme("mytheme")
@CDIUI("login")
@SuppressWarnings("serial")
public class LoginUi extends UI {

    @Inject
    UserDao userDao;
    private VaadinSession current;
    private VerticalLayout layout;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        current = VaadinSession.getCurrent();

        layout = new VerticalLayout();

try
{
        if (current.getAttribute("isLogged").equals(true)) {
            showInfo();
        }}
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }

        TextField login = new TextField("login");
        TextField pswd = new PasswordField("password");
        Button loginButton = new Button("Login");

        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            User user = userDao.getUserByLogin(login.getValue());
            if (user!=null && user.getPassword().equals(pswd.getValue())) {
                setCurrentUser(login);

            }
            else {
                current.setAttribute("isLogged", false);
                current.setAttribute("currentUser", null);
                Notification.show("Unable to log in",
                        "Apparently there's an issue with login. There's either no user of specifed login or the password isn't correct. Please try again or create a new user.",
                        Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        layout.addComponents(login, pswd, loginButton);
        setContent(layout);

    }

    private void setCurrentUser(TextField login) {
        try {current.getLockInstance().lock();
        current.setAttribute("currentUser", login.getValue());
        current.setAttribute("isLogged", true);}
        finally {
            current.getLockInstance().unlock();
        }
    }

    private void showInfo() {
        Label infoLabale = new Label("Currently logged in "+current.getAttribute("currentUser"));
        layout.addComponent(infoLabale);
    }
}
