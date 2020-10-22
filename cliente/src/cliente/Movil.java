package cliente;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;

@SuppressWarnings("serial")
@XmlRootElement
public class Movil  implements Serializable{

	long id;
	String tipo;
	double latitud;
	double longitud;
	String fecha;
	String hora;

	
	public Movil(){
		
	}

	public Movil(long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	public Movil(long id, double latitud, double longitud) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Movil(long id, String tipo, double latitud, double longitud, String fecha) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fecha = fecha;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getlongitud() {
		return longitud;
	}

	public void setlongitud(double longitud) {
		this.longitud = longitud;
	}


	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

		
	

	
}