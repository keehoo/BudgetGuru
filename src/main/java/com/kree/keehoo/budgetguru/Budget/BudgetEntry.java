package com.kree.keehoo.budgetguru.Budget;

import com.kree.keehoo.budgetguru.Users.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = BudgetEntry.GET_ALL_BUDGET_ENTRIES,
                query = "select u from BudgetEntry u"),
        @NamedQuery(name = BudgetEntry.GET_ALL_FOR_LOGIN,
                query = "select b from BudgetEntry b where b.user = :id "
        )
})
public class BudgetEntry {

    final public static String GET_ALL_BUDGET_ENTRIES= "BudgetEntry.GET_ALL_BUDGET_ENTRIES";
    final public static String GET_ALL_FOR_LOGIN= "BudgetEntry.GET_ALL_FOR_LOGIN";

    @Id
    @GeneratedValue
    private Long id;
    private long user;
    private String dateOfCost;
    private String timeOfCost;
    private Category category;

    @Embedded
    private BudgetItem budgetItem;
    public BudgetEntry(BudgetItem budgetItem) {
        this.budgetItem = budgetItem;
        boolean isCost;
        if (budgetItem.getValue().doubleValue() > 0) {
            isCost = true;
        } else isCost = false;
        category = Category.FOOD_OUT;
    }

    public BudgetEntry(BudgetItem budgetItem, Category category) {
        setCategory(category);
        this.budgetItem = budgetItem;
        boolean isCost;
        if (budgetItem.getValue().doubleValue() > 0) {
            isCost = true;
        } else isCost = false;

    }

    public BudgetEntry() {

    }

    public double getValue() {
        return budgetItem.getValue().doubleValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public BudgetItem getBudgetItem() {
        return budgetItem;
    }

    public void setBudgetItem(BudgetItem budgetItem) {
        this.budgetItem = budgetItem;
    }

    public String getDateOfCost() {
        return dateOfCost;
    }

    public void setDateOfCost(String dateOfCost) {
        this.dateOfCost = dateOfCost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTimeOfCost() {
        return timeOfCost;
    }

    public void setTimeOfCost(String timeOfCost) {
        this.timeOfCost = timeOfCost;
    }
}
