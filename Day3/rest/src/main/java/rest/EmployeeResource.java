/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sinanjasar
 */
@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "test";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        //TODO return proper representation object
        return new Gson().toJson(facade.getAllEmployees());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpID(@PathParam("id") Long id) {
        return new Gson().toJson(new EmployeeDTO(facade.getEmployeeById(id)));
    }
    
    @GET
    @Path("/highest")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighest() {
        return new Gson().toJson(facade.getEmployeesWithHighestSalary());
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighest(@PathParam("name") String name) {
        return new Gson().toJson(facade.getEmployeesByName(name));
    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
