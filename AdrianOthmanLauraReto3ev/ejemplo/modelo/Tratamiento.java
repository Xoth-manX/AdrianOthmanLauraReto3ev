package ejemplo.modelo;

public class Tratamiento {

	protected int idtratamiento;
	protected String nombre;
	protected double precio;
	public Tratamiento(int idtratamieno, String nombre, double precio) {
		super();
		this.idtratamiento = idtratamieno;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Tratamiento(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	

	public Tratamiento() {
		super();
	}

	public int getIdtratamieno() {
		return idtratamiento;
	}
	public void setIdtratamieno(int idtratamieno) {
		this.idtratamiento = idtratamieno;
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
		return "Tratamiento [idtratamieno=" + idtratamiento + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	
}
