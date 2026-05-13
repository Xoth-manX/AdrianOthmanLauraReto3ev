package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ejemplo.modelo.LineaFactura;
import ejemplo.util.ConexionBD;

public class LineaFacturaDAO implements GenericDAO<LineaFactura>{

	@Override
	public boolean insertar(LineaFactura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LineaFactura> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaFactura obtenerPorId(int id) {
		
		String sql = """
                SELECT * FROM lineas_factura
                WHERE id = ?
                """;

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener préstamo por id: " + e.getMessage());
        }

        return null;

	}

	@Override
	public boolean actualizar(LineaFactura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
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




}
