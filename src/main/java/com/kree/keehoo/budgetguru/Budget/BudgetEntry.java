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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @Embedded
    private BudgetItem budgetItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BudgetItem getBudgetItem() {
        return budgetItem;
    }

    public void setBudgetItem(BudgetItem budgetItem) {
        this.budgetItem = budgetItem;
    }
}
