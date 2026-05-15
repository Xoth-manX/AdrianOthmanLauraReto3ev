package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ejemplo.modelo.Tratamiento;
import ejemplo.util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento>{

	@Override
	public boolean insertar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tratamiento> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tratamiento obtenerPorId(int id) {
		String sql = """
				select a.nombre,a.precio from tratamientos a inner join historial b on
				 a.id_tratamiento=b.id_tratamiento join veterinarios c on b.id_veterinario=c.id_veterinario where c.id_veterinario=?;

				""";
	    try (Connection con = ConexionBD.getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {

	           ps.setString(1, "id_veterinario");
	          
	           ResultSet rs = ps.executeQuery();
	           if (rs.next()) {
	             
          return new Tratamiento(rs.getString("a.nombre"), rs.getDouble("a.precio")); 
	               
	           }
	       } catch (SQLException e) {
	           System.out.println("Error al obtener municipio por id: " + e.getMessage());
	       }
	       return null;
	   }

	@Override
	public boolean actualizar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
