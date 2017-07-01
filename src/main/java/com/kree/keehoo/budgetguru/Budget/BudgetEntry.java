package com.kree.keehoo.budgetguru.Budget;

import com.kree.keehoo.budgetguru.Users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = BudgetEntry.GET_ALL_BUDGET_ENTRIES,
                query = "select u from BudgetEntry u")
})
public class BudgetEntry {

    final public static String GET_ALL_BUDGET_ENTRIES= "BudgetEntry.GET_ALL_BUDGET_ENTRIES";

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    BudgetItem budgetItem;

    @ManyToOne(cascade = {CascadeType.ALL})
    public User user;

    private Long dateTime;

    public BudgetEntry() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBudgetItem(BudgetItem budgetItem) {
        this.budgetItem = budgetItem;
    }

    public BudgetItem getBudgetItem() {
        return budgetItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
