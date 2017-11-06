package com.kree.keehoo.budgetguru.Utils;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;

import javax.ejb.Stateless;


@Stateless
public class SessionDataUtils {

    public static final String CURRENT_USER = "currentUser";
    public static final String IS_LOGGED = "isLogged";
    public static final String REFERER = "referer";


    private VaadinSession currentSession;

    public SessionDataUtils() {
        currentSession = VaadinSession.getCurrent();
    }

    public void setCurrentUser(String login) {
        try {
            currentSession.getLockInstance().lock();
            currentSession.setAttribute(CURRENT_USER, login);
            currentSession.setAttribute(IS_LOGGED, true);
        } finally {
            currentSession.getLockInstance().unlock();
        }
    }

    public void logout() {
        try {
            currentSession.getLockInstance().lock();
            currentSession.setAttribute(CURRENT_USER, null);
            currentSession.setAttribute(IS_LOGGED, false);
        } finally {
            currentSession.getLockInstance().unlock();
        }
    }

    public String getCurrentUser() {
        return (String) currentSession.getAttribute(CURRENT_USER);
    }

    public boolean isUserLogged() {
        return (boolean) currentSession.getAttribute(IS_LOGGED);
    }

    public VaadinSession getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(VaadinSession currentSession) {
        this.currentSession = currentSession;
    }

    public void setReferer(VaadinRequest req) {
        currentSession.setAttribute(REFERER, req.getPathInfo());
    }
}
