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

    @ManyToOne
    @JoinColumn(name="user_id")
    public User userId;

    private Long dateTime;

    public BudgetEntry() {
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
