package py.una.pol.moviles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;


@SuppressWarnings("serial")
@XmlRootElement
public class Movil  implements Serializable {

	Long id;
	String tipo;
	Double latitud;
	Double longitud;
	String fecha;
	Time hora;

	
	public Movil(){
		
	}

	public Movil(Long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Movil(Long id, String tipo, Double latitud, Double longitud, String fecha) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}


	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

		
	

	
}