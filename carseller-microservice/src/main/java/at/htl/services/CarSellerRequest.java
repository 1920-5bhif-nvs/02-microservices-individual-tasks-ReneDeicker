package at.htl.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
public interface CarSellerRequest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employees/getEmployees")
    JsonArray getEmployees();
}
