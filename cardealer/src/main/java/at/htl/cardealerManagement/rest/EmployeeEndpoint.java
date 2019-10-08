package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.model.Customer;
import at.htl.cardealerManagement.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/employees")
@Stateless
public class EmployeeEndpoint {
    @PersistenceContext
    EntityManager em;

    @POST
    @Path("/insertEmployee")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertEmployee(Employee employee) {
        em.persist(employee);
        return Response.ok().entity(employee).build();
    }

    @GET
    @Path("/getEmployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") long id) {
        return em.find(Employee.class, id);
    }

    @GET
    @Path("/getEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getCustomers(){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
        return query.getResultList();
    }

    @PUT
    @Path("/updateEmployee/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(@PathParam("id") long id, Employee updatedEmployee) {
        if (updatedEmployee == null || em.find(Employee.class, id) == null){
            return Response.serverError().build();
        }
        updatedEmployee.setId(id);
        em.merge(updatedEmployee);
        return Response.ok().entity(em.find(Employee.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteEmployee/{id}")
    public void deleteEmployee(@PathParam("id") long id) {
        Employee employee = em.find(Employee.class, id);
        if(employee != null) {
            em.remove(em.contains(employee) ? employee : em.merge(employee));
        }
    }
}
