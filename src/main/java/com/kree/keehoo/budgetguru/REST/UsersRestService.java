package com.kree.keehoo.budgetguru.REST;

import com.kree.keehoo.budgetguru.Daos.UserDao;
import com.kree.keehoo.budgetguru.Users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Krzysiek on 2017-07-01.
 */
@Stateless
@Path("/users")
public class UsersRestService {

    @Inject
    UserDao userDao;

    @Path("/allUsers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Path("/addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContact(@Valid User user) {

        System.out.println("User to be added: "+user.getName());
        userDao.addUser(user);
        return Response.accepted().build();

    }
}
