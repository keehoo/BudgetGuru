package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;
import com.kree.keehoo.budgetguru.Daos.ExpenseCatDao;
import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI.AbstractUI;
import com.kree.keehoo.budgetguru.Users.User;
import com.kree.keehoo.budgetguru.Utils.HashingUtils;
import com.kree.keehoo.budgetguru.Utils.SessionDataUtils;
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

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LOGIN_BUTTON_TEXT = "Login";
    public static final String UNABLE_TO_LOG_IN_TOAST_TEXT = "Unable to log in";
    public static final String APPARENTLY_THERE_S_AN_ISSUE_WITH_LOGIN = "Apparently there's an issue with login. There's either no user of specifed login or the password isn't correct. Please try again or create a new user.";
    @Inject
    UserDao userDao;

    @Inject
    private HashingUtils hashingUtils;

    @Inject
    SessionDataUtils sessionUtils;

    private VaadinSession current;
    private VerticalLayout layout;
    private Button loginButton;
    private Button createNewUserButton;
    private TextField login;
    private TextField pswd;
    private Panel loginPanel;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        current = VaadinSession.getCurrent();
        layout = new VerticalLayout();

        setupTextFields();
        setupLoginButton(login, pswd);
        setupCreateNewUserButton();

        setupLoginPanel();

        layout.addComponent(loginPanel);
        layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

    private void setupLoginPanel() {
        loginPanel = new Panel();
        loginPanel.setWidth("400px");
        loginPanel.setHeight("300px");
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(login);
        content.addComponent(pswd);
        content.addComponent(loginButton);
        content.addComponent(createNewUserButton);
        content.setSizeFull();
        content.setSpacing(true);
        content.setMargin(true);
        loginPanel.setContent(content);
    }

    private void setupTextFields() {
        login = new TextField(LOGIN);
        login.setSizeFull();
        pswd = new PasswordField(PASSWORD);
        pswd.setSizeFull();
    }

    private void setupCreateNewUserButton() {
        createNewUserButton = new Button("New User");
        createNewUserButton.setSizeFull();
        createNewUserButton.addClickListener((Button.ClickListener) event -> {
            getPage().open("/createnewuser/", "");
        });
    }

    private void setupLoginButton(TextField login, TextField pswd) {
        loginButton = new Button(LOGIN_BUTTON_TEXT);
        loginButton.setSizeFull();
        loginButton.addClickListener((Button.ClickListener) clickEvent -> {
            User user = userDao.getUserByLogin(login.getValue());
            if (user != null && user.getPassword().equals(hashingUtils.hash2withGuava(pswd.getValue()))) {
                sessionUtils.setCurrentUser(login.getValue());
                if (VaadinSession.getCurrent().getAttribute(AbstractUI.REFERER) != null ) {
                    getPage().setLocation((String) VaadinSession.getCurrent().getAttribute(AbstractUI.REFERER));}

            } else {
                sessionUtils.logout();
                Notification.show(UNABLE_TO_LOG_IN_TOAST_TEXT,
                        APPARENTLY_THERE_S_AN_ISSUE_WITH_LOGIN,
                        Notification.Type.WARNING_MESSAGE);
            }
        });
    }

}
