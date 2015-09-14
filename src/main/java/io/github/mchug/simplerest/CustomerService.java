package io.github.mchug.simplerest;

import java.util.concurrent.CopyOnWriteArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
public class CustomerService {

    private final CopyOnWriteArrayList<Customer> cList = CustomerList.getInstance();

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCustomers() {
        return "---Customer List---\n" + cList.toString();
    }

    @GET
    @Path("/allJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomersJSON() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Customer cust : cList) {
            arrayBuilder.add(cust.toJson());
        }

        return arrayBuilder.build().toString();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCustomer(@PathParam("id") long id) {

        for (Customer cust : cList) {
            if (cust.getId() == id) {
                return "---Customer---\n" + cust.toString();
            }
        }

        return "Customer not found";
    }
}
