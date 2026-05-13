package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Nota;
import modelo.Provincia;
import util.ConexionBD;

public class ProvinciaDAO implements GenericDAO<Provincia> {
	
	public Provincia obtenerProvinciaConMasMunicipios() {
		String sql = """
				select a.id, a.nombre, a.comunidad_autonoma, superficie from municipios b inner join provincias a
				on b.provincia_id=a.id group by provincia_id order by count(*) desc limit 1;
				""";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					return mapearFila(rs);
				}
			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todas las notas: " + e.getMessage());
			}
		System.out.println();
		return null;
	}
	
	private Provincia mapearFila(ResultSet rs) throws SQLException {
		Provincia p = new Provincia();
		p.setId(rs.getInt("id"));
		p.setNombre(rs.getString("nombre"));
		p.setComunidad_autonoma(rs.getString("comunidad_autonoma"));
		p.setSuperficie(rs.getDouble("superficie"));
		return p;
	}
	
	@Override
	public boolean insertar(Provincia objeto) {
		String sql = """
				insert into provincia (nombre, comunidad_autonoma, superficie) values (?, ?, ?)
				""";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

					pstmt.setString(1, objeto.getNombre());
					pstmt.setString(2, objeto.getComunidad_autonoma());
					pstmt.setDouble(3, objeto.getSuperficie());

					int filas = pstmt.executeUpdate();

					if (filas > 0) {
						try (ResultSet rs = pstmt.getGeneratedKeys()) {
							if (rs.next()) {
								objeto.setId(rs.getInt(1)); // asigna el ID
								return true;
							}
						}
					}
					return false;

				} catch (SQLException e) {
					System.err.println("Error SQL al insertar '" + objeto.getNombre() + "': " + e.getMessage());
					return false;
				}
	}

	@Override
	public List<Provincia> obtenerTodos() {
		List<Provincia> lista = new ArrayList<>();
		String sql = """
				select * from provincias order by nombre
				""";
		try (Connection conn = ConexionBD.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					Provincia p = new Provincia();
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setComunidad_autonoma(rs.getString("comunidad_autonoma"));
					p.setSuperficie(rs.getDouble("superficie"));
					lista.add(p);
				}
			} catch (SQLException e) {
				System.err.println("Error al obtener municipios: " + e.getMessage());
			}
		return lista;
	}

	@Override
	public Provincia obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Provincia objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
