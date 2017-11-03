package com.kree.keehoo.budgetguru.Daos;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Budget.BudgetItem;
import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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

    public List<BudgetEntry> getAllForLogin(long id) {
        List<BudgetEntry> list = entityManager.createNamedQuery(BudgetEntry.GET_ALL_FOR_LOGIN).setParameter("id", id).getResultList();
        return list;
    }




    public void addBudgetEntry(BudgetEntry budgetEntry) {

        if (!budgetItemList().contains(budgetEntry)) {
            if (budgetEntry.getBudgetItem().getValue().doubleValue() > 0) {
                budgetEntry.getBudgetItem().setCost();
            }

            entityManager.persist(budgetEntry);
        }
    }

    public void updateBudgetEntry(BudgetEntry budgetEntry) {
        entityManager.merge(budgetEntry);
    }
}
