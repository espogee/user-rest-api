package com.espogee;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/users")
    public Response listUsers() {
        List<Users> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    @GET
    @Path("/users/{id}")
    public Response getUser(@PathParam("id") Long id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/users")
    public Response createUser(Users user) {
        Users createdUser = userService.addUser(user);
        if (createdUser != null) {
            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/users/{id}")
    public Response updateUser(@PathParam("id") Long id, Users user) {
        Users updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return Response.ok(updatedUser).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        Users user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
