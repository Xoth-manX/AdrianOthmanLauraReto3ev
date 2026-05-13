package modelo;

public class Autor {
	protected int id;
	protected String nombre;
	protected String nacionalidad;
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
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Autor(String nombre, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	public Autor() {
		super();
	}

	public Autor(int id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
	
}
