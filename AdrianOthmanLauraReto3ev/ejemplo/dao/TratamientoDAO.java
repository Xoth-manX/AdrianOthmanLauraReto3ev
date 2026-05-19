package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Tratamiento;
import ejemplo.util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

    @Override
    public boolean insertar(Tratamiento objeto) {
        return false;
    }

    @Override
    public  List<Tratamiento> obtenerTodos() {

        String sql = "SELECT * FROM tratamientos";

        List<Tratamiento> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Tratamiento t = new Tratamiento();

                t.setNombre(rs.getString("nombre"));
                t.setPrecio(rs.getDouble("precio"));

                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error tratamientos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Tratamiento obtenerPorId(int id) {

        String sql = "SELECT * FROM tratamientos WHERE id_tratamiento=?";

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Tratamiento t = new Tratamiento();

                t.setNombre(rs.getString("nombre"));
                t.setPrecio(rs.getDouble("precio"));

                return t;
            }

        } catch (SQLException e) {
            System.out.println("Error tratamiento: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Tratamiento objeto) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }

    public int contarTratamientosHistorial(int idTratamiento) {

        String sql = """
                SELECT COUNT(*) total
                FROM historial
                WHERE id_tratamiento=?
                """;

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTratamiento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Error contar tratamientos: " + e.getMessage());
        }

        return 0;
    }
}
