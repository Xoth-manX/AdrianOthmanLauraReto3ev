package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Factura;
import ejemplo.util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura> {

	@Override
	public boolean insertar(Factura factura) {

		String sql = "insert into facturas (id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total) values (?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, factura.getId_cliente());
			ps.setInt(2, factura.getId_veterinario());
			ps.setInt(3, factura.getId_mascota());
			ps.setObject(4, factura.getFecha());
			ps.setDouble(5, factura.getSubtotal());
			ps.setDouble(6, factura.getTotal_iva());
			ps.setDouble(7, factura.getTotal());

			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						factura.setId_factura(rs.getInt(1));
					}
				}
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.err.println("Error no se puede insertar la factura: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Factura> obtenerTodos() {
		List<Factura> lista = new ArrayList<>();
		String sql = "select id_factura, id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total "
				+ "from facturas order by fecha desc";
		
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener facturas: " + e.getMessage());
		}
		return lista;
	}
	
		

	@Override
	public Factura obtenerPorId(int id) {

		String sql = "select id_factura, id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total FROM facturas WHERE id_factura = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener datos por id " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizar(Factura factura) {
		String sql = "update facturas set id_cliente=?, id_veterinario=?, id_mascota=?, fecha=?, subtotal=?, total_iva=?, total=? WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, factura.getId_cliente());
			ps.setInt(2, factura.getId_veterinario());
			ps.setInt(3, factura.getId_mascota());
			ps.setObject(4, factura.getFecha());
			ps.setDouble(5, factura.getSubtotal());
			ps.setDouble(6, factura.getTotal_iva());
			ps.setDouble(7, factura.getTotal());
			ps.setInt(8, factura.getId_factura());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar factura: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "delete from facturas where id_factura = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Eror no existe la factura " + e.getMessage());
			return false;
		}
	}

	public List<Factura> obtenerFacturaporMes(int mes) {
		List<Factura> lista= new ArrayList<Factura>();
		
		String sql = """
				select id_factura, id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total
				from facturas where month(fecha) = ?
				""";
		
	
		try (Connection con = ConexionBD.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {

		    ps.setInt(1, mes);
		    try (ResultSet rs = ps.executeQuery()) {
		        while (rs.next()) {
		            lista.add(mapear(rs));
		        }
		    }
		} catch (SQLException e) {
		    System.err.println("error al obtener fatura por mes: " + e.getMessage());
		}
		return lista;
		}
		
	

	private Factura mapear(ResultSet rs) throws SQLException {
		Factura p = new Factura();

		p.setId_factura(rs.getInt("id_factura"));
		p.setId_cliente(rs.getInt("id_cliente"));
		p.setId_veterinario(rs.getInt("id_veterinario"));
		p.setId_mascota(rs.getInt("id_mascota"));
		p.setFecha(rs.getObject("fecha", LocalDate.class));
		p.setSubtotal(rs.getDouble("subtotal"));
		p.setTotal_iva(rs.getDouble("total_iva"));
		p.setTotal(rs.getDouble("total"));

		return p;
	}
	
}