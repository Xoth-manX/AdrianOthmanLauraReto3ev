package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Mascota;
import ejemplo.modelo.Tratamiento;
import ejemplo.util.ConexionBD;


public class MascotaDAO implements GenericDAO<Mascota>{

	@Override
	public boolean insertar(Mascota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Mascota> obtenerTodos() {
		String sql = "SELECT is_mascota,nombremespecie,fechaNacimiento,peso FROM mascotas" ;
		ArrayList<Mascota> lista=new ArrayList<>();
	    try (Connection con = ConexionBD.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {

	           
	          
	           ResultSet rs = ps.executeQuery();
	           while (rs.next()) {
	             
          lista.add(new Mascota(rs.getInt("id_cliente"), rs.getString("nombre"),rs.getString("especie"),
        		  (localdate)rs.getObject("fechaNacimiento"),rs.getDouble("peso"))); 
	               
	           }
	       } catch (SQLException e) {
	           System.out.println("Error al obtener municipio por id: " + e.getMessage());
	       }
	       return lista;
	   }

	@Override
	public Mascota obtenerPorId(int id) {
	
		
		       return null;
		   }

	@Override
	public boolean actualizar(Mascota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
