package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.LineaFactura;
import ejemplo.util.ConexionBD;

public class LineaFacturaDAO implements GenericDAO<LineaFactura> {

	@Override
	public boolean insertar(LineaFactura lineafactura) {

		String sql = "INSERT INTO lineas_factura (id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, lineafactura.getId_factura());
            ps.setInt(2, lineafactura.getId_tratamiento());
            ps.setObject(3, lineafactura.getFecha());
            ps.setInt(4, lineafactura.getCantidad());
            ps.setDouble(5, lineafactura.getPrecio_tratamiento());
            ps.setDouble(6, lineafactura.getImporte());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) lineafactura.setId_linea_factura(rs.getInt(1));
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error al insertar línea de factura: " + e.getMessage());
            return false;
        }

	}

	@Override
	public List<LineaFactura> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaFactura obtenerPorId(int id) {

		String sql = "SELECT id_linea_factura, id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe FROM lineas_factura WHERE id_linea_factura = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapear(rs);
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener Linea de Factura por ID: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizar(LineaFactura linearfactura) {
		String sql = """
				update lineas_factura id_factura=?, id_tratamiento=?, fecha=?, cantidad=?, precio_tratamiento=?, importe=? WHERE id_linea_factura=?
				""";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, linearfactura.getId_factura());
			ps.setInt(2, linearfactura.getId_tratamiento());
			ps.setObject(3, linearfactura.getFecha());
			ps.setInt(4, linearfactura.getCantidad());
			ps.setDouble(5, linearfactura.getPrecio_tratamiento());
			ps.setDouble(6, linearfactura.getImporte());
			ps.setInt(7, linearfactura.getId_linea_factura());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al actualizar Linea de Factura: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {

		String sql = "DELETE FROM lineas_factura WHERE id_linea_factura = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar Linea de Factura: " + e.getMessage());
			return false;
		}

	}

	private LineaFactura mapear(ResultSet rs) throws SQLException {
		LineaFactura p = new LineaFactura();

		p.setId_linea_factura(rs.getInt("id_linea_factura"));
		p.setId_factura(rs.getInt("id_factura"));
		p.setId_tratamiento(rs.getInt("id_tratamiento"));
		p.setFecha(rs.getObject("fecha", LocalDate.class));
		p.setCantidad(rs.getInt("cantidad"));
		p.setPrecio_tratamiento(rs.getDouble("precio_tratamiento decimal"));
		p.setImporte(rs.getDouble("importe"));

		return p;
	}

	public List<LineaFactura> obtenerPorFactura(int idFactura) {
		List<LineaFactura> lista = new ArrayList<>();
		String sql = "SELECT id_linea_factura, id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe "
				+ "FROM lineas_factura WHERE id_factura = ? ORDER BY id_linea_factura";

		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idFactura);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener líneas por factura: " + e.getMessage());
		}
		return lista;
	}
}
