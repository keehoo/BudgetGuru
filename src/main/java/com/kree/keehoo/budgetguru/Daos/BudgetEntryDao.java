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

    public void saveDummyDataToDatabase() {
        BudgetEntry budgetEntry = new BudgetEntry();
        budgetEntry.setBudgetItem(new BudgetItem(new BigDecimal(100.0)));
       // budgetEntry.budgetItem.setCost();
        budgetEntry.setDateTime(LocalDateTime.now());
        budgetEntry.setUser(new User("krzys", "password", "Krzysiek", "Kubicki", "kkubicki2@gmail.com"));
        try {
            //if (entityManager == null) System.out.println("BudgetEntry is null");
            entityManager.persist(budgetEntry);
        } catch (NullPointerException nullPointer) {
            System.out.println("Null Pointer " + nullPointer.getMessage());
            System.out.println(nullPointer.toString());
        }
    }

    public List<BudgetEntry> budgetItemList() {
        List<BudgetEntry> list = entityManager.createNamedQuery(BudgetEntry.GET_ALL_BUDGET_ENTRIES).getResultList();
        return list;
    }

}
