package com.kree.keehoo.budgetguru.Daos;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by k on 20.02.17.
 */
@Stateless
public class BudgetEntryDao {

    @PersistenceContext
    public EntityManager entityManager;

    public List<BudgetEntry> budgetItemList() {
        List<BudgetEntry> list = entityManager.createNamedQuery(BudgetEntry.GET_ALL_BUDGET_ENTRIES).getResultList();
        return list;
    }

    public void addBudgetEntry(BudgetEntry budgetEntry) {

        if (!budgetItemList().isEmpty() && !budgetItemList().contains(budgetEntry)) {
            entityManager.persist(budgetEntry);
        }
    }
}
