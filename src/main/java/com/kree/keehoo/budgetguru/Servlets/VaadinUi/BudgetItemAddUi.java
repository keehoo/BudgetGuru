package com.kree.keehoo.budgetguru.Servlets.VaadinUi;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
import com.kree.keehoo.budgetguru.Budget.Category;
import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
import com.kree.keehoo.budgetguru.Daos.ExpenseCatDao;
import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Servlets.VaadinUi.UI.AbstractUI;
import com.kree.keehoo.budgetguru.Utils.SessionDataUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;

import javax.inject.Inject;
import javax.xml.soap.Text;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Theme("valo")
@CDIUI("add")
public class BudgetItemAddUi extends AbstractUI {

    @Inject
    BudgetEntryDao budgetEntryDao;

    @Inject
    UserDao userDao;

    @Inject
    ExpenseCatDao catDao;

    private String cat;


    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
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

        grid.addSelectionListener((SelectionListener<BudgetEntry>) event -> {
            Optional<BudgetEntry> firstSelectedItem = event.getFirstSelectedItem();
            String result = firstSelectedItem.isPresent() ? "Selected :"+firstSelectedItem.get().getValue() : "Oops - optional resulted in no value";
            Notification.show(result);
        });
        grid.addItemClickListener((ItemClickListener<BudgetEntry>) event -> {

        }
        );

        layout.addComponents(grid);
        layout.setExpandRatio(grid, 1); // Expand to fill
    }

    private void setupTextFields(VerticalLayout superLayout) {

        TextField value = new TextField("Value");
        Button addButton = new Button("Add cost");
        Label label = new Label("Category");
        ComboBox categoryComboBox = new ComboBox();
        HorizontalLayout catAddingLayout = new HorizontalLayout();
        TextField category = new TextField("category to be added");
        Button addCatButton = new Button("add category");
        addCatButton.addClickListener((Button.ClickListener) event -> catDao.add(new ExpenseCategory(category.getValue())));
catAddingLayout.addComponents(category, addCatButton);
        for (ExpenseCategory s : catDao.getAllCategories()) {
            System.out.println(s.getCategoryName() +  "  " + s.getId());
        }


        categoryComboBox.setItems(catDao.getCatNames());
       // categoryComboBox.setItemCaptionGenerator(Category::);
        categoryComboBox.setEmptySelectionAllowed(false);
        categoryComboBox.setEmptySelectionCaption("Please select Category");

       categoryComboBox.addValueChangeListener((HasValue.ValueChangeListener) event -> cat = (String) event.getValue());

        addButton.addClickListener((Button.ClickListener) event -> {
            BudgetEntry b = new BudgetEntry(new BudgetItem(new BigDecimal(value.getValue())));
            b.setUser(userDao.getUserByLogin((String) VaadinSession.getCurrent().getAttribute(SessionDataUtils.CURRENT_USER)).getId());
            b.setCategory(cat);
            budgetEntryDao.addBudgetEntry(b);
            getPage().reload();
        });

        superLayout.addComponents(value, addButton, label, categoryComboBox, catAddingLayout);
    }
}
