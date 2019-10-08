package at.htl.cardealerManagement.rest;

import at.htl.cardealerManagement.model.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/carExemplars")
public class CarExemplarEndpoint {

    @PersistenceContext
    protected EntityManager em;

    @POST
    @Path("/insertCarExemplar")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCarExemplar(CarExemplar carExemplar) {
        System.out.println("****inserted CarExemplar");
        em.persist(carExemplar);
        return Response.ok().entity(carExemplar).build();
    }

    @GET
    @Path("/getCarExemplar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CarExemplar getCar(@PathParam("id") long id) {
        return em.find(CarExemplar.class, id);
    }


    @GET
    @Path("/getCarExemplars")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarExemplar> getCustomers(){
        TypedQuery<CarExemplar> query = em.createNamedQuery("CarExemplar.findAll", CarExemplar.class);
        return query.getResultList();
    }

    @PUT
    @Path("/updateCarExemplar/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(@PathParam("id") long id, CarExemplar updatedCarExemplar) {
        if (updatedCarExemplar == null || em.find(CarExemplar.class, id) == null){
            return Response.serverError().build();
        }
        updatedCarExemplar.setId(id);
        em.merge(updatedCarExemplar);
        return Response.ok().entity(em.find(CarExemplar.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteCarExemplar/{id}")
    public void deleteCar(@PathParam("id") long id) {
        CarExemplar carExemplar = em.find(CarExemplar.class, id);
        if(carExemplar != null) {
            em.remove(em.contains(carExemplar) ? carExemplar : em.merge(carExemplar));
        }
    }
}
