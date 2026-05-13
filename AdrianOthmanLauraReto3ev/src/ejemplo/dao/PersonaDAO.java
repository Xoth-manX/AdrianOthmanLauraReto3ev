package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Persona;
import ejemplo.util.ConexionBD;

public class PersonaDAO implements GenericDAO<Persona> {

	@Override
	public boolean insertar(Persona persona) {
		String sql = "INSERT INTO personas (dni, nombre) VALUES (?, ?)";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setString(1, persona.getDni());
					pstmt.setString(2, persona.getNombre());

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						try (ResultSet rs = pstmt.getGeneratedKeys()) {
							if (rs.next()) {
								persona.setId_persona(rs.getInt(1)); // asigna el ID
								return true;
							}
						}
					}
					return false;

				} catch (SQLException e) {
					System.err.println("Error SQL al insertar '" + persona.getNombre() + "': " + e.getMessage());
					return false;
				}
	}

	@Override
	public List<Persona> obtenerTodos() {
		List<Persona> lista = new ArrayList<Persona>();
		String sql = "select id_persona, dni, nombre from personas order by nombre";
			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					lista.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todas las personas: " + e.getMessage());
			}
			return lista;
	}

	@Override
	public Persona obtenerPorId(int id) {
		Persona p = new Persona();
		String sql = """
				select id_persona, dni, nombre from personas where id_persona=?
				""";
		try (Connection conn = ConexionBD.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql)){
				
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					p = mapearFila(rs);
				}
				
			} catch (SQLException e) {
				System.err.println("Error SQL al obtener ID: " + e.getMessage());
			}
		return p;
	}
	
	public Persona mapearFila(ResultSet rs) throws SQLException {
		Persona p = new Persona();
		p.setId_persona(rs.getInt("id_persona"));
		p.setDni(rs.getString("dni"));
		p.setNombre(rs.getString("nombre"));
		return p;
	}
	
	@Override
	public boolean actualizar(Persona persona) {
		String sql = """
				update personas set dni=?, nombre=? where id_persona=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, persona.getDni());
			pstmt.setString(2, persona.getNombre());
			pstmt.setInt(3, persona.getId_persona());

			int filas = pstmt.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + persona.getId_persona() + ": " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = """
				delete from personas where id_persona=?
				""";
		try (Connection conn = ConexionBD.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			int filas = ps.executeUpdate();
			if (filas>0) {
				System.out.println("Se han eliminado " + filas + " filas");
			}
			
		} catch (SQLException e) {
			System.err.println("Error SQL al eliminar: " + e.getMessage());
		}
		return false;
	}

}
