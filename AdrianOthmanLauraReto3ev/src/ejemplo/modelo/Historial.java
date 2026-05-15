package ejemplo.modelo;

import java.time.LocalDate;

public class Historial {

	protected int id_mascota;
	protected int id_tratamiento;
	protected int id_veterinario;
	protected LocalDate fecha;
	public Historial(int id_mascota, int id_tratamiento, int id_veterinario, LocalDate fecha) {
		super();
		this.id_mascota = id_mascota;
		this.id_tratamiento = id_tratamiento;
		this.id_veterinario = id_veterinario;
		this.fecha = fecha;
	}
	public Historial() {
		super();
	}
	public int getId_mascota() {
		return id_mascota;
	}
	public void setId_mascota(int id_mascota) {
		this.id_mascota = id_mascota;
	}
	public int getId_tratamiento() {
		return id_tratamiento;
	}
	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}
	public int getId_veterinario() {
		return id_veterinario;
	}
	public void setId_veterinario(int id_veterinario) {
		this.id_veterinario = id_veterinario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Historial [id_mascota=" + id_mascota + ", id_tratamiento=" + id_tratamiento + ", id_veterinario="
				+ id_veterinario + ", fecha=" + fecha + "]";
	}
	
	
}
