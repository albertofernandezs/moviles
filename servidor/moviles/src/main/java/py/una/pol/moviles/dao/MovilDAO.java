package py.una.pol.moviles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.moviles.model.Movil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Stateless
public class MovilDAO {
	
	 @Inject
	    private Logger log;
	    
		/**
		 * 
		 * @param condiciones 
		 * @return
		 */
		public List<Movil> seleccionar() {
			String query = "SELECT * FROM movil ";
			
			List<Movil> lista = new ArrayList<Movil>();
			
			Connection conn = null; 
	        try 
	        {
	        	conn = Bd.connect();
	        	ResultSet rs = conn.createStatement().executeQuery(query);
	        	while(rs.next()) {
	        		Movil p = new Movil();
	        		p.setId(rs.getLong(1));
	        		p.setTipo(rs.getString(2));
	        		p.setLatitud(rs.getDouble(3));
	        		p.setLongitud(rs.getDouble(4));
	        		p.setFecha(rs.getString(5));
	        		p.setHora(rs.getTime(6));
	        		lista.add(p);
	        	}
	        	
	        } catch (SQLException ex) {
	            log.severe("Error en la seleccion: " + ex.getMessage());
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
			return lista;

		}
		
		public Movil seleccionarPorId(long id) {
			String SQL = "SELECT m FROM Movil m WHERE m.id = ? ";
			
			Movil p = null;
			
			Connection conn = null; 
	        try 
	        {
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL);
	        	pstmt.setLong(1, id);
	        	
	        	ResultSet rs = pstmt.executeQuery();

	        	while(rs.next()) {
	        		//java.sql.Date sqlTime= rs.getDate(6);
	        		//Date date=new Date(sqlTime.getTime()); 
	        		p = new Movil();
	        		p.setId(rs.getLong(1));
	        		p.setTipo(rs.getString(2));
	        		p.setLatitud(rs.getDouble(3));
	        		p.setLongitud(rs.getDouble(4));
	        		p.setFecha(rs.getString(5));
	        		p.setHora(rs.getTime(6));
	        	}
	        	
	        } catch (SQLException ex) {
	        	log.severe("Error en la seleccion: " + ex.getMessage());
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
			return p;

		}
		public void insertar(Movil m) throws SQLException {

	        String SQL = "INSERT INTO movil(id, tipo, latitud, longitud) "
	                + "VALUES(?,?,?,?)";
	 
	        
	        Connection conn = null;
	        
	        try 
	        {
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setLong(1, m.getId());
	            pstmt.setString(2, m.getTipo());
	            pstmt.setDouble(3, 0);
	            pstmt.setDouble(4, 0);
	            
	 
	            int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	                // get the ID back
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        //id = rs.getLong(1);
	                    }
	                } catch (SQLException ex) {
	                	throw ex;
	                }
	            }
	        } catch (SQLException ex) {
	        	throw ex;
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
	        	
	        
	    	
	    	
	    }
	    public void registrar(Long id, Double latitud, Double longitud) throws SQLException {

	        String SQL = "UPDATE Movil SET latitud = ? , longitud = ?, fecha=?, hora=? WHERE id = ? ";
	 
	        Connection conn = null;
	        
	        try 
	        {
	        	java.util.Date today = new Date();
	        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        	final String stringDate= dateFormat.format(today);
	        	java.sql.Time sqlTime = new java.sql.Time(today.getTime());
	        	
	        	today=dateFormat.parse(stringDate);
	        	java.sql.Date sql = new java.sql.Date(today.getTime());
	        	
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setDouble(1, latitud);
	            pstmt.setDouble(2, longitud);
	            pstmt.setDate(3, sql);
	            pstmt.setTime(4, sqlTime);
	            pstmt.setLong(5, id);
	            
	 
	            int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	                // get the ID back
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        id = rs.getLong(1);
	                    }
	                } catch (SQLException ex) {
	                    System.out.println(ex.getMessage());
	                }
	            }
	        } catch (SQLException ex) {
	        	log.severe("Error en la actualizacion: " + ex.getMessage());
	        }
	        catch(ParseException e) {
	        	log.severe("Error de parse");
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }

	    }
	    
	  


}
