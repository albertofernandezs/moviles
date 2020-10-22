package py.una.pol.personas.rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.una.pol.personas.model.Movil;
import py.una.pol.personas.service.MovilService;



/**
 * JAX-RS Example
 * <p/>
 * Esta clase produce un servicio RESTful para leer y escribir contenido de personas
 */
@Path("/moviles")
@RequestScoped
public class MovilRESTService {
	
	@Inject
    private Logger log;

    @Inject
    MovilService movilService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movil> listar() {
        return movilService.seleccionar();
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Movil m) {

        Response.ResponseBuilder builder = null;

        try {
            movilService.crear(m);
            // Create an "ok" response
            
            //builder = Response.ok();
            builder = Response.status(201).entity("Movil creado exitosamente");
            
        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }
    
    @GET
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Movil obtenerPorIdQuery(@QueryParam("id") long id) {
        Movil p = movilService.seleccionarId(id);
        if (p == null) {
        	log.info("obtenerPorId " + id + " no encontrado.");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        log.info("obtenerPorId " + id + " encontrada: " + p.getId());
        return p;
    }
    
    @POST
    @Path("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrar(Movil m) {
    	
    	 Response.ResponseBuilder builder = null;
    	 
    	 try {
             movilService.registrar(m);
             // Create an "ok" response
             
             //builder = Response.ok();
             builder = Response.status(201).entity("Movil registrado exitosamente");
             
         } catch (SQLException e) {
             // Handle the unique constrain violation
             Map<String, String> responseObj = new HashMap<>();
             responseObj.put("bd-error", e.getLocalizedMessage());
             builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
         } catch (Exception e) {
             // Handle generic exceptions
             Map<String, String> responseObj = new HashMap<>();
             responseObj.put("error", e.getMessage());
             builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
         }
    	 
    	 return builder.build();
    }
    
    @GET
    @Path("/ubicacion")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movil> obtenerPorUbicacion(@QueryParam("latitud") double la, @QueryParam("longitud") double lo, @QueryParam("rango") int rango) {
    	return movilService.listar_por_ubicacion(la, lo, rango);
    }
    
    
    

}

