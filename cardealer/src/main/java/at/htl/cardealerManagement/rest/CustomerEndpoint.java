package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/customers")
@Stateless
public class CustomerEndpoint {

    @PersistenceContext
    EntityManager em;

    @POST
    @Path("/insertCustomer")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(Customer customer) {
        em.persist(customer);
        System.out.println("insert Customer");
        return Response.ok().entity(customer).build();
    }

    @GET
    @Path("/getCustomer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") long id) {
        return em.find(Customer.class, id);
    }

    @GET
    @Path("/getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        return query.getResultList();
    }

    @PUT
    @Path("/updateCustomer/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id, Customer updatedCustomer) {
        if (updatedCustomer == null || em.find(Customer.class, id) == null){
            return Response.serverError().build();
        }
        updatedCustomer.setId(id);
        em.merge(updatedCustomer);
        return Response.ok().entity(em.find(Customer.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteCustomer/{id}")
    public void deleteCustomer(@PathParam("id") long id) {
        Customer customer = em.find(Customer.class, id);
        System.out.println(customer.getFirstName());
        if(customer != null) {
            em.remove(em.contains(customer) ? customer : em.merge(customer));
        }
    }
}
