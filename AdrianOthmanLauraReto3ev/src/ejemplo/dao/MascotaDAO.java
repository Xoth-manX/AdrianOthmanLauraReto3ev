package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ejemplo.modelo.Mascota;
import ejemplo.util.ConexionBD;

public class MascotaDAO implements GenericDAO<Mascota> {

    @Override
    public boolean insertar(Mascota mascota) {

        String sql = """
                INSERT INTO mascotas
                (id_cliente,nombre,especie,fecha_nacimiento,peso)
                VALUES(?,?,?,?,?)
                """;

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, mascota.getId_cliente());
            ps.setString(2, mascota.getNombre());
            ps.setString(3, mascota.getEspecie());
            ps.setObject(4, mascota.getFechaNacimiento());
            ps.setDouble(5, mascota.getPeso());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    mascota.setId_Mascota(rs.getInt(1));
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error insertar mascota: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Mascota> obtenerTodos() {

        String sql = "SELECT * FROM mascotas";

        List<Mascota> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener mascotas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public Mascota obtenerPorId(int id) {

        String sql = "SELECT * FROM mascotas WHERE id_mascota=?";

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearFila(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error obtener mascota: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean actualizar(Mascota mascota) {

        String sql = """
                UPDATE mascotas
                SET nombre=?,
                    especie=?,
                    fecha_nacimiento=?,
                    peso=?
                WHERE id_mascota=?
                """;

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setObject(3, mascota.getFechaNacimiento());
            ps.setDouble(4, mascota.getPeso());
            ps.setInt(5, mascota.getId_Mascota());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar mascota: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = """
                DELETE m
                FROM mascotas m
                LEFT JOIN facturas f
                ON m.id_mascota = f.id_mascota
                WHERE m.id_mascota = ?
                AND f.id_factura IS NULL
                """;

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar mascota: " + e.getMessage());
        }

        return false;
    }

    public boolean actualizarPeso(int idMascota, double pesoNuevo) {

        String sql = "UPDATE mascotas SET peso=? WHERE id_mascota=?";

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, pesoNuevo);
            ps.setInt(2, idMascota);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar peso: " + e.getMessage());
        }

        return false;
    }

    public List<Mascota> obtenerMascotasPorCliente(int idCliente) {

        String sql = "SELECT * FROM mascotas WHERE id_cliente=?";

        List<Mascota> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener mascotas cliente: " + e.getMessage());
        }

        return lista;
    }

    private Mascota mapearFila(ResultSet rs) throws SQLException {

        Mascota m = new Mascota();

        m.setId_Mascota(rs.getInt("id_mascota"));
        m.setId_cliente(rs.getInt("id_cliente"));
        m.setNombre(rs.getString("nombre"));
        m.setEspecie(rs.getString("especie"));
        m.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
        m.setPeso(rs.getDouble("peso"));

        return m;
    }
}