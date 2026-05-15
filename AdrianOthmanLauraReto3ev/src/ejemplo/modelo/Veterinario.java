package ejemplo.modelo;

public class Veterinario extends Persona {
	protected int id_veterinario;
	protected String num_colegiado;
	public Veterinario(String dni, String nombre, int id_veterinario, String num_colegiado) {
		super(dni, nombre);
		this.id_veterinario = id_veterinario;
		this.num_colegiado = num_colegiado;
	}
	public Veterinario(String dni, String nombre, String num_colegiado) {
		super(dni, nombre);
		this.num_colegiado = num_colegiado;
	}
	public Veterinario() {
		super();
	}
	
	public int getId_veterinario() {
		return id_veterinario;
	}
	public void setId_veterinario(int id_veterinario) {
		this.id_veterinario = id_veterinario;
	}
	public String getNum_colegiado() {
		return num_colegiado;
	}
	public void setNum_colegiado(String num_colegiado) {
		this.num_colegiado = num_colegiado;
	}
	@Override
	public String toString() {
		return "Veterinario [id_veterinario=" + id_veterinario + ", num_colegiado=" + num_colegiado + "]";
	}
	
}
