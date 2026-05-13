package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Autor;
import util.ConexionBD;

public class AutorDAO implements GenericDAO<Autor> {
	
	public Autor autorConMasLibros() {
		String sql = """
				select a.*, count(b.id) num from autor a inner join
				libro b on a.id=b.id_autor group by a.id order by
				count(b.id) desc limit 1
				""";
		try (Connection con = ConexionBD.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
			
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			return mapearFila(rs);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
		return null;
	}
	
	public Autor mapearFila(ResultSet rs) throws SQLException {
		Autor a = new Autor();
		a.setId(rs.getInt("id"));
		a.setNombre(rs.getString("nombre"));
		a.setNacionalidad(rs.getString("nacionalidad"));
		return a;
	}
	
	@Override
	public Autor obtenerPorId(int id) {
		String sql = """
				SELECT id,nombre,nacionalidad
				FROM autor
				WHERE id=?
				""";
		try (Connection con = ConexionBD.getConnection(); 
                    PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new Autor(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	
	@Override
	public boolean insertar(Autor objeto) {
		String sql = """
				insert into autor (nombre, nacionalidad) values(?,?)
				""";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

					pstmt.setString(1, objeto.getNombre());
					pstmt.setString(2, objeto.getNacionalidad());
					
					int filas = pstmt.executeUpdate();
					if (filas>0) {
						System.out.println("Se han actualizado " + filas + " filas");
					}
					return true;
				} catch (SQLException e) {
					System.err.println("Error SQL al insertar '" +  e.getMessage());
					return false;
				}
	}

	@Override
	public List<Autor> obtenerTodos() {
		ArrayList<Autor> lista = new ArrayList<Autor>();
		String sql = """
				select *, count(idautor) from autor group by 
				""";
		return null;
	}

	@Override
	public boolean actualizar(Autor objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
