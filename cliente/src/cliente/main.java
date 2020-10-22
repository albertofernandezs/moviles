package cliente;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class main {

	public static void main(String[] args) {
		
		//Movil m= new Movil(4,"lancha");
		//Movil m2= new Movil(4,150,150);
		//Client cliente = ClientBuilder.newClient();
		//WebTarget target1 = cliente.target("http://localhost:8080/personas/rest/").path("moviles");
		//WebTarget target2 = cliente.target("http://localhost:8080/personas/rest/").path("/moviles/registro");
		//String[] s =new String[1];
		//s[0]=MediaType.APPLICATION_JSON;
		//Invocation.Builder invocationBuilder1 =  target1.request(s);
		//Invocation.Builder invocationBuilder2 =  target2.request(s);
		//Response response1 = invocationBuilder1.post(Entity.entity(m, MediaType.APPLICATION_JSON));
		//Response response2 = invocationBuilder2.post(Entity.entity(m2, MediaType.APPLICATION_JSON));
		//System.out.println(response1.getStatus());
		//System.out.println(response2.getStatus());
		obtenerPorUbicacion(100,100,213400000);
		
	}
	
	public static void obtenerPorUbicacion(long latitud, long longitud, int rango){
		
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080/personas/rest/").path("/moviles/ubicacion")
		.queryParam("latitud", latitud)
		.queryParam("longitud", longitud)
		.queryParam("rango", rango);
		
		String[] s =new String[1];
		s[0]=MediaType.APPLICATION_JSON;
		Invocation.Builder invocationBuilder =  target.request(s);
		/*Form form = new Form();
		form.param("latitud", Long.toString(latitud));
		form.param("longitud", Long.toString(longitud));
		form.param("rango", Integer.toString(rango));*/
		Response response = invocationBuilder.get(Response.class);
		List<Movil> listaf = response.readEntity(new GenericType<List<Movil>>() {});
		System.out.println("cantidad: "+listaf.size());
		
		
		
		//List<Movil> lista= (List<Movil>)response.getEntity();
		
		
		
		
		for(Movil m: listaf) {
			System.out.println("Id: "+m.getId()+", Tipo: "+m.getTipo()+", Latitud: "+m.getLatitud()+", Longitud: "+m.getlongitud()+", Fecha: "+m.getFecha()+ ", Hora: "+m.getHora());
			
		}
		
		
	}

}
