package com.kree.keehoo.budgetguru.REST;

import com.kree.keehoo.budgetguru.Budget.ExpenseCategory;
import com.kree.keehoo.budgetguru.Daos.ExpenseCatDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Stateless
@Path("/category")
public class CategoryRestService {

    @Inject
    ExpenseCatDao categoryDao;

    @Path("/getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Object> getAll() {
        Object[] catNames = categoryDao.getCatNames();
        try {
            String[] catNameStrings = Arrays.copyOf(catNames, catNames.length, String[].class);
            return Arrays.asList(catNameStrings);
        } catch (Throwable t) {
            System.out.println("Exception while serving the categories array through rest, " + t.getMessage());
            return Collections.emptyList();
        }
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(@Valid ExpenseCategory category) {

        if (categoryDao.add(category)) {
            return Response.accepted().build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
