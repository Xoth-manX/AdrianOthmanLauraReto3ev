package ejemplo.modelo;

public class Persona {
	protected int id_persona;
	protected String dni;
	protected String nombre;
	public int getId_persona() {
		return id_persona;
	}
	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Persona(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
	public Persona() {
		super();
	}
	public Persona(int id_persona, String dni, String nombre) {
		super();
		this.id_persona = id_persona;
		this.dni = dni;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Persona [id_persona=" + id_persona + ", dni=" + dni + ", nombre=" + nombre + "]";
	}
}
