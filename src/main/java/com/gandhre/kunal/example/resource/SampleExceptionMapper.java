package com.gandhre.kunal.example.resource;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.gandhre.kunal.example.exception.ApiException;
import com.gandhre.kunal.example.exception.NotFoundException;
import com.gandhre.kunal.example.model.ApiResponse;

@Provider
public class SampleExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof javax.ws.rs.WebApplicationException) {
            javax.ws.rs.WebApplicationException e = (javax.ws.rs.WebApplicationException) exception;
            return Response.status(e.getResponse().getStatus())
                    .entity(new ApiResponse(e.getResponse().getStatus(),
                            exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON).build();
        } else if (exception instanceof NotFoundException) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new ApiResponse(ApiResponse.ERROR,
                            exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON).build();
        } else if (exception instanceof BadRequestException) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new ApiResponse(ApiResponse.ERROR,
                            exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON).build();
        } else if (exception instanceof ApiException) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new ApiResponse(ApiResponse.ERROR,
                            exception.getMessage()))
                    .type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(500)
                    .entity(new ApiResponse(500, "something bad happened"))
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }
}