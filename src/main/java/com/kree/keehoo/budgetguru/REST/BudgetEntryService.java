package com.kree.keehoo.budgetguru.REST;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Krzysiek on 2017-07-01.
 */
@Stateless
@Path("/budget")
public class BudgetEntryService {

    @Inject
    BudgetEntryDao budgetEntryDao;


    @Path("/allBudgets")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BudgetEntry> getAllBudgets() {
        System.out.println("Returning budget entry list");
        return budgetEntryDao.budgetItemList();
    }
}
