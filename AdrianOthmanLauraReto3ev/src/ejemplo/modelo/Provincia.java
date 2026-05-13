package modelo;

public class Provincia {
	protected int id;
	protected String nombre;
	protected String comunidad_autonoma;
	protected double superficie;
	public Provincia() {
		super();
	}
	public Provincia(String nombre, String comunidad_autonoma, double superficie) {
		super();
		this.nombre = nombre;
		this.comunidad_autonoma = comunidad_autonoma;
		this.superficie = superficie;
	}
	public Provincia(int id, String nombre, String comunidad_autonoma, double superficie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.comunidad_autonoma = comunidad_autonoma;
		this.superficie = superficie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getComunidad_autonoma() {
		return comunidad_autonoma;
	}
	public void setComunidad_autonoma(String comunidad_autonoma) {
		this.comunidad_autonoma = comunidad_autonoma;
	}
	public double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nombre=" + nombre + ", comunidad_autonoma=" + comunidad_autonoma
				+ ", superficie=" + superficie + "]";
	}
	
	
}
