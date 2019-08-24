/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Student;
import entity.StudentEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sinanjasar
 */
@Path("student")
public class StudentResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public StudentResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{\"selam\":\"aleykum\"}";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomStud() {
        Random rnd = new Random();
        List<Student> studs = new ArrayList();
        studs.add(new Student(0, "OB"));
        studs.add(new Student(1, "sne"));
        return new Gson().toJson(studs);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStud(@PathParam("id") Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new StudentEntity(1,"sne"));
            em.getTransaction().commit();
            return new Gson().toJson(em.find(StudentEntity.class, id));
        } finally {
            em.close();
        }
    }

}
