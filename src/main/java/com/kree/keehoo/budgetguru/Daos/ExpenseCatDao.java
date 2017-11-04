package com.kree.keehoo.budgetguru.Daos;


import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Stateless
public class ExpenseCatDao {


    @PersistenceContext
    public EntityManager manager;


    public List<ExpenseCategory> getAllCategories() {
        return manager.createNamedQuery(ExpenseCategory.GET_ALL).getResultList();
    }

    public boolean add(ExpenseCategory e) {
        if (!Arrays.asList(getCatNames()).contains(e.getCategoryName())){
            manager.persist(e);
            return true;
        }
        else {
            return false;
        }
    }

    public Object[] getCatNames() {
        List<String> list = new ArrayList<>();
        for (ExpenseCategory e : getAllCategories()) {
            list.add(e.getCategoryName());
        }
        return list.toArray();
    }
}
