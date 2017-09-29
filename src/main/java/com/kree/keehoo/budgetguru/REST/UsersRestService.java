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

    @Path("/getUser")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByLogin(@QueryParam("login") String login) {
        System.out.println("Querying db for user " + login);
        //TODO: should add response here.
        return userDao.getUserByLogin(login);
    }

    // POST


    @Path("/addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addContact(@Valid User user) {

        System.out.println("User to be added: " + user.getName());
        if (userDao.addUser(user) == 202) {
            return Response.accepted().build();}
        else
            {
            return Response.status(0).build();
            }

        }
    }
