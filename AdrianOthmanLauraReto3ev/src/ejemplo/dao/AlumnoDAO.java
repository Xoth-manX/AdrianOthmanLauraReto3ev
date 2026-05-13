package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Alumno;
import util.ConexionBD;

public class AlumnoDAO implements GenericDAO<Alumno>{
	@Override
	public boolean insertar(Alumno alumno) {
		String sql = """
				INSERT INTO alumnos (nombre, edad,
				curso, repetidor) VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = ConexionBD.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, alumno.getNombre());
			pstmt.setInt(2, alumno.getEdad());
			pstmt.setString(3, alumno.getCurso());
			pstmt.setBoolean(4, alumno.isRepetidor());

			int filas = pstmt.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						alumno.setId(rs.getInt(1)); // asigna el ID
						return true;
					}
				}
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error SQL al insertar '" + alumno.getNombre() + "': " + e.getMessage());
			return false;
		}
	}


	@Override
	public List<Alumno> obtenerTodos() {
		List<Alumno> alumnos = new ArrayList<>();
	    String sql = "SELECT id, nombre, edad, curso, repetidor FROM alumnos ORDER BY nombre";

			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					alumnos.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los alumnos: " + e.getMessage());
			}
			return alumnos;

	}


	private Alumno mapearFila(ResultSet rs) throws SQLException {
		Alumno a = new Alumno();
		a.setId(rs.getInt("id"));
		a.setNombre(rs.getString("nombre"));
		a.setEdad(rs.getInt("edad"));
		a.setCurso(rs.getString("curso"));
		a.setRepetidor(rs.getBoolean("repetidor"));
		return a;
	}



	@Override
	public Alumno obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Alumno alumno) {
		String sql = """
				UPDATE alumnos SET nombre=?, edad=?, curso=?, repetidor=? WHERE id=?               
				""";

		try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, alumno.getNombre());
			pstmt.setInt(2, alumno.getEdad());
			pstmt.setString(3, alumno.getCurso());
			pstmt.setBoolean(4, alumno.isRepetidor());
			pstmt.setInt(5, alumno.getId());

			int filas = pstmt.executeUpdate();
			return filas > 0; // false si el ID no existía en la BD

		} catch (SQLException e) {
			System.err.println("Error SQL al actualizar ID " + alumno.getId() + ": " + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
