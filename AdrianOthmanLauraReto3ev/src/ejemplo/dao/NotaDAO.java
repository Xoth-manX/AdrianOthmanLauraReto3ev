package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Alumno;
import modelo.Nota;
import util.ConexionBD;

public class NotaDAO implements GenericDAO<Nota> {

	public boolean update() {
		String sql = "update notas set calificacion='10.00' where ";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setString(1, "Sistemas informáticos");
					pstmt.setDouble(2, 7.00);
					pstmt.setInt(3, 3);

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						System.out.println("Filas insertadas: " + filas);
					}
					return false;

				} catch (SQLException e) {
					System.err.println("Error SQL al insertar: " + e.getMessage());
					return false;
				}
	}
	
	public boolean insertar() {
		String sql = "insert into notas(asignatura, calificacion, id_alumno) values(?, ?, ?)";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setString(1, "Sistemas informáticos");
					pstmt.setDouble(2, 7.00);
					pstmt.setInt(3, 3);

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						System.out.println("Filas insertadas: " + filas);
					}
					return false;

				} catch (SQLException e) {
					System.err.println("Error SQL al insertar: " + e.getMessage());
					return false;
				}
	}
	@Override
	public List<Nota> obtenerTodos() {
		List<Nota> notas = new ArrayList<>();
	    String sql = "SELECT id, asignatura, calificacion, id_alumno FROM notas ORDER BY calificacion";

			try (Connection conn = ConexionBD.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					notas.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
			}
			return notas;
	}

	private Nota mapearFila(ResultSet rs) throws SQLException {
		Nota n = new Nota();
		n.setId(rs.getInt("id"));
		n.setAsignatura(rs.getString("asignatura"));
		n.setCalificacion(rs.getDouble("calificacion"));
		n.setIdAlumno(rs.getInt("id_alumno"));
		return n;
	}
	
	@Override
	public Nota obtenerPorId(int id) {
		List<Nota> notas = new ArrayList<>();
		String sql = "select id, asignatura, calificacion, id_alumno from notas where id = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				return mapearFila(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public List<Nota> obtenerPorIdAlumno(int id) {
		List<Nota> notas = new ArrayList<>();
		String sql = "select id, asignatura, calificacion, id_alumno from notas where id_alumno = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				notas.add(mapearFila(rs));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notas;
	}
	@Override
	public boolean actualizar(Nota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean insertar(Nota objeto) {
		// TODO Auto-generated method stub
		return false;
	}

}
