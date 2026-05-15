package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Mascota;
import ejemplo.modelo.Tratamiento;
import ejemplo.util.ConexionBD;


public class MascotaDAO implements GenericDAO<Mascota>{

	@Override
	public boolean insertar(Mascota mascota) {
		

	    String sql = "insert into mascotas(id_cliente,nombre,especie,fecha_nacimiento,peso)values(?,?,?,?,?);";
	    try (Connection con = ConexionBD.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	          ps.setInt(1, mascota.getId_cliente());
	          ps.setString(2, mascota.getNombre());
	          ps.setString(3, mascota.getEspecie());
	          ps.setObject(4, mascota.getFechaNacimiento());
	          ps.setDouble(5, mascota.getPeso());
	          int filas = ps.executeUpdate();
	          if (filas > 0) {
	                ResultSet rs = ps.getGeneratedKeys();
	                if (rs.next()) {
	                    mascota.setId_cliente(1);
	                }
	                return true;
	            }
	      } catch (SQLException e) {
	            System.out.println("Error al insertar: " + e.getMessage());
	      }
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
	             
					lista.add(mapearFila(rs)); 
	               
	           }
	       } catch (SQLException e) {
	           System.out.println("Error al obtener municipio por id: " + e.getMessage());
	       }
	       return lista;
	   }
    private Mascota mapearFila(ResultSet rs) throws SQLException {
    	 Mascota a = new  Mascota();
	  a.setId_cliente(rs.getInt("id_cliente"));
	  a.setNombre( rs.getString("nombre"));
	  a.setEspecie( rs.getString("especie"));
	  a.setFechaNacimiento( rs.getObject("fechaNacimineto",LocalDate.class));
	  a.setPeso( rs.getDouble("peso"));
	  return a;
    }
	@Override
	public Mascota obtenerPorId(int id) {
	
		
		       return null;
		   }

	@Override
	public boolean actualizar(Mascota mascota) {
		   String sql = "Update mascotas set nombre=?,set especie=?,set fechaNacimiento, set peso=? where id_cliente=?";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
			  
		          ps.setString(1, mascota.getNombre());
		          ps.setString(2, mascota.getEspecie());
		          ps.setObject(3, mascota.getFechaNacimiento());
		          ps.setDouble(4, mascota.getPeso());
		          ps.setInt(5, mascota.getId_cliente());
		          
		          
		          return ps.executeUpdate() > 0;
		    
		    } catch (SQLException e) {
		            System.out.println("Error: " + e.getMessage());
		            
		    }

		return false;
		}
		   
		    

	@Override
	public boolean eliminar(int id) {
		String sql = """

				delete m from mascotas m
				left join facturas f on m.id_mascota = f.id_mascota
				where m.id_mascota = ? and f.id_factura is null

				""";



		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			int filas = pstmt.executeUpdate();

			if (filas > 0) {

				System.out.println("Se han eliminado " + filas + " filas");

			}



		} catch (SQLException e) {

			System.err.println("Error SQL al eliminar cliente " + id + ": " + e.getMessage());

			return false;

		}

		return true;

	}
}


