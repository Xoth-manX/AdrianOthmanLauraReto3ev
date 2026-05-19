package ejemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejemplo.modelo.Factura;
import ejemplo.modelo.Historial;
import ejemplo.modelo.LineaFactura;
import ejemplo.modelo.Mascota;
import ejemplo.util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura> {
	public static FacturaDAO facturaDAO;
	public static Scanner sc;
	public static LineaFacturaDAO lineafacturaDAO;
	public static ClienteDAO clienteDAO;
	public static MascotaDAO mascotaDAO;
	public static HistorialDAO historialDAO;
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

	/**
	 * @param mes se ingrese el mes en formato numero ejemplo 4
	 * @return
	 */
	public List<Factura> obtenerFacturaporMes(int mes) {
		List<Factura> lista = new ArrayList<Factura>();

		String sql = """
				select id_factura, id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total
				from facturas where month(fecha) = ?
				""";

		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

	// Creacion del ejercicio numero 4
	/**
	 * Muestra todas las facturas generadas y se introduce un id factura y muestra
	 * el detalle de la factura por linea de factura
	 */
	public static void factura4() {
		sc = new Scanner(System.in);
		lineafacturaDAO = new LineaFacturaDAO();
		facturaDAO = new FacturaDAO();
		facturaDAO.obtenerTodos();
		System.out.println("SE MUESTRAN TODAS LAS FACTURAS A LA FECHA \n" + ".".repeat(50));
		for (Factura a : facturaDAO.obtenerTodos()) {
			System.out.println(a);
		}
		System.out.println("\nINTRODUCE UN NUMERO DE ID DE FACTURA");
		String nume = sc.nextLine();
		int id = Integer.parseInt(nume);
		Factura f = facturaDAO.obtenerPorId(id);
		if (f == null) {
			System.out.println("Factura no encontrada");
			return;
		} else {
			System.out.println("Factura  " + f.getId_factura() + " fecha " + f.getFecha() + "\n Cliente: ....\" "
					+ f.getId_cliente() + "\n Mascota ID:.." + f.getId_mascota());
		}
		System.out.println("LINEAS POR FACTURA");
		for (LineaFactura lf : lineafacturaDAO.obtenerPorFactura(id)) {
			System.out.println(lf);
		}
	}

	// Factura por numero de mes ejercicio10
	// BUSCAR FACTURA NUMERO DEL MES
	/**
	 * Se ingrese un numero de factura por el mes y te buscar que factura existe en
	 * ese mes
	 */
	public static void FacturaporMes10() {
		sc = new Scanner(System.in);
		lineafacturaDAO = new LineaFacturaDAO();
		facturaDAO = new FacturaDAO();
		System.out.println("\nINGRESE UN NUMERO DE FACTURA SE BUSCARA POR MES \n" + ".".repeat(50));
		String nume = sc.nextLine();
		int mes = Integer.parseInt(nume);
		List<Factura> facturas = facturaDAO.obtenerFacturaporMes(mes);
		double totalfacturado = 0;
		totalfacturado = 0;
		for (Factura f : facturas) {
			System.out.println(f.getId_factura() + " - " + f.getFecha() + " - " + f.getTotal());
			totalfacturado += f.getTotal();
		}
		System.out.println("Total: " + totalfacturado);
		if (totalfacturado == 0) {
			System.out.println("FACTURA NO EXISTE por mes");

		}
	}

	// ejercicio 16
	/**
	 * Duplicafactura16() duplica la factura ingrese el id de la factura y se
	 * duplicara con su linea de factura la fecha se colocara la fecha actual
	 */
	public static void Duplicafactura16() {
		sc = new Scanner(System.in);
		lineafacturaDAO = new LineaFacturaDAO();
		facturaDAO = new FacturaDAO();
		System.out.println("\nIngrese un numero de factura a Duplicar \n" + ".".repeat(50));
		String facturaduplicar = sc.nextLine();
		int duplicar = Integer.parseInt(facturaduplicar);
		Factura factori = facturaDAO.obtenerPorId(duplicar);
		if (factori == null) {
			System.out.println("factura no encontrada");
			return;
		}
		Factura nueva = new Factura();
		nueva.setId_cliente(factori.getId_cliente());
		nueva.setId_veterinario(factori.getId_veterinario());
		nueva.setId_mascota(factori.getId_mascota());
		nueva.setFecha(LocalDate.now());
		nueva.setSubtotal(factori.getSubtotal());
		nueva.setTotal_iva(factori.getTotal_iva());
		nueva.setTotal(factori.getTotal());

		if (facturaDAO.insertar(nueva)) {
			List<LineaFactura> lineasOrig = lineafacturaDAO.obtenerPorFactura(duplicar);
			for (LineaFactura lf : lineasOrig) {
				LineaFactura copia = new LineaFactura();
				copia.setId_factura(nueva.getId_factura());
				copia.setId_tratamiento(lf.getId_tratamiento());
				copia.setFecha(LocalDate.now());
				copia.setCantidad(lf.getCantidad());
				copia.setPrecio_tratamiento(lf.getPrecio_tratamiento());
				copia.setImporte(lf.getImporte());
				lineafacturaDAO.insertar(copia);
			}
			System.out.println("Factura duplicada: " + nueva.getId_factura());
			facturaDAO.obtenerTodos();
			for (Factura a : facturaDAO.obtenerTodos()) {
				System.out.println(a);
			}

		}
	}
	
	// 17. Elimina * un tratamiento del historial y rehaz una factura. Selecciona
	// una mascota,
	// muestra su historial de una fecha concreta, elimina uno de los tratamientos
	// de ese día y actualiza la factura asociada recalculando sus líneas, subtotal,
	// IVA y total
	public static void eliminaFactHist() {
		System.out.println("Ingrede ID mascota");
		sc = new Scanner(System.in);
		String nume = sc.nextLine();
		int id = Integer.parseInt(nume);
		Mascota m = mascotaDAO.obtenerPorId(id);
		System.out.println("Ingrede fecha");	
		String fec = sc.nextLine();
		LocalDate fecha=LocalDate.parse(fec);
		
	}
	
}
