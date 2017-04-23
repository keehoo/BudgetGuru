package com.kree.keehoo.budgetguru;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BudgetEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    User user;

    public BudgetItem getBudgetItem() {
        return budgetItem;
    }

    public void setBudgetItem(BudgetItem budgetItem) {
        this.budgetItem = budgetItem;
    }

    @Embedded
    BudgetItem budgetItem;
    private LocalDateTime dateTime;

    public BudgetEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
