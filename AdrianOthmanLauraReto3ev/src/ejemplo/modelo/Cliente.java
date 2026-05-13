package ejemplo.modelo;

public class Cliente extends Persona {
	protected int id_cliente;
	protected int telefono;
	public Cliente(String dni, String nombre, int id_cliente, int telefono) {
		super(dni, nombre);
		this.id_cliente = id_cliente;
		this.telefono = telefono;
	}
	public Cliente(String dni, String nombre, int telefono) {
		super(dni, nombre);
		this.telefono = telefono;
	}
	public Cliente(String dni, String nombre) {
		super(dni, nombre);
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", telefono=" + telefono + "]";
	}
}
