package ejemplo.modelo;

public class Tratamiento {

	protected String nombre;
	protected double precio;
	public Tratamiento(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	public Tratamiento() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Tratamiento [nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
}
