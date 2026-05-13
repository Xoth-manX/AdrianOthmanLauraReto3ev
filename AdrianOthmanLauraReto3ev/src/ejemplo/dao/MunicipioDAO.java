package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Municipio;
import modelo.Provincia;
import util.ConexionBD;

public class MunicipioDAO implements GenericDAO<Municipio> {

	public boolean eliminarPorNombre(String nombre) {
		String sql = """
				delete from municipios where nombre=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, nombre);
			
			int filas = pstmt.executeUpdate();
			if (filas >= 0) {
				System.out.println("Se han eliminado " + filas + " filas");
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
		}
		System.out.println();
		return false;
	}

	public boolean actualizarHabitantesPorNombre(String nombre, int nuevosHabitantes) {
		String sql = """
				update municipios set habitantes=? where nombre=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, nuevosHabitantes);
			pstmt.setString(2, nombre);
			int filas = pstmt.executeUpdate();

			if (filas >= 0) {
				System.out.println("Se han actualizado " + filas + " filas");
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println();
		return false;
	}

	public int sumaHabitantesPorProvincia(int provincia_id) {
		int hab = 0;
		String sql = """
				select sum(habitantes) sumatorio from municipios where provincia_id=?
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, provincia_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return hab = rs.getInt("sumatorio");
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
		}
		System.out.println();
		return 0;
	}

	public Municipio obtenerCapitalPorProvincia(int provincia_id) {
		String sql = """
				select m.id, m.nombre, m.habitantes, m.es_capital, m.provincia_id from
				municipios m inner join provincias p on p.id=m.provincia_id
				where m.es_capital=1 and m.provincia_id=?;
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, provincia_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				return mapearFila(rs);
			}
		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
		}
		System.out.println();
		return null;
	}

	public ArrayList<Municipio> obtenerPorProvincia(int provincia_id) {
		ArrayList<Municipio> municipios = new ArrayList<Municipio>();
		String sql = """
				select id, nombre, habitantes, es_capital, provincia_id from municipios where provincia_id = ?;
				""";
		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, provincia_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				municipios.add(mapearFila(rs));
			}
		} catch (SQLException e) {
			System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
		}
		return municipios;
	}

	public Municipio mapearFila(ResultSet rs) throws SQLException {
		Municipio m = new Municipio();
		m.setId(rs.getInt("id"));
		m.setNombre(rs.getString("nombre"));
		m.setHabitantes(rs.getInt("habitantes"));
		m.setEs_capital(rs.getBoolean("es_capital"));
		m.setProvincia_id(rs.getInt("provincia_id"));
		return m;
	}

	@Override
	public boolean insertar(Municipio objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Municipio> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Municipio obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Municipio objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
