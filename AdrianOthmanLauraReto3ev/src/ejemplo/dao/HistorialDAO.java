package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import ejemplo.modelo.Historial;
import ejemplo.modelo.Mascota;
import ejemplo.util.ConexionBD;


public class HistorialDAO implements GenericDAO<Historial>{

	@Override
	public boolean insertar(Historial historial) {
		 String sql = """
		 	             insert into historial 
		 					(id_mascota, id_tratamiento, id_veterinario, fecha)
		 				values (?, ?, ?, ?)
		 		
		 		""";
		    try (Connection con = ConexionBD.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		          ps.setInt(1, historial.getId_mascota());
		          ps.setInt(2, historial.getId_tratamiento());
		          ps.setInt(3, historial.getId_veterinario());
		          ps.setObject(4, historial.getFecha());
		
		          int filas = ps.executeUpdate();
		          if (filas > 0) {
		                
		                return true;
		            }
		          
		      } catch (SQLException e) {
		            System.out.println("Error al insertar: " + e.getMessage());
		      }
		        return false;
		    }

	@Override
	public List<Historial> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Historial obtenerHistorialPorIdMacota(int id) {
		String sql = """
				select b.id_mascota b.id_tratamiento,b.id_veterinario,b.fecha from mascotas a inner join historial b on
				 a.id_mascota=b.id_mascota where a.id_mascota=?;

					""";
		    try (Connection con = ConexionBD.getConnection();
		            PreparedStatement ps = con.prepareStatement(sql)) {

		           ps.setString(1, "a.id_mascota");
		          
		           ResultSet rs = ps.executeQuery();
		           if (rs.next()) {
		             
	          return new Historial(rs.getInt("b.id_mascota"), rs.getInt("b.id_tratamiento"),rs.getInt("b.id_veterinario"),
	        		  (LocalDate)rs.getObject("b.fecha")); 
		               
		           }
		       } catch (SQLException e) {
		           System.out.println("Error al obtener municipio por id: " + e.getMessage());
		       }
		       return null;
		   }

	@Override
	public boolean actualizar(Historial objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Historial obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	 private Historial mapearFila(ResultSet rs) throws SQLException {
    	 Historial a = new  Historial();
	  a.setId_mascota(rs.getInt("id_mascota"));
	  a.setId_tratamiento( rs.getInt("nombre"));
	  a.setId_veterinario( rs.getInt("especie"));
	  a.setFecha( rs.getObject("fecha",LocalDate.class));
	  
	  return a;
    }
}
