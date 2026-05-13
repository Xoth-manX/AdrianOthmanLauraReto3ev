package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Persona;
import ejemplo.modelo.Veterinario;
import ejemplo.util.ConexionBD;

public class VeterinarioDAO implements GenericDAO<Veterinario> {

	@Override
	public boolean insertar(Veterinario veterinario) {
		String sql = "INSERT INTO personas (dni, nombre) VALUES (?, ?)";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setString(1, veterinario.getDni());
					pstmt.setString(2, veterinario.getNombre());

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						try (ResultSet rs = pstmt.getGeneratedKeys()) {
							if (rs.next()) {
								veterinario.setId_persona(rs.getInt(1)); // asigna el ID
							}
						}
					}
				} catch (SQLException e) {
					System.err.println("Error SQL al insertar '" + veterinario.getId_persona() + "': " + e.getMessage());
					return false;
				}
		String sql2 = "INSERT INTO veterinarios (id_persona, num_colegiado) VALUES (?, ?)";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setInt(1, veterinario.getId_persona());
					pstmt.setString(2, veterinario.getNum_colegiado());

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						try (ResultSet rs = pstmt.getGeneratedKeys()) {
							if (rs.next()) {
								veterinario.setId_persona(rs.getInt(1)); // asigna el ID
							}
						}
					}

				} catch (SQLException e) {
					System.err.println("Error SQL al insertar '" + veterinario.getId_persona() + "': " + e.getMessage());
					return false;
				}
	}

	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> lista = new ArrayList<Veterinario>();
		String sql = """
				select id_veterinario, id_persona, num_ colegiado from veterinarios order by nombre
				""";
			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					lista.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los veterinarios: " + e.getMessage());
			}
			return lista;
	}
	
	public Veterinario mapearFila(ResultSet rs) throws SQLException {
		Veterinario v = new Veterinario();
		v.setId_veterinario(rs.getInt("id_veterinario"));
		v.setId_persona(rs.getInt("id_persona"));
		v.setNum_colegiado(rs.getString("num_colegiado"));
		return v;
	}
	
	@Override
	public Veterinario obtenerPorId(int id) {
		Veterinario v = new Veterinario();
		String sql = """
				select id_veterinario, id_persona, num_colegiado from veterinarios where id_veterinarios=?
				""";
		try (Connection conn = ConexionBD.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql)){
				
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					v = mapearFila(rs);
				}
				
			} catch (SQLException e) {
				System.err.println("Error SQL al obtener ID: " + e.getMessage());
			}
		return v;
	}

	@Override
	public boolean actualizar(Veterinario veterinario) {
		String sql = """
				update personas set id_persona=?, num_colegiado where id_veterinario=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, veterinario.getId_persona());
			pstmt.setString(2, veterinario.getNum_colegiado());
			pstmt.setInt(1, veterinario.getId_veterinario());

			int filas = pstmt.executeUpdate();
			if (filas>0) {
				System.out.println("Se han actualizado " + filas  + " filas");
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + veterinario.getId_veterinario() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
