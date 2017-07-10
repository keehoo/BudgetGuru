package com.kree.keehoo.budgetguru.Budget;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Embeddable
public class BudgetItem {

    private BigDecimal value;
    private boolean cost;

    public BudgetItem(BigDecimal value) throws IllegalArgumentException {
        if (value.doubleValue() == 0) throw new IllegalArgumentException("The value cannot be zero");
        this.value = value;
        checkIfItsACosAndAssignACostFlat();
    }

    public BudgetItem() {
    }

    void checkIfItsACosAndAssignACostFlat() {
        if (value.doubleValue() < 0) setCost();
        else {
            setNotCost();
        }
    }

    public void setCost() {
        cost = true;
    }

    public void setNotCost() {
        cost = false;
    }

    public void setCost(boolean cost) {
        this.cost = cost;
        // Setter added just for hibernate reasons
    }

    public boolean isCost() {
        return cost;
    }

    public BigDecimal getValue() {
        return value;
    }

    void setValue(BigDecimal value) {
        this.value = value;
    }
}
