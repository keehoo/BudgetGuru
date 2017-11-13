package com.kree.keehoo.budgetguru.Budget;

import java.util.ArrayList;
import java.util.List;

public enum Category {

    GROCERY, CHEM, ENTERTAINMENT, FOOD_OUT;


     public static List<String> getAll() {
            List<String> listToBeReturned = new ArrayList<>();
         for (Category c : values()) {
             listToBeReturned.add(c.name());
         }
         return listToBeReturned;
    }
}
