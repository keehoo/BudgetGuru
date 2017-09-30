package com.kree.keehoo.budgetguru.REST;

import com.kree.keehoo.budgetguru.Budget.BudgetEntry;
import com.kree.keehoo.budgetguru.Daos.BudgetEntryDao;
import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
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
        return budgetEntryDao.budgetItemList();
    }


    // POST


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBudgetEntry(@Valid BudgetEntry budgetEntry) {

        budgetEntryDao.addBudgetEntry(budgetEntry);
            return Response.accepted().build();
    }
}
