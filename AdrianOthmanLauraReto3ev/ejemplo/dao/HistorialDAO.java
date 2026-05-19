package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Historial;
import ejemplo.util.ConexionBD;

public class HistorialDAO implements GenericDAO<Historial> {

    @Override
    public boolean insertar(Historial historial) {

        String sql = """
                INSERT INTO historial
                (id_mascota,id_tratamiento,id_veterinario,fecha)
                VALUES(?,?,?,?)
                """;

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, historial.getId_mascota());
            ps.setInt(2, historial.getId_tratamiento());
            ps.setInt(3, historial.getId_veterinario());
            ps.setObject(4, historial.getFecha());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar historial: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Historial> obtenerTodos() {

        String sql = "SELECT * FROM historial";

        List<Historial> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener historial: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Historial obtenerPorId(int id) {

        String sql = "SELECT * FROM historial WHERE id_historial=?";

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearFila(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error obtener historial: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Historial historial) {

        String sql = """
                UPDATE historial
                SET id_tratamiento=?,
                    id_veterinario=?,
                    fecha=?
                WHERE id_historial=?
                """;

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM historial WHERE id_historial=?";

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar historial: " + e.getMessage());
        }

        return false;
    }

    public List<Historial> obtenerHistorialPorIdMascota(int idMascota) {

        String sql = "SELECT * FROM historial WHERE id_mascota=?";

        List<Historial> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMascota);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error historial mascota: " + e.getMessage());
        }

        return lista;
    }

    private Historial mapearFila(ResultSet rs) throws SQLException {

        Historial h = new Historial();

        h.setId_mascota(rs.getInt("id_mascota"));
        h.setId_tratamiento(rs.getInt("id_tratamiento"));
        h.setId_veterinario(rs.getInt("id_veterinario"));
        h.setFecha(rs.getObject("fecha", LocalDate.class));

        return h;
    }
}

