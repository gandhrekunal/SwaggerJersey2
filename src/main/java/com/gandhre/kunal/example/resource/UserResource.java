package com.gandhre.kunal.example.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gandhre.kunal.example.data.UserData;
import com.gandhre.kunal.example.exception.ApiException;
import com.gandhre.kunal.example.exception.NotFoundException;
import com.gandhre.kunal.example.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/user")
@Api(value = "/user", description = "Operations about user")
@Produces({ "application/json" })
public class UserResource {
    static UserData userData = new UserData();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create user", notes = "This can only be done by the logged in user.", position = 1)
    public Response createUser(
            @ApiParam(value = "Created user object", required = true) User user) {
        userData.addUser(user);
        return Response.ok().entity("").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createWithArray")
    @ApiOperation(value = "Creates list of users with given input array", position = 2)
    public Response createUsersWithArrayInput(
            @ApiParam(value = "List of user object", required = true) User[] users) {
        for (User user : users) {
            userData.addUser(user);
        }
        return Response.ok().entity("").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createWithList")
    @ApiOperation(value = "Creates list of users with given input array", position = 3)
    public Response createUsersWithListInput(
            @ApiParam(value = "List of user object", required = true) java.util.List<User> users) {
        for (User user : users) {
            userData.addUser(user);
        }
        return Response.ok().entity("").build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    @ApiOperation(value = "Updated user", notes = "This can only be done by the logged in user.", position = 4)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid user supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response updateUser(
            @ApiParam(value = "name that need to be deleted", required = true) @PathParam("username") String username,
            @ApiParam(value = "Updated user object", required = true) User user) {
        userData.addUser(user);
        return Response.ok().entity("").build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    @ApiOperation(value = "Delete user", notes = "This can only be done by the logged in user.", position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid username supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response deleteUser(
            @ApiParam(value = "The name that needs to be deleted", required = true) @PathParam("username") String username) {
        if (userData.removeUser(username)) {
            return Response.ok().entity("").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    @ApiOperation(value = "Get user by user name", response = User.class, position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid username supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response getUserByName(
            @ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathParam("username") String username)
            throws ApiException {
        User user = userData.findUserByName(username);
        if (null != user) {
            return Response.ok().entity(user).build();
        } else {
            throw new NotFoundException(404, "User not found");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    @ApiOperation(value = "Logs user into the system", response = String.class, position = 6)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid username/password supplied") })
    public Response loginUser(
            @ApiParam(value = "The user name for login", required = true) @QueryParam("username") String username,
            @ApiParam(value = "The password for login in clear text", required = true) @QueryParam("password") String password) {
        return Response.ok()
                .entity("logged in user session:" + System.currentTimeMillis())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/logout")
    @ApiOperation(value = "Logs out current logged in user session", position = 7)
    public Response logoutUser() {
        return Response.ok().entity("").build();
    }
}
