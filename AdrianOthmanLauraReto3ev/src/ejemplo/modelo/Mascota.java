package ejemplo.modelo;

import java.time.LocalDate;

public class Mascota {
 
	protected int id_Mascota;
	protected int id_cliente;
	protected String nombre;
	protected String especie;
	protected LocalDate fechaNacimiento;
	protected double peso;
	public Mascota(int id_Mascota,  int id_cliente, String nombre, String especie, LocalDate fechaNacimiento, double peso) {
		super();
		this.id_Mascota=id_Mascota;
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
	}
	public Mascota(  int id_cliente, String nombre, String especie, LocalDate fechaNacimiento, double peso) {
		super();
		
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
		
	}
	
	public Mascota() {
	}
	public int getId_Mascota() {
		return id_Mascota;
	}
	public void setId_Mascota(int id_Mascota) {
		this.id_Mascota = id_Mascota;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Mascota [id_Mascota=" + id_Mascota + ", id_cliente=" + id_cliente + ", nombre=" + nombre + ", especie="
				+ especie + ", fechaNacimiento=" + fechaNacimiento + ", peso=" + peso + "]";
	}
	
}
