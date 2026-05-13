package modelo;

public class Municipio {
	protected int id;
	protected String nombre;
	protected int habitantes;
	protected boolean es_capital;
	protected int provincia_id;
	public Municipio() {
		super();
	}
	public Municipio(String nombre, int habitantes, boolean es_capital, int provincia_id) {
		super();
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.es_capital = es_capital;
		this.provincia_id = provincia_id;
	}
	public Municipio(int id, String nombre, int habitantes, boolean es_capital, int provincia_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.es_capital = es_capital;
		this.provincia_id = provincia_id;
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
	public int getHabitantes() {
		return habitantes;
	}
	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}
	public boolean isEs_capital() {
		return es_capital;
	}
	public void setEs_capital(boolean es_capital) {
		this.es_capital = es_capital;
	}
	public int getProvincia_id() {
		return provincia_id;
	}
	public void setProvincia_id(int provincia_id) {
		this.provincia_id = provincia_id;
	}
	@Override
	public String toString() {
		return "Municipio [id=" + id + ", nombre=" + nombre + ", habitantes=" + habitantes + ", es_capital="
				+ es_capital + ", provincia_id=" + provincia_id + "]";
	}
}
