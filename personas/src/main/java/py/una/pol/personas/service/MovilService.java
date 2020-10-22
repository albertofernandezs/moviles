package py.una.pol.personas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.dao.MovilDAO;
import py.una.pol.personas.model.Movil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MovilService {
	
	 @Inject
	  private Logger log;

	 @Inject
	 private MovilDAO dao;
	 
	 public void crear(Movil m) throws Exception {
	        log.info("Creando Movil: " + m.getId() + " " + m.getTipo());
	        try {
	        	dao.insertar(m);
	        }catch(Exception e) {
	        	log.severe("ERROR al crear movil: " + e.getLocalizedMessage() );
	        	throw e;
	        }
	        log.info("Movil creado con éxito: " + m.getId() + " " + m.getTipo() );
	  }
	 public List<Movil> seleccionar() {
	    	return dao.seleccionar();
	 }
	 
	 public Movil seleccionarId(Long id) {
	    	return dao.seleccionarPorId(id);
	    }
	 
	 public void registrar(Movil m) throws Exception {
		 log.info("Registrando movil: " + m.getId() + " " + m.getLatitud()+ " "+ m.getLongitud() );
		 try {
	        	dao.registrar(m.getId(), m.getLatitud(), m.getLongitud());
	        }catch(Exception e) {
	        	log.severe("ERROR al registrar el movil: " + e.getLocalizedMessage() );
	        	throw e;
	        }
	        log.info("Movil registrado con éxito: " + m.getId() + " " + m.getLatitud()+ " "+ m.getLongitud() );
	 }
	 
	 public List<Movil> listar_por_ubicacion(Double latitud, Double longitud, int metros){
		 List<Movil> lista= dao.seleccionar();
		 List<Movil> listaFinal= new ArrayList<Movil>();
		 for(Movil m: lista) {
			 if(m.getLongitud()> 0 && m.getLatitud() >0) {
				 int distancia= calculateDistanceByHaversineFormula(longitud, latitud, m.getLongitud(), m.getLatitud());
				 if(distancia<=metros) {
					 listaFinal.add(m);
				 }
			 }
		 }
		 return listaFinal;
		 
	 }
	 
	 private static int calculateDistanceByHaversineFormula(double lon1, double lat1,
			 double lon2, double lat2) {

			 double earthRadius = 6371; // km

			 lat1 = Math.toRadians(lat1);
			 lon1 = Math.toRadians(lon1);
			 lat2 = Math.toRadians(lat2);
			 lon2 = Math.toRadians(lon2);

			 double dlon = (lon2 - lon1);
			 double dlat = (lat2 - lat1);

			 double sinlat = Math.sin(dlat / 2);
			 double sinlon = Math.sin(dlon / 2);

			 double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
			 double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

			 double distanceInMeters = earthRadius * c * 1000;

			 return (int)distanceInMeters;

	}
	 
	 

}
