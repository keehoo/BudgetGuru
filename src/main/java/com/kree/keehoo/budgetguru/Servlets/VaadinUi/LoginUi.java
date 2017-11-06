package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;
import com.kree.keehoo.budgetguru.Daos.ExpenseCatDao;
import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI.AbstractUI;
import com.kree.keehoo.budgetguru.Users.User;
import com.kree.keehoo.budgetguru.Utils.HashingUtils;
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

    public static final String IS_LOGGED = "isLogged";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LOGIN_BUTTON_TEXT = "Login";
    public static final String CURRENT_USER = "currentUser";
    public static final String UNABLE_TO_LOG_IN_TOAST_TEXT = "Unable to log in";
    public static final String APPARENTLY_THERE_S_AN_ISSUE_WITH_LOGIN = "Apparently there's an issue with login. There's either no user of specifed login or the password isn't correct. Please try again or create a new user.";
    @Inject
    UserDao userDao;

    @Inject
    ExpenseCatDao expensecategorydao;

    @Inject
    private HashingUtils hashingUtils;
    private VaadinSession current;
    private VerticalLayout layout;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        current = VaadinSession.getCurrent();
        layout = new VerticalLayout();

        try {
            if (current.getAttribute(IS_LOGGED).equals(true)) {
                showInfo();
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }

        TextField login = new TextField(LOGIN);
        TextField pswd = new PasswordField(PASSWORD);
        Button loginButton = new Button(LOGIN_BUTTON_TEXT);

        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            User user = userDao.getUserByLogin(login.getValue());
            if (user != null && user.getPassword().equals(hashingUtils.hash2withGuava(pswd.getValue()))) {
                setCurrentUser(login);
                if (VaadinSession.getCurrent().getAttribute(AbstractUI.REFERER) != null ) {
                getPage().setLocation((String) VaadinSession.getCurrent().getAttribute(AbstractUI.REFERER));}

            } else {
                current.setAttribute(IS_LOGGED, false);
                current.setAttribute(CURRENT_USER, null);
                Notification.show(UNABLE_TO_LOG_IN_TOAST_TEXT,
                        APPARENTLY_THERE_S_AN_ISSUE_WITH_LOGIN,
                        Notification.Type.WARNING_MESSAGE);
            }
        });

        Button createNewUserButton = new Button("New User");
        createNewUserButton.addClickListener((Button.ClickListener) event -> {
            getPage().open("/createnewuser/", "");
          // getPage().setLocation("/createnewuser");
        });
        checkResult();

        layout.addComponents(login, pswd, loginButton, createNewUserButton);
        setContent(layout);

    }

    private void setCurrentUser(TextField login) {
        try {
            current.getLockInstance().lock();
            current.setAttribute(CURRENT_USER, login.getValue());
            current.setAttribute(IS_LOGGED, true);
        } finally {
            current.getLockInstance().unlock();
        }
    }

    private void checkResult() {
        expensecategorydao.add(new ExpenseCategory("GROCERIES"));
        expensecategorydao.add(new ExpenseCategory("CAR"));
        expensecategorydao.add(new ExpenseCategory("ENTERTAINMENT"));
        expensecategorydao.add(new ExpenseCategory("FOOD OUT"));
    }

    private void showInfo() {
        Label infoLabale = new Label("Currently logged in " + current.getAttribute("currentUser"));
        layout.addComponent(infoLabale);
    }
}
