package ejemplo.modelo;

public class Cliente {
	protected int id_cliente;
	protected int id_persona;
	protected int telefono;
	public Cliente(int id_cliente, int id_persona, int telefono) {
		super();
		this.id_cliente = id_cliente;
		this.id_persona = id_persona;
		this.telefono = telefono;
	}
	public Cliente(int id_persona, int telefono) {
		super();
		this.id_persona = id_persona;
		this.telefono = telefono;
	}
	public Cliente() {
		super();
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_persona() {
		return id_persona;
	}
	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", id_persona=" + id_persona + ", telefono=" + telefono + "]";
	}
}
