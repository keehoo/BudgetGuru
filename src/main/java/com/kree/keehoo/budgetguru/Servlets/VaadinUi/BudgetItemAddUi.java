package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI.AbstractUI;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;
import java.math.BigDecimal;

@Theme("mytheme")
@CDIUI("add")
public class BudgetItemAddUi extends AbstractUI {

    @Inject
    BudgetEntryDao budgetEntryDao;

    @Inject
    UserDao userDao;


    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
        System.out.println(request.getPathInfo());
        System.out.println(request.getRemoteUser());
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getContextPath());
        for (String s : request.getParameterMap().keySet())
            System.out.println(request.getParameterMap().get(s) + " -> "+s);
        setupTextFields(layout);
        if (!budgetEntryDao.budgetItemList().isEmpty()) {

            showTable();
        }
        setContent(layout);
    }

    private void showTable() {
        Grid<BudgetEntry> grid = new Grid<>();
        grid.setCaption("My Grid");
        grid.setItems(budgetEntryDao.budgetItemList());
        grid.setSizeFull();
        grid.addColumn(BudgetEntry::getValue).setCaption("Value");
        grid.addColumn(BudgetEntry::getUser).setCaption("User id");
        grid.addColumn(BudgetEntry::getDateOfCost).setCaption("Date of Cost");
        grid.addColumn(BudgetEntry::getTimeOfCost).setCaption("Time of Cost");
        grid.addColumn(BudgetEntry::getCategory).setCaption("Category");
        grid.addColumn(BudgetEntry::getId).setCaption("Id");

        layout.addComponents(grid);
        layout.setExpandRatio(grid, 1); // Expand to fill
    }

    private void setupTextFields(VerticalLayout superLayout) {

        TextField value = new TextField("Value");
        Button addButton = new Button("Add cost");



        addButton.addClickListener((Button.ClickListener) event -> {
            BudgetEntry b = new BudgetEntry(new BudgetItem(new BigDecimal(value.getValue())));
            b.setUser(userDao.getUserByLogin((String) VaadinSession.getCurrent().getAttribute(LoginUi.CURRENT_USER)).getId());
            budgetEntryDao.addBudgetEntry(b);
            getPage().reload();
        });

        superLayout.addComponents(value, addButton);

    }

}
