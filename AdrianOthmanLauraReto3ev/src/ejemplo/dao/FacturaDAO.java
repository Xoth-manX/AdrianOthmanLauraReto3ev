package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ejemplo.modelo.Factura;
import ejemplo.util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura> {

	@Override
	public boolean insertar(Factura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Factura> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura obtenerPorId(int id) {
		
		String sql = """
                SELECT * FROM factura 
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
	public boolean actualizar(Factura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
    private Factura mapear(ResultSet rs) throws SQLException {
        Factura p = new Factura();

        p.setId_factura(rs.getInt("id_factura"));
        p.setId_cliente(rs.getInt("id_cliente "));
        p.setId_veterinario(rs.getInt("id_veterinario"));
        p.setId_mascota(rs.getInt("id_mascota"));
        p.setFecha(rs.getObject("fecha", LocalDate.class));
        p.setSubtotal(rs.getDouble("subtotal"));
        p.setTotal_iva(rs.getDouble("total_iva"));
        p.setTotal(rs.getDouble("total"));
        
        return p;
    }
    
	
	public Factura  obtenerPorIdFacturaLineasFactura(int id) {
		
		String sql = """
                select * from facturas f 
                inner join lineas_factura a on a.id_factura=f.id_factura
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


}
