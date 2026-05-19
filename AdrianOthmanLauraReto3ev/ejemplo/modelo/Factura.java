package ejemplo.modelo;

import java.time.LocalDate;

public class Factura  {
		private int id_factura;
		private int id_cliente;
		private int id_veterinario;
		private int id_mascota;
		LocalDate fecha;
		private double subtotal;
		private double total_iva;
		private double total;
						
		
		public Factura() {
			super();
		}

		/**
		 * @param id_cliente id del cliente ingresado
		 * @param id_veterinario  id del veterinario
		 * @param id_mascota id id de la mascota
		 * @param fecha la fecha
		 * @param subtotal es es subtotal sin IVA
		 * @param total_iva el iva es 21%
		 * @param total subtotal + el iva
		 */
		public Factura(int id_cliente, int id_veterinario, int id_mascota, LocalDate fecha, double subtotal,
				double total_iva, double total) {
			super();
			this.id_cliente = id_cliente;
			this.id_veterinario = id_veterinario;
			this.id_mascota = id_mascota;
			this.fecha = fecha;
			this.subtotal = subtotal;
			this.total_iva = total_iva;
			this.total = total;
		}


		public Factura(int id_factura, int id_cliente, int id_veterinario, int id_mascota, LocalDate fecha,
				double subtotal, double total_iva, double total) {
			super();
			this.id_factura = id_factura;
			this.id_cliente = id_cliente;
			this.id_veterinario = id_veterinario;
			this.id_mascota = id_mascota;
			this.fecha = fecha;
			this.subtotal = subtotal;
			this.total_iva = total_iva;
			this.total = total;
		}

		public int getId_factura() {
			return id_factura;
		}

		public void setId_factura(int id_factura) {
			this.id_factura = id_factura;
		}

		public int getId_cliente() {
			return id_cliente;
		}

		public void setId_cliente(int id_cliente) {
			this.id_cliente = id_cliente;
		}

		public int getId_veterinario() {
			return id_veterinario;
		}

		public void setId_veterinario(int id_veterinario) {
			this.id_veterinario = id_veterinario;
		}

		public int getId_mascota() {
			return id_mascota;
		}

		public void setId_mascota(int id_mascota) {
			this.id_mascota = id_mascota;
		}

		public LocalDate getFecha() {
			return fecha;
		}

		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}

		public double getSubtotal() {
			return subtotal;
		}

		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}

		public double getTotal_iva() {
			return total_iva;
		}

		public void setTotal_iva(double total_iva) {
			this.total_iva = total_iva;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		@Override
		public String toString() {
			return "Factura [id_factura=" + id_factura + ", id_cliente=" + id_cliente + ", id_veterinario="
					+ id_veterinario + ", id_mascota=" + id_mascota + ", fecha=" + fecha + ", subtotal=" + subtotal
					+ ", total_iva=" + total_iva + ", total=" + total + "]";
		}


		
			
		
}
