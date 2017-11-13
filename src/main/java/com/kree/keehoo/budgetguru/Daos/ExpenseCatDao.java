package com.kree.keehoo.budgetguru.Daos;


import com.kree.keehoo.budgetguru.Budget.Category;
import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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
        Set<String> set = new HashSet<>();
        for (ExpenseCategory e : getAllCategories()) {
            set.add(e.getCategoryName());
        }
        addDefaultCategories(set);
        return set.toArray();
    }

    private void addDefaultCategories(Set<String> list) {
        list.addAll(Category.getAll());
    }
}
