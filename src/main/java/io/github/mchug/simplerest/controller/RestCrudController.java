package io.github.mchug.simplerest.controller;

import io.github.mchug.simplerest.model.JsonData;
import io.github.mchug.simplerest.model.impl.SimpleData;
import io.github.mchug.simplerest.service.RestCrudService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/rest")
public class RestCrudController {

    private final RestCrudService service = new RestCrudService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<SimpleData> all = service.getAll();

        List<JSONObject> allJsonObjectList = all
                .stream()
                .map(JsonData::toJSON)
                .collect(Collectors.toList());

        JSONArray allJsonArray = new JSONArray(allJsonObjectList);

        return Response.ok(allJsonArray.toString()).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response clear() {
        List<SimpleData> allDeleted = service.clear();

        List<JSONObject> allDeletedJsonObjectList = allDeleted
                .stream()
                .map(JsonData::toJSON)
                .collect(Collectors.toList());

        JSONArray allDeletedJsonArray = new JSONArray(allDeletedJsonObjectList);

        return Response.ok(allDeletedJsonArray.toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String newEntityJson)
    {
        try
        {
            JSONObject rawJsonObject = new JSONObject(newEntityJson);

            SimpleData newEntity = new SimpleData(rawJsonObject);

            SimpleData savedEntity = service.save(newEntity);

            return Response.ok(savedEntity.toJSON().toString()).build();
        }
        catch (IllegalArgumentException ex)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.toString()).build();
        }
        catch (Exception ex)
        {
            return Response.serverError().entity(ex.toString()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id)
    {
        SimpleData savedEntity = service.getById(id);

        if (savedEntity == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(savedEntity.toJSON().toString()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateById(@PathParam("id") Integer id, String newEntityJson)
    {
        try
        {
            JSONObject rawJsonObject = new JSONObject(newEntityJson);

            SimpleData newEntity = new SimpleData(rawJsonObject);

            SimpleData oldEntity = service.updateById(id, newEntity);

            return Response.ok(oldEntity.toJSON().toString()).build();
        }
        catch (IllegalArgumentException ex)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.toString()).build();
        }
        catch (Exception ex)
        {
            return Response.serverError().entity(ex.toString()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id)
    {
        try
        {
            SimpleData deletedEntity = service.deleteById(id);

            return Response.ok(deletedEntity.toJSON().toString()).build();
        }
        catch (IllegalArgumentException ex)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.toString()).build();
        }
        catch (Exception ex)
        {
            return Response.serverError().entity(ex.toString()).build();
        }
    }
}
