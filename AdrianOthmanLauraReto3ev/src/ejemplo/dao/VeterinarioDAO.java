package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Veterinario;
import ejemplo.util.ConexionBD;

public class VeterinarioDAO implements GenericDAO<Veterinario> {

	@Override
	public boolean insertar(Veterinario veterinario) {

		String sql = "INSERT INTO personas (dni, nombre) VALUES (?, ?)";
		String sql2 = "INSERT INTO veterinarios(id_persona, num_colegiado) VALUES (?, ?)";

		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			// INSERT PERSONA
			pstmt.setString(1, veterinario.getDni());
			pstmt.setString(2, veterinario.getNombre());

			int filas = pstmt.executeUpdate();

			if (filas == 0) {
				return false;
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					int idPersona = rs.getInt(1);
					veterinario.setId_persona(idPersona);
					try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
						pstmt2.setInt(1, idPersona);
						pstmt2.setString(2, veterinario.getNum_colegiado());
						return pstmt2.executeUpdate() > 0;
					}
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar: " + e.getMessage());
		}
		return false;
	}

	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> lista = new ArrayList<Veterinario>();
		String sql = """
				select id_veterinario, id_persona, num_colegiado from veterinarios
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
				select id_veterinario, id_persona, num_colegiado from veterinarios where id_veterinario=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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
				update veterinarios set id_persona=?, num_colegiado=? where id_veterinario=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, veterinario.getId_persona());
			pstmt.setString(2, veterinario.getNum_colegiado());
			pstmt.setInt(3, veterinario.getId_veterinario());

			int filas = pstmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Se han actualizado " + filas + " filas");
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + veterinario.getId_veterinario() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean eliminar(int id) {
		String sql = """
				delete from veterinarios where id_veterinario=?
				""";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			int filas = pstmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Se han eliminado " + filas + " filas");
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al eliminar veterinario " + id + ": " + e.getMessage());
			return false;
		}
		return true;
	}

}
