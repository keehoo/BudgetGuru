package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Users.User;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;

import javax.inject.Inject;

public class UsersView extends Grid<User> implements View {

    @Inject
    UserDao userDao;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (userDao==null) {
            System.out.println("Dao u null");
        }
        System.out.println("dAO OK");

        Grid<User> grid = new Grid<>();
        grid.setCaption("My Grid");
        grid.setItems(userDao.getAllUsers());
        grid.setSizeFull();
        grid.addColumn(User::getName).setCaption("Name");
        grid.addColumn(User::getEmail).setCaption("Email");
    }
}
