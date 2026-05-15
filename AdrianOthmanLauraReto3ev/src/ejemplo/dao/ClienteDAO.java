package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Cliente;
import ejemplo.util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente> {

	@Override
	public boolean insertar(Cliente cliente) {
		String sql = "INSERT INTO personas (dni, nombre) VALUES (?, ?)";
		String sql2 = "INSERT INTO clientes(id_persona,telefono) VALUES(?,?)";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, cliente.getDni());
			pstmt.setString(2, cliente.getNombre());

			if (pstmt.executeUpdate() > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();

				if (rs.next()) {
					int idPersona = rs.getInt(1);
					PreparedStatement pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, idPersona);
					pstmt.setInt(2, cliente.getTelefono());
					return pstmt2.executeUpdate() > 0;
				}
			}

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						cliente.setId_persona(rs.getInt(1)); // asigna el ID
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error SQL al insertar " + cliente.getId_persona() + "': " + e.getMessage());
		}
		return false;
	}

	@Override
	public List<Cliente> obtenerTodos() {
		List<Cliente> lista = new ArrayList<Cliente>();
		String sql = """
				select id_cliente, id_persona, telefono from clientes order by nombre
				""";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				lista.add(mapearFila(rs));

			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todos los clientes: " + e.getMessage());
		}
		return lista;
	}

	public Cliente mapearFila(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.setId_cliente(rs.getInt("id_cliente"));
		c.setId_persona(rs.getInt("id_persona"));
		c.setTelefono(rs.getInt("num_colegiado"));
		return c;
	}

	@Override
	public Cliente obtenerPorId(int id) {
		Cliente c = new Cliente();
		String sql = """
				select id_cliente, id_persona, telefono from cliente where id_cliente=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				c = mapearFila(rs);
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener ID: " + e.getMessage());
		}
		return c;
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		String sql = """
				update cliente set id_persona=?, telefono=? where id_cliente=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, cliente.getId_persona());
			pstmt.setInt(2, cliente.getTelefono());
			pstmt.setInt(3, cliente.getId_cliente());

			int filas = pstmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Se han actualizado " + filas + " filas");
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + cliente.getId_cliente() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean eliminar(int id) {
		String sql = """
				delete from cliente where id_cliente=?
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
