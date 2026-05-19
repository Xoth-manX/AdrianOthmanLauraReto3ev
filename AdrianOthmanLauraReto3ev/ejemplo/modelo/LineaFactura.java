package ejemplo.modelo;

import java.time.LocalDate;

public class LineaFactura {
	
	private int id_linea_factura;
	private int id_factura;
	private int id_tratamiento;
	LocalDate fecha;
	private int cantidad;
	private double precio_tratamiento;
	private double importe;
	
		
	public LineaFactura() {
		super();
	}


	public LineaFactura(int id_factura, int id_tratamiento, LocalDate fecha, int cantidad, double precio_tratamiento,
			double importe) {
		super();
		this.id_factura = id_factura;
		this.id_tratamiento = id_tratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precio_tratamiento = precio_tratamiento;
		this.importe = importe;
	}



	public LineaFactura(int id_linea_factura, int id_factura, int id_tratamiento, LocalDate fecha, int cantidad,
			double precio_tratamiento, double importe) {
		super();
		this.id_linea_factura = id_linea_factura;
		this.id_factura = id_factura;
		this.id_tratamiento = id_tratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precio_tratamiento = precio_tratamiento;
		this.importe = importe;
	}


	public int getId_linea_factura() {
		return id_linea_factura;
	}


	public void setId_linea_factura(int id_linea_factura) {
		this.id_linea_factura = id_linea_factura;
	}


	public int getId_factura() {
		return id_factura;
	}


	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}


	public int getId_tratamiento() {
		return id_tratamiento;
	}


	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getPrecio_tratamiento() {
		return precio_tratamiento;
	}


	public void setPrecio_tratamiento(double precio_tratamiento) {
		this.precio_tratamiento = precio_tratamiento;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}


	@Override
	public String toString() {
		return "LineaFactura [id_linea_factura=" + id_linea_factura + ", id_factura=" + id_factura + ", id_tratamiento="
				+ id_tratamiento + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precio_tratamiento="
				+ precio_tratamiento + ", importe=" + importe + "]";
	}



	

	
}
