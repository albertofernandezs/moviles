package cliente;

import java.util.List;
import java.util.Scanner;

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

		// Movil m= new Movil(4,"lancha");
		// Movil m2= new Movil(4,150,150);
		// Client cliente = ClientBuilder.newClient();
		// WebTarget target1 =
		// cliente.target("http://localhost:8080/moviles/rest/").path("moviles");
		// WebTarget target2 =
		// cliente.target("http://localhost:8080/moviles/rest/").path("/moviles/registro");
		// String[] s =new String[1];
		// s[0]=MediaType.APPLICATION_JSON;
		// Invocation.Builder invocationBuilder1 = target1.request(s);
		// Invocation.Builder invocationBuilder2 = target2.request(s);
		// Response response1 = invocationBuilder1.post(Entity.entity(m,
		// MediaType.APPLICATION_JSON));
		// Response response2 = invocationBuilder2.post(Entity.entity(m2,
		// MediaType.APPLICATION_JSON));
		// System.out.println(response1.getStatus());
		// System.out.println(response2.getStatus());
		// obtenerPorUbicacion(100,100,213400000);
		menu();

	}


	public static void menu() {
		String op;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Elija una de las siguientes opciones: ");
			System.out.println("1- Crear un movil");
			System.out.println("2- Listar moviles");
			System.out.println("3- Registrar ubicacion de un movil");
			System.out.println("4- Listar moviles de acuerdo a una ubicacion");
			System.out.println("5- Cerrar");
			//op = "";
			op = sc.nextLine();
			//System.out.println("la operacion elegida es: "+op);
			if (op.equals("1")) {
				System.out.println("Ingrese el id y tipo del movil");
				int id= sc.nextInt();
				String tipo= sc.nextLine();
				crearMovil(id,tipo);
			} else if (op.equals("3")) {
				System.out.println("Ingrese id, longitud y latitud");
				int id= sc.nextInt();
				long la= sc.nextLong();
				long lo= sc.nextLong();
				registrarMovil(id,la,lo);
			} else if (op.equals("4")) {
				System.out.println("Ingrese latitud, longitud y rango en metros");
				long la= sc.nextLong();
				long lo= sc.nextLong();
				int rango= sc.nextInt();
				obtenerPorUbicacion(la,lo,rango);
			} else if(op.equals("2")) {
				mostrarMoviles();
			}else if(op.equals("5")){
				System.out.println("Usted salio");
				break;
			}
		}while (op != "5");

	}
	
	public static void crearMovil(int id, String tipo) {
		Movil m= new Movil(id,tipo);
		Client cliente = ClientBuilder.newClient();
		WebTarget target1 =
		cliente.target("http://localhost:8080/moviles/rest/").path("moviles");
		String[] s =new String[1];
		s[0]=MediaType.APPLICATION_JSON;
		Invocation.Builder invocationBuilder1 = target1.request(s);
		Response response1 = invocationBuilder1.post(Entity.entity(m,MediaType.APPLICATION_JSON));
		System.out.println(response1.getStatus());
	}
	
	public static  void registrarMovil(int id, double latitud, double longitud) {
		Movil m= new Movil(id, latitud,longitud);
		Client cliente = ClientBuilder.newClient();
		WebTarget target1 =
		cliente.target("http://localhost:8080/moviles/rest/").path("moviles/registro");
		String[] s =new String[1];
		s[0]=MediaType.APPLICATION_JSON;
		Invocation.Builder invocationBuilder1 = target1.request(s);
		Response response1 = invocationBuilder1.post(Entity.entity(m,MediaType.APPLICATION_JSON));
		System.out.println(response1.getStatus());
	}
	
	public static void mostrarMoviles() {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080/moviles/rest/").path("/moviles");
		String[] s = new String[1];
		s[0] = MediaType.APPLICATION_JSON;
		Invocation.Builder invocationBuilder = target.request(s);
		Response response = invocationBuilder.get(Response.class);
		List<Movil> listaf = response.readEntity(new GenericType<List<Movil>>() { });
		for (Movil m : listaf) {
			System.out.println("Id: " + m.getId() + ", Tipo: " + m.getTipo() + ", Latitud: " + m.getLatitud()
					+ ", Longitud: " + m.getlongitud() + ", Fecha: " + m.getFecha() + ", Hora: " + m.getHora());

		}
		
		
	}
	public static void obtenerPorUbicacion(long latitud, long longitud, int rango) {

		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8080/moviles/rest/").path("/moviles/ubicacion")
				.queryParam("latitud", latitud).queryParam("longitud", longitud).queryParam("rango", rango);

		String[] s = new String[1];
		s[0] = MediaType.APPLICATION_JSON;
		Invocation.Builder invocationBuilder = target.request(s);
		Response response = invocationBuilder.get(Response.class);
		List<Movil> listaf = response.readEntity(new GenericType<List<Movil>>() {});
		for (Movil m : listaf) {
			System.out.println("Id: " + m.getId() + ", Tipo: " + m.getTipo() + ", Latitud: " + m.getLatitud()
					+ ", Longitud: " + m.getlongitud() + ", Fecha: " + m.getFecha() + ", Hora: " + m.getHora());

		}

	}

}
