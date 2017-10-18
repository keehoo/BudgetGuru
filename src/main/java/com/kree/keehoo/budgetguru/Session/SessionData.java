package com.kree.keehoo.budgetguru.Session;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Krzysiek on 2017-06-30.
 */
@SessionScoped
public class SessionData implements Serializable {

    private String currentUser;
    private boolean logged;


    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }


}
